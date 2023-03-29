/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

/**
 *
 * @author student
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import helper.Message;

public class messageDatabase {
   private static Connection getCon(){
    Connection con = null;
    try{
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Message_DB?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC","root","student");
        System.out.println("Connection established");
    }catch(Exception e){
        System.out.println(e);
    }
    return con;
}
   public static ArrayList<Message> messageRead(String username){
       ArrayList<Message> bean = new ArrayList<Message>();
       try{
           Connection con = getCon();
           
           String q = "select * from MESSAGE WHERE username LIKE '" + username+"'";
           
           PreparedStatement ps = con.prepareStatement(q);
           ResultSet rs = ps.executeQuery();
           while(rs.next()){
               String text = rs.getString("text");
               Message message = new Message(text);
               bean.add(message);
           }
           con.close();
       }catch(Exception e){
           System.out.println(e);
       }
       return bean;
   }
   public static void writeMessage(String username, String text){
       try{
           Connection con = getCon();
           String q = "INSERT INTO MESSAGE (username, text) VALUES('"+username+"','"+text+"')";
           PreparedStatement ps = con.prepareStatement(q);
           ps.executeUpdate();
           con.close();
       }catch(Exception e){
           System.out.println(e);
       }
   }
}
