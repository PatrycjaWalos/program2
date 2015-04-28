/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package program;

import Baza.Result;
import Baza.User;
import java.text.SimpleDateFormat;
//import HibernateUtil;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.logging.Level;
import net.sf.ehcache.hibernate.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Scanner;
import java.util.Set;
import javax.imageio.spi.ServiceRegistry;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import org.hibernate.criterion.Restrictions;
import org.hibernate.service.ServiceRegistryBuilder;
import org.hibernate.sql.JoinType;

//import org.hibernate.dialect.MySQLDialect
public class Program {

    public static void main(String[] args) throws InterruptedException {
        int znak;
        boolean zadania = true;
         ObslugaBazy baza = new ObslugaBazy();
        Scanner odczyt = new Scanner(System.in); 
        System.out.println("Sudoku - opcje:");

        while (zadania == true) {
            System.out.println("1. Wprowadz nowego uzytkownika");
            System.out.println("2. Wyswietl wszystkich uzytkownikow");
            System.out.println("3. Zaloguj");
            System.out.println("4. Usun uzytkownika");
            System.out.println("5. Zapisz czas gry");
            System.out.println("6. Wyswietl wyniki");
            System.out.println("7. Wyjscie");
            znak = odczyt.nextInt();
            
            switch (znak) {
                case 1: {
                    baza.AddUser();
                    break;
                }

                case 2: {
                    baza.PrintUser();
                    break;
                }
                case 3: {

                    baza.Zaloguj();
                    break;
                }
                case 4: {
                    baza.DeleteUser();
                    break;
                }
                case 5: {
                    baza.AddResult();
                    break;
                }
                case 6: {
                    baza.PrintResult();
                    break;
                }
                case 7: {
                    zadania = false;
                  
            }
                 if (zadania == false) {
                System.out.println("Dziekuje za skorzystanie!");
            }


        
        System.exit(0);
            }
    }
}
}     


