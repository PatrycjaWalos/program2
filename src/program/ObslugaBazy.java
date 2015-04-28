/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package program;

import Baza.Result;
import Baza.User;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
//import static program.Program.getSessionFactory;


public class ObslugaBazy {
     private static SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            Configuration configuration = new Configuration();
            return configuration.configure()
                    .buildSessionFactory(
                            new StandardServiceRegistryBuilder()
                            .applySettings(configuration.getProperties())
                            .build());
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    
    
    
     
    public void AddUser() {
        buildSessionFactory();
        Session session = getSessionFactory().openSession();
        session.beginTransaction();
        String hql = "from User";
        Query q = session.createQuery(hql);
        //List resultList = q.list();
        //Object o;
        //User u = (User) o;
        System.out.println("Wprowadz nowego uzytkownika. Podaj imie:");
         Scanner zapis = new Scanner(System.in);
        String imie=zapis.nextLine();
        System.out.println("Podaj login:");
        String login = zapis.nextLine();
        System.out.println("Podaj haslo:");
        String password = zapis.nextLine();
         User u= new User(imie, login, password);
         session.save(u);
        
         session.getTransaction().commit();
        session.close();
        sessionFactory.close();
    }        
    public void PrintUser(){ // po loginach
        buildSessionFactory();
        Session session = getSessionFactory().openSession();
        session.beginTransaction();
        String hql = "from User";
        Query q = session.createQuery(hql);
        List resultList = q.list();
        System.out.println("Uzytkownicy:");
        System.out.println(resultList);
        
        for (Object o : resultList) {
            User u = (User) o;
                System.out.print(u.getIdUser());
                System.out.print(". ");
                 System.out.println(u.getLogin());

        }
        session.getTransaction().commit();
        session.close();
        sessionFactory.close();
  
    }
    
    public void Zaloguj(){
         buildSessionFactory();
        Session session = getSessionFactory().openSession();
        session.beginTransaction();
        
        
        Scanner zapis = new Scanner(System.in);
        System.out.print("Podaj Id:");
        int Id = zapis.nextInt();
        
       // String hql = "SELECT id_user, name,login, password FROM User WHERE id_user LIKE '" + Id + "'";
       // String hql = "SELECT id_user, name,login, password FROM User WHERE id_user IN ( '" + Id + "')";
        String hql = "from User";

        Query q = session.createSQLQuery(hql);
         List userList = q.list();
        
       // System.out.print("Podaj login:");
       // String login = zapis.nextLine();
        System.out.print("Podaj haslo:");
        String password = zapis.nextLine();
        
         for (Object o : userList) {
            User u = (User) o;
             
             if (Id==(u.getIdUser()))
                 //User u= (User) userList.get(Id);
                 if ( /*login.equals(u.getLogin()) &&*/ password.equals(u.getPassword()))
                 {
                     System.out.println("Witaj "+u.getName()+"! Zostales zalogowany");
                 }    
         }
             session.getTransaction().commit();
                 session.close();
             sessionFactory.close();
         }
         
    public void DeleteUser(){
         buildSessionFactory();
        Session session = getSessionFactory().openSession();
        session.beginTransaction();
        
        
        Scanner zapis = new Scanner(System.in);
        System.out.print("Podaj Id:");
        int Id = zapis.nextInt();
        String hql = "DELETE FROM User WHERE id_user = '" +Id +"' ";
        Query q = session.createSQLQuery(hql);
         //List resultList = q.list();
          System.out.println("Usunieto");
         

session.getTransaction().commit();
        session.close();
        sessionFactory.close();
    }
    
 
    
    
    public void AddResult(){
        System.out.println("Rozpoczeto liczenie czasu. Aby zakończyc nacisnij 0 !");
        Calendar cal = Calendar.getInstance();
    	cal.getTime();
    	SimpleDateFormat sdf = new SimpleDateFormat("YYYY:MM:dd - HH:mm:ss");
        //Date d = cal.getTime();
    	
        long start = System.currentTimeMillis();
        //Thread.sleep(10);
        Scanner zapis = new Scanner(System.in); 
        int z = zapis.nextInt();
        if(z==0)
         {
            long stop = System.currentTimeMillis();
            double czas=stop-start;
            czas=czas/1000;
            System.out.println(stop - start);
            Date d = new Date();
            d.setHours(0);
            d.setMinutes(0);
            d.setSeconds((int) czas);
            System.out.println( sdf.format(cal.getTime()) );
            //session.save(d);
         }
        else
        {
            System.out.println("zly znak!");
        }
        
        
    }
    public void PrintResult(){
        buildSessionFactory();
        Session session = getSessionFactory().openSession();
        session.beginTransaction();
        
        Scanner zapis = new Scanner(System.in);
        System.out.print("Podaj Id:");
        int Id = zapis.nextInt();
        
        String hql = "SELECT id_result, id_user, date,time FROM Result WHERE id_user LIKE" + Id;
        Query q = session.createSQLQuery(hql);
        List resultList = q.list();
        User u= (User) resultList.get(Id-1);
        System.out.println(resultList);
        for (Object o : resultList) {
            Result r = (Result) o;
            System.out.println(r.getDate());
            System.out.println(r.getTime());
          
            
        }
        
    }
}
