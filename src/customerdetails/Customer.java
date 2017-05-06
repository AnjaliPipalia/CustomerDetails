/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customerdetails;

/**
 * Customer class that contains user data
 * @author arp226
 */
public class Customer {
     private String id;
    private String email;
    private String fName;
    private String lName;
    
    public Customer(String ID,String Email,String FirstName,String LastName){
        this.id = ID;
        this.email=Email;
        this.fName = FirstName;
        this.lName = LastName;
        
    }
    //gets user ID
    public String getId() {
        return id;
    }
    //set the values for ID
    public void setId(String id) {
        this.id = id;
    }
    //gets user Email
    public String getEmail() {
        return email;
    }
  //set the values for Email
    public void setEmail(String email) {
        this.email = email;
    }
    //gets First Name
    public String getfName() {
        return fName;
    }
    //set the values for first Name
    public void setfName(String fName) {
        this.fName = fName;
    }
    //gets Last Name
    public String getlName() {
        return lName;
    }
    //set the values for Last name
    public void setlName(String lName) {
        this.lName = lName;
    }
    
}

