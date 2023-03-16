/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helper;

/**
 *
 * @author student
 */

import java.util.ArrayList;

public class Admin {
    ArrayList<User> users;
    
    //Collect information from form 
    public void addUser(User user){
        this.users.add(user);
    }
    
    public void removeUser(User user){
        for(int i = 0; i < this.users.size(); i++){
            if(this.users.get(i).username.equals(user.username)){
                this.users.remove(i);
            }
        }
    }
}
