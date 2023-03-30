/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helper;

import java.util.ArrayList;
import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
/**
 *
 * @author student
 */
    @XmlRootElement(name="prescription")
   @XmlAccessorType(XmlAccessType.FIELD)
public class Prescription {
        
      @XmlTransient
              private boolean tobePrescribed;
      
              public boolean isTobePrescribed(){
                  return tobePrescribed;
              }
              
              public void setTobePrescribed(boolean tobePrescribed){
                  this.tobePrescribed = tobePrescribed;
              }
              
              private String drugName, dosage, description;
              int id;
              
    public Prescription(int id,String name, String dosage, String description){
        this.drugName = name;
        this.dosage = dosage;
        this.description = description;
        this.id = id;
    }
    
    public String getName(){
        return this.drugName;
    }
    
    public String getDosage(){
        return this.dosage;
    }
    
    public String getDescription(){
        return this.description;
    }
    public int getId(){
        return this.id;
    }
}
