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
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

/**
 *
 * @author student
 */
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

 @XmlRootElement(name = "messages")
@XmlAccessorType (XmlAccessType.FIELD)
       public class Message_XML{
     @XmlElement(name="message")
           private ArrayList<Message> messages;
           
           
           public List<Message> getMessage(){
               return messages;
               
           }
          public  Message_XML(){
               
               
           }
           public void setMessages(ArrayList<Message> mes){
               messages=mes;
               
           }
           
       }


