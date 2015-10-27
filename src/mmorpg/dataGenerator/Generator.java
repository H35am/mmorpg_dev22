/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mmorpg.dataGenerator;

import java.util.Date;
import java.util.Random;
import mmorpg.Characters;
import mmorpg.Helper;
import mmorpg.Servers;
import mmorpg.Users;

/**
 *
 * @author 0775768 <Hesam.Zarza>
 */
public class Generator {
    
    public Generator(){
    
    }
    
    public void generateServers(int length){
        Random r = new Random();
        Helper help = new Helper();
        for(int i = 0; i < length; i++){
            RandomString s = new RandomString(r.nextInt(8) + 1);
            
            String ip = r.nextInt(256) + "." + r.nextInt(256) + "." +r.nextInt(256) + "." + r.nextInt(256);
            String name = s.nextString();
            String location = s.nextString();
            int maxUsers = r.nextInt(999)+1;
            
            Servers server = new Servers(ip, name, location, maxUsers, 0);
            
            Servers serverControle = help.ReadServer(ip);
            if (serverControle == null){
                help.CreateServer(server);
            }
            
            
            
        }
    }
    
    public void generateUsers(int length){
        Random r = new Random();
        Helper help = new Helper();
        RandomDate rDate= new RandomDate();
        
        for(int i = 0; i < length; i++){
            RandomString s = new RandomString(r.nextInt(5) + 3);
            
            String name = s.nextString();
            int balance = r.nextInt(999)+1;
            String fName = s.nextString();
            String lName = s.nextString();
            String iban = s.nextString();
            Boolean banned = Math.random() < 0.5;
            String passw = s.nextString();
            int monthsPayed = r.nextInt(11)+1;
            Date lastPayed = rDate.nextDate();
            int slots = r.nextInt(1)+5;
            
            Users user = new Users(name, balance, fName, lName, iban, banned, passw, monthsPayed, lastPayed, slots);
            
            Users userControle = help.ReadUsers(name);
            if (userControle == null){
                help.CreateUser(user, help.getRandomServer().getAddress());
            }
        }
        
    }
    
    public void generateCharacters(int length){
        Random r = new Random();
        Helper help = new Helper();
        String classNames[] = {"Warrior", "Hunter", "Mage", "Priest", "Warlock"};        
        String raceNames[] = {"Human", "Orc", "Dwarf", "Elf", "Gnome", "Undead"};        
        for(int i = 0; i < length; i++){
            RandomString s = new RandomString(r.nextInt(5) + 3);
            
            String name = s.nextString();
            String className = classNames[r.nextInt(4)];
            String race = raceNames[r.nextInt(5)];
            int level = r.nextInt(100)+1;

            Characters character = new Characters(name, className, race, level);
            
            Characters characterControle = help.ReadCharacter(name);
            if (characterControle == null){
                help.CreateCharacter(character, help.getRandomUser().getUserName());
            }
        }
        
    }
    
    
}
