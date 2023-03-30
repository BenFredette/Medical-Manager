/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helper;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
/**
 *
 * @author student
 */

@XmlRootElement(name = "prescriptions")
@XmlAccessorType (XmlAccessType.FIELD)
public class PrescriptionsXML {
    @XmlElement(name = "prescriptions")
        private ArrayList<Prescription> prescriptions;
    
        public List<Prescription>getPrescriptions(){
            return prescriptions;
        }
        PrescriptionsXML(){
            
        }
        public void setPrescription(ArrayList<Prescription> pr){
            prescriptions = pr;
        }
}
