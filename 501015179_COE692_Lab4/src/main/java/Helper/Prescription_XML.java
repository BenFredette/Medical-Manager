/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helper;

/**
 *
 * @author student
 */
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import helper.Prescription;

 @XmlRootElement(name = "prescriptions")
@XmlAccessorType (XmlAccessType.FIELD)
       public class Prescription_XML{
     @XmlElement(name="prescription")
           private ArrayList<Prescription> prescriptions;
           
           
           public List<Prescription>getPrescription(){
               return prescriptions;
               
           }
          public  Prescription_XML(){
               
               
           }
           public void setPrescription(ArrayList<Prescription> pr){
               prescriptions=pr;
               
           }
           
       }

