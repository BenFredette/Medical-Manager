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
public class Drug {
    String name;
    String dosage;
    
    public Drug(String name, String dosage){
        this.name = name;
        this.dosage = dosage;
    }
    
    public String getName(){
        return this.name;
    }
    
    public String getDosage(){
        return this.dosage;
    }
}
