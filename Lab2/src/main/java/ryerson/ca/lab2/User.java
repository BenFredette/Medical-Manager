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
    
    String name;
    String username;
    String password;
    String email;
    ArrayList<Appointment> appointments;
    ArrayList<String> messages;

    public String getName(){
        return name;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public void removeMessage(){
        this.messages.remove(0);
    }
}

