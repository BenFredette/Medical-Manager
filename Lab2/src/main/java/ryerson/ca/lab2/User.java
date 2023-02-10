/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ryerson.ca.lab2;

import java.util.ArrayList;
/**
 *
 * @author Ben and Faiz
 */
public class User {

    String username;
    String password;
    String name;
    ArrayList<Appointment> appointments;
    ArrayList<Message> messages;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    /*
    To be completed
     */    
}

