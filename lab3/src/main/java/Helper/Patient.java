/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helper;

import Helper.User;
import java.util.ArrayList;

/**
 *
 * @author student
 */
public class Patient extends User{
    Doctor doctor;
    ArrayList<Prescription> drugs;
    
    public Patient(String name, String username, String password, String email, Doctor doctor){
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.doctor = doctor;
        this.drugs = new ArrayList<Prescription>();
        //this.appointments = new ArrayList<Appointment>();
        this.messages = new ArrayList<String>();
        
    }
    
    /* public void addApointment(Appointment app){
        int errorFlag = 0;
        //Loop through doctor's appointments and check for time conflict
        for(int i = 0; i < this.doctor.appointments.size(); i++){
            if((this.doctor.appointments.get(i).time == app.time) && (this.doctor.appointments.get(i).day.equals(app.day))){
                //Display Error Message in pop up 
                System.out.println("Oops, time slot is already booked!");
                errorFlag = 1;
            }
        }
        // Add appointment if there is no time conflict with the doctors appointments
        if(errorFlag == 0){
           this.appointments.add(app); 
           this.doctor.appointments.add(app);
        }
    }
    */ //May use later
    /* public void cancelApp(Appointment app){
        //Loop through appoitments and remove appointment from list
        for(int i = 0; i < this.appointments.size(); i++){
            if((this.appointments.get(i).day.equals(app.day)) && this.appointments.get(i).time == app.time ){
                this.appointments.remove(i);
                for(int j = 0; i < app.patient.appointments.size(); j++){
                   if((app.doctor.appointments.get(j).day.equals(app.day)) && app.doctor.appointments.get(j).time == app.time){
                       app.doctor.appointments.remove(j);
                       //Send message to Doctor
                       this.sendMessage(this.name + " has cancelled their appointment at " + app.time + " on " + app.day);
                   } 
                }
            }else{
                //Note** Should change time to String to have nicer output here
                System.out.println("Oops, you do not have an appointment at this time!");
                //Error message pop up
            }        
        }
    }
    */ //May use later
    public void sendMessage(String text){
        this.doctor.messages.add(text);
    }
    
    public void reportSideEffect(String string){
        //Send message to doctor about side effect
        //** Have not decided whats easiest way to do this yet. Will discuss after jsps are made
        this.sendMessage(this.name + " has reported a side effect from the drug");
    }
    
    public ArrayList<String> getMessages(){
        return this.messages;
    }
    
    public ArrayList<Prescription> getDrugs(){
        return this.drugs;
    }
    
    public String getUsername(){
        return this.username;
    }
    
    public Doctor getDoctor(){
        return this.doctor;
    }
    
    public String getDoctorUsername(){
        return this.doctor.getUsername();
    }
}
