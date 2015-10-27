/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mmorpg;

import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import mmorpg.mngment.Login;

/**
 *
 * @author heszar
 */
public class Mmorpg {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        //Servers server = new Servers("120.0.0.2","Nieuwe server","NL",10,0);
        
        //Helper helper = new Helper();
        //Servers server = helper.ReadServer("120.0.0.1");
        
        //helper.CreateServer(server);
        //Users hesam = new Users("heszar",50,"Hesam","Zarza","NL90INGB0008910372",false,"12345",2,new Date(2015,12,12),5);
        //helper.CreateUser(hesam, server.getAddress());
        //Characters bob = new Characters("bob", "warrior", "black", 1);
        //helper.CreateCharacter(bob, hesam.getUserName());
        Login login = new Login();
        login.setVisible(true);
        
        
        
    }
    
}
