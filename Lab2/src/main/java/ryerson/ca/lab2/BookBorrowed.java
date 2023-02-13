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
public class BookBorrowed {

    String username;
    String password;
    ArrayList<Appointment> appointments;
    ArrayList<Drug> drugs;
            
    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    private  boolean available;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date date) {
        this.dueDate = date;
    }

    /*
    To be completed
     */
    public BookBorrowed(String bookName, String bookAuthor, Date borrowStartTime, boolean available) {
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.dueDate = borrowStartTime;
        this.available=available;
    }

    
}
