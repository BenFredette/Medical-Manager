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
import helper.Prescription;
public class PrescriptionDatabase {
   private static Connection getCon(){
    Connection con = null;
    try{
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Prescribe_DB?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC","root","student");
        System.out.println("Connection established");
    }catch(Exception e){
        System.out.println(e);
    }
    return con;
}
   public static ArrayList<Prescription> prescriptionRead(String username){
       ArrayList<Prescription> bean = new ArrayList<Prescription>();
       try{
           Connection con = getCon();
           
           String q = "select * from PATIENT_PRESCRIPTIONS WHERE patient_uname LIKE '" + username+"'";
           
           PreparedStatement ps = con.prepareStatement(q);
           ResultSet rs = ps.executeQuery();
           while(rs.next()){
               String pname = rs.getString("patient_uname");
               int pres_id = rs.getInt("pres_id");
               
               String q2 = "select * from PRESCRIPTION WHERE id LIKE "+ pres_id;
               PreparedStatement ps2 = con.prepareStatement(q2);
               ResultSet rs2 = ps2.executeQuery();
               if(rs2.next()){
               String drugname = rs2.getString("drugname");
               String dosage = rs2.getString("dosage");
               Prescription p = new Prescription(pres_id,drugname, dosage);
               System.out.println("Prescription with id" +pres_id+" and name " + drugname+" added");
               bean.add(p);
               }
           }
           con.close();
       }catch(Exception e){
           System.out.println(e);
       }
       return bean;
   }
   public static void writePrescription(int id, String drugname, String dosage, String patient_uname){
       try{
           Connection con = getCon();
           String q = "INSERT INTO PRESCRIPTION (id, drugname, dosage) VALUES("+id+", '"+drugname+"', '"+dosage+"')";
           String q2 = "INSERT INTO PATIENT_PRESCRIPTIONS (patient_uname, pres_id) VALUES('"+patient_uname+"', "+id+")";
           PreparedStatement ps = con.prepareStatement(q);
           PreparedStatement ps2 = con.prepareStatement(q2);
           ps.executeUpdate();
           ps2.executeUpdate();
           System.out.println("Prescription Written");
           con.close();
       }catch(Exception e){
           System.out.println(e);
       }
   }
}
