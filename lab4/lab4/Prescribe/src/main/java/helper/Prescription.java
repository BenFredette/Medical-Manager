/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
/**
 *
 * @author student
 */
@XmlRootElement(name = "prescription")
@XmlAccessorType(XmlAccessType.FIELD)
public class Prescription {
    int id;
    String name;
    String dosage;
    public Prescription(int id,String name, String dosage){
        this.name = name;
        this.dosage = dosage;
        this.id = id;
    }
    public Prescription(){
        
    }
    
    public String getName(){
        return this.name;
    }
    
    public String getDosage(){
        return this.dosage;
    }
}

