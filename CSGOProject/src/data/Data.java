package data;
import Interfaces.IData;
import java.sql.*;
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
    private String[] data;
    
    public Data()
            
    {
        // Hver gang vi opretter et objekt af vores data-lag, vil vi have at den opretter
        //forbindelse til vores DB som det første via try/catch.
        try {
            Class.forName("org.postgresql.Driver");
        } catch (java.lang.ClassNotFoundException e) {
            System.out.println(e);
        }
        
    }
    
    
 public String[] getSomething()
 {
        try {
            Connection db = DriverManager.getConnection(url, username, password);

            Statement st = db.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Persons");
            while (rs.next()) {

                System.out.print(rs.getString(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t" + rs.getString(4) + "\n");

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
