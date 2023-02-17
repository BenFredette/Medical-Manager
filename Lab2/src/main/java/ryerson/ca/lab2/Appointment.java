/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ryerson.ca.lab2;

/**
 *
 * @author student
 */
public class Appointment {
    Doctor doctor;
    Patient patient;
    int time;
    String day;
    
    public Appointment(Doctor doctor, Patient patient, int time, String day){
        this.doctor = doctor;
        this.patient = patient;
        this.time = time;
        this.day = day;
    }
}
