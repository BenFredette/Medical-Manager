/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

/**
 *
 * @author student
 */
import java.util.ArrayList;
import persistence.messageDatabase;
import helper.Message_XML;
/**
 *
 * @author student
 */
public class MessageBusiness {
    
    public Message_XML getMessagesUser(String uname) {
        ArrayList<String> messages= messageDatabase.messageRead(uname);
        
        Message_XML mes = new Message_XML();
        mes.setMessages(messages);
        
        return (mes);
    }

public void message(String username, String text) {
        //Could incorporate boolean return value to handle SQL integrity errors 
        messageDatabase.writeMessage(username, text);
    }
}
