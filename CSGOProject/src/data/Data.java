package data;
import Interfaces.IData;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/*
	Run with:
	java -cp postgresql-42.2.1.jar:. DBTest
*/

public class Data implements IData 
{
    
    //Hver gang vi opretter et objekt af denne klasse (starter vores program op), 
    // vil vi have at det er vores DB vi opretter forbindelse til.
    // derfor får den vores brugernavn og pass som private strings.
    
    private String url = "jdbc:postgresql://horton.elephantsql.com:5432/sywigydc";
    private String username = "sywigydc";
    private String password = "U61YX4R8TCresQKKjJvTpcot4dQubxdV";
    
    Connection db;
        
    
    public Data()
            
    {
        
        //Tjek at vi har vores SQL driver
        try {
            Class.forName("org.postgresql.Driver");
        } catch (java.lang.ClassNotFoundException e) {
            System.out.println(e);
        }
        
        // Hver gang vi opretter et objekt af vores data-lag, vil vi have at den opretter
        //forbindelse til vores DB som det første via try/catch.
        try {
            db = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    
    //Methode til at hente data fra database i StringArray
    @Override
 public ArrayList<String> getData(String query)
    {
        ArrayList<String> data = new ArrayList<>();
        
        try {
            Statement st = db.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Persons");
            while (rs.next()) {
                int i = 1;
                while (rs.getString(i) != null){
                    System.out.print(rs.getString(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t" + rs.getString(4) + "\n");
                }
                // push test
            }
            rs.close();
            st.close();


        } catch (Exception e) {
            System.out.println(e);
        }
        
        return data;
        
    }

    @Override
    public ArrayList<String> getCoachInfo() 
    {
        
        ArrayList<String> data = new ArrayList<>();
        
            try {
                Statement st = db.createStatement();
                
                // Vælg/Vis Persons.navn, Persons.nickname, part_of.teamname
                // Fra Persons Natural join med part_of 
                // (Flet sammen med fælles kolonne, og vis de valgte kolonner)
                //Hvor Persons.role = 'coach'
                ResultSet rs = st.executeQuery("SELECT Persons.name, Persons.nickname, part_of.teamname "
                                            + "FROM Persons NATURAL JOIN part_of "
                                            + "WHERE Persons.role = 'coach'");
            while (rs.next()) {
                int i = 1;
                while (rs.getString(i) != null){
                    System.out.print(rs.getString(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t" + rs.getString(4) + "\n");
                }
                // push test
            }
            rs.close();
            st.close();


        } catch (Exception e) {
            System.out.println(e);
        }
        
        return data;    
    }


}
