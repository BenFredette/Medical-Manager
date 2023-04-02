/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helper;

import java.util.ArrayList;
/**
 *
 * @author student
 */
public class Doctor extends User{
    ArrayList<Patient> patients;
    public Doctor(String name, String username, String password, String email){
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email; 
        this.messages = new ArrayList<String>();
    }
   
    //** Drug object to be created in doctor jsp
    public void prescribeDrug(Patient patient, Prescription drug){
        patient.drugs.add(drug);
        this.sendMessage(patient, "Dr. " + this.name + " has prescribed you " + drug.name + ". See drugs section for instructions");
    }
    
    /* public void cancellApp(Appointment app){
        //get appointment patient and send them a message
        //loop through Doctor appointments
        for(int i = 0; i < this.appointments.size(); i++){
            if((this.appointments.get(i).day.equals(app.day)) && this.appointments.get(i).time == app.time ){
                this.appointments.remove(i);
                for(int j = 0; i < app.patient.appointments.size(); j++){
                   if((app.patient.appointments.get(j).day.equals(app.day)) && app.patient.appointments.get(j).time == app.time){
                       app.patient.appointments.remove(j);
                       //Send message to patient
                       this.sendMessage(app.patient, this.name + " has cancelled the appointment you had at " + app.time + " on " + app.day);
                   } 
                }
            }        
        }
    } */ // May be used later
    
    public void sendMessage(Patient patient,String text){
        patient.messages.add(text);
    }
    
    public String getUsername(){
        return this.username;
    }
    
    public ArrayList<String> getMessages(){
        return this.messages;
    }
}
