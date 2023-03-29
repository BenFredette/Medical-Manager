/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.util.ArrayList;
import helper.Prescription;
import persistence.PrescriptionDatabase;
import helper.Prescription_XML;
/**
 *
 * @author student
 */
public class PrescribeBusiness {
    
    public Prescription_XML getPrescriptionsUser(String uname) {
        ArrayList<Prescription> prescriptions = PrescriptionDatabase.prescriptionRead(uname);
        
        Prescription_XML pr = new Prescription_XML();
        pr.setPrescription(prescriptions);
        
        return (pr);
    }

public void prescribe(int id, String drugname, String dosage, String patient_uname) {
        //Could incorporate boolean return value to handle SQL integrity errors 
        PrescriptionDatabase.writePrescription(id, drugname, dosage, patient_uname);
    }
}
