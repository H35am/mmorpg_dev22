/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mmorpg;

import java.util.Collection;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author heszar
 */
public class Helper {
    
    private EntityManagerFactory emf = null;
    private EntityManager em = null;
    private EntityTransaction tx = null;
    public Helper()
    {
        //Server connectie wordt aangemaakt
        emf = Persistence.createEntityManagerFactory("mmorpgPU");
        em = emf.createEntityManager();
        tx = em.getTransaction();
    }
    
    //server entity wordt aangemaakt
    public void CreateServer(Servers server)
    {
        
        tx.begin();
        //spoelt alles eerst door
        em.flush();
        //maakt daadwewrkelijk de entity
        em.persist(server);
        //voert uit
        tx.commit();
        em.close();
        emf.close();
    }
    
    public Servers ReadServer(String address)
    {
        return em.find(Servers.class, address);
    }
    
    public Servers UpdateServer(Servers updatedServer)
    {
        Servers currentServer = ReadServer(updatedServer.getAddress());
        tx.begin();
        em.merge(currentServer);
        currentServer.setName("nieuwe naam");
        tx.commit();
        em.close();
        emf.close();
        
//        if(currentServer != null)
//        {
//            if(!updatedServer.getLocation().equals(currentServer.getLocation()))
//            {
//                currentServer.setLocation(updatedServer.getLocation());
//            }
//            
//            if(!updatedServer.getName().equals(currentServer.getName()))
//            {
//                currentServer.setName(updatedServer.getName());
//            }
//            
//        
//        
//        }
        
        return currentServer;
        
    }
    
    public void DeleteServer(String address)
    {
        Servers server = ReadServer(address);
        if(server != null)
        {
            tx.begin();
            //spoelt alles eerst door
            em.flush();
            //maakt daadwewrkelijk de entity
            em.remove(server);
            //voert uit
            tx.commit();
            em.close();
            emf.close();
        }
        
    }
  
    
    //CR USERS
    public void CreateUser(Users user,String address)
    {
        //server object aangemaakt om te zoeken (in server naar adres)
        Servers server = ReadServer(address);
        //uit server object haalt usercollection en add user daar aan
        server.getUsersCollection().add(user);
        
        user.setAddress(server);
        
        tx.begin();
        em.flush();
        em.persist(user);
        tx.commit();
        em.close();
        emf.close();
    }
     
    public Users ReadUsers(String user_name)
    {
        return em.find(Users.class, user_name);
    }
    
    
    //C Cahracters
    public void CreateCharacter(Characters character, String user_name)
    {
         //server object aangemaakt om te zoeken (in user naar user_name)
        Users user = em.find(Users.class, user_name);
        //uit server object haalt usercollection en add user daar aan
        user.getCharactersCollection().add(character);
        
        character.setUserName(user);
        
        tx.begin();
        em.flush();
        em.persist(character);
        tx.commit();
        em.close();
        emf.close();
    }

    public String Login(String Username, String Password) {
        String message = "";
        
        //user ophalen
        Users user = ReadUsers(Username);
        
        //kijken of user niet null is
        if(user == null){
            message = "Geen gebruiker gevonden";
        }
        else
        {
            //controleren of het ingevoerde password gelijk is aan het opgeslagen password
            if(!user.getPassword().equals(Password)){
                //klopt dit allemaal? dan 'ok' = true;
                message = "Password klopt niet bij username";
            }
        }
        
        
        
        
        return message;
    }

    public Collection<Servers> getAllServers() {
        Query q = em.createQuery("SELECT e FROM Servers e");
        return (Collection<Servers>) q.getResultList();
    }
    
    
}
