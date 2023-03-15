/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistance;

import Helper.User;
import Helper.Patient;
import Helper.Doctor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
/**
 *
 * @author student
 */
public class UserDatabase {
   private static Connection getCon(){
    Connection con = null;
    try{
        System.out.println("Before Connection");
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC [root on Default schema]","root","student");
        System.out.println("Connection established");
    }catch(Exception e){
        System.out.println(e);
    }
    return con;
}
   public Patient patientRead(String username, String password){
       Patient bean = null;
       try{
           Connection con = getCon();
           
           String q = "select * from PATIENT WHERE username LIKE '" + username+"'";
           
           PreparedStatement ps = con.prepareStatement(q);
           ResultSet rs = ps.executeQuery();
           if(rs.next()){
               String userName = rs.getString("username");
               String pass = rs.getString("password");
               String user_name = rs.getString("patient_name");
               String email = rs.getString("email");
               String doctor_uname = rs.getString("doctor_uname");
               
               //Create the doctor
               String q2 = "select * from DOCTOR WHERE username LIKE '" + doctor_uname+"'";
               
               PreparedStatement ps2 = con.prepareStatement(q2);
               ResultSet rs2 = ps2.executeQuery();
               
               String dusername = rs2.getString("username");
               String dpassword = rs2.getString("password");
               String doctor_name = rs2.getString("doctor_name");
               String demail = rs2.getString("email");
               
               Doctor bean2 = new Doctor(doctor_name, dusername,dpassword, demail); 
               //if(pass.equals(password)){
                //   System.out.println("In bean if statement");
               //    bean = new Patient(user_name,userName,pass, email, bean2);
               //}
               System.out.println("Before bean");
               bean = new Patient(user_name,userName,pass, email, bean2);
           }
           con.close();
       }catch(Exception e){
           System.out.println(e);
       }
       return bean;
   }
   public static Doctor doctorRead(String username, String password){
       Doctor bean = null;
       try{
       
       Connection con = getCon();
       
       String q = "select * from DOCTOR WHERE username LIKE '" + username+"'";
               
       PreparedStatement ps = con.prepareStatement(q);
       ResultSet rs = ps.executeQuery();
       if(rs.next()){        
       String dusername = rs.getString("username");
       String dpassword = rs.getString("password");
       String doctor_name = rs.getString("doctor_name");
       String demail = rs.getString("email");
       if(dpassword.equals(password)){
       bean = new Doctor(doctor_name,dusername,dpassword, demail);
       }
       }
       con.close();
   }catch(Exception e){
       System.out.println(e);
   }
       return bean;
   }
}
