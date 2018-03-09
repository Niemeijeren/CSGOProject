/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csgoproject2;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 *
 * @author Linea Hoffmann
 */
public class FXMLDocumentController implements Initializable
{

    private String dburl = "jdbc:postgresql://horton.elephantsql.com:5432/sywigydc";
    private String dbusername = "sywigydc";
    private String dbpassword = "U61YX4R8TCresQKKjJvTpcot4dQubxdV";
    
    @FXML
    private Button allCoaches;
    @FXML
    private Button allWinningTeams;
    @FXML
    private Button listAllTeams;
    @FXML
    private Button listAllTournaments;
    @FXML
    private Button searchCoaches;
    @FXML
    private Button searchAllWiningTeams;
    @FXML
    private Button searchAllTeams;
    @FXML
    private Button searchAllTournaments;
    @FXML
    private TextField textCoach;
    @FXML
    private TextField textWinningTeam;
    @FXML
    private TextField textAllTeam;
    @FXML
    private TextField textTournament;
    @FXML
    private TextArea output;

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        try
        {
            Class.forName("org.postgresql.Driver");
        } catch (java.lang.ClassNotFoundException e)
        {
            System.out.println(e);
        }
    }

    @FXML
    private void listAllCoaches(ActionEvent event)
    {
        String textOutput = "";

        try
        {
            Connection db = DriverManager.getConnection(dburl, dbusername, dbpassword);

            Statement st = db.createStatement();
            String query = "SELECT Persons.name, Persons.nickname, part_of.teamname "
                    + "FROM Persons NATURAL JOIN part_of "
                    + "WHERE Persons.role = 'coach'";
            System.out.println(query);
            //       String query = "SELECT * FROM information_schema.tables";
            ResultSet rs = st.executeQuery(query);
            while (rs.next())
            {
                textOutput += " " + rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3) + "\n";
                System.out.print(rs.getString(1) + " ");
                System.out.print(rs.getString(2) + " ");
                System.out.println(rs.getString(3));

            }
            output.setText(textOutput);
            rs.close();
            st.close();
            db.close();

        } catch (Exception e)
        {
            System.out.println(e);
        }
    }

    @FXML
    private void listAllWinningTeams(ActionEvent event)
    {
        String textOutput = "";

        try
        {
            Connection db = DriverManager.getConnection(dburl, dbusername, dbpassword);

            Statement st = db.createStatement();
            String query
                    = // Inner join tager data fra 2 tabeller, kan også sammenligne
                    // ON HVordan vi skal joine
                    "SELECT Persons.name, part_of.teamname FROM Persons "
                    + "INNER JOIN part_of ON Persons.nickname = part_of.nickname " // Sammenligner Nickname, så vi kan få hold navnet
                    + "INNER JOIN Participates_in ON part_of.teamname = Participates_in.Teamname "
                    + "INNER JOIN Tournaments ON Participates_in.Tournamentname = Tournaments.name "
                    //WHERE Hvad vi skal have ud af data, krav til dataen vi bruger
                    + "WHERE Tournaments.Winner LIKE part_of.teamname "
                    + "GROUP BY persons.name, part_of.teamname";
                    // AND Logisk operator
                    
                    //query = "SELECT DISTINCT(Winner) , 'null' FROM Tournaments"; // Vælger de hold der har vundet. 
                     
            System.out.println(query);
            //       String query = "SELECT * FROM information_schema.tables";
            ResultSet rs = st.executeQuery(query);
            while (rs.next())
            {
                textOutput += rs.getString(1) + " " + rs.getString(2) + "\n";
                System.out.print(rs.getString(1) + " ");

            }
            output.setText(textOutput);
            rs.close();
            st.close();
            db.close();

        } catch (Exception e)
        {
            System.out.println(e);
        }
    }



    @FXML
    private void listAllTeams(ActionEvent event)
    {
        String textOutput = "";

        try
        {
            Connection db = DriverManager.getConnection(dburl, dbusername, dbpassword);

            Statement st = db.createStatement();
            String query
                    = "SELECT part_of.teamname, COUNT(part_of.teamname) FROM persons "
                    + "INNER JOIN part_of ON Persons.nickname = part_of.nickname "
                    
                    //GROUP BY Gruppere data på en eller anden måde, condition vi sætter
                    + "GROUP BY part_of.teamname";
                     
            System.out.println(query);
            //       String query = "SELECT * FROM information_schema.tables";
            ResultSet rs = st.executeQuery(query);
            while (rs.next())
            {
                textOutput += rs.getString(1) + " " + rs.getString(2) + "\n";
                System.out.print(rs.getString(1) + " ");

            }
            output.setText(textOutput);
            rs.close();
            st.close();
            db.close();

        } catch (Exception e)
        {
            System.out.println(e);
        }
        
        
    }


    @FXML
    private void listAllTournaments(ActionEvent event)
    {
        String textOutput = "";

        try
        {
            Connection db = DriverManager.getConnection(dburl, dbusername, dbpassword);

            Statement st = db.createStatement();
            String query
                    = "SELECT name FROM Tournaments"; 
                     
            System.out.println(query);
            //       String query = "SELECT * FROM information_schema.tables";
            ResultSet rs = st.executeQuery(query);
            while (rs.next())
            {
                textOutput += rs.getString(1) + "\n";
                System.out.print(rs.getString(1) + " ");

            }
            output.setText(textOutput);
            rs.close();
            st.close();
            db.close();

        } catch (Exception e)
        {
            System.out.println(e);
        }
        
    }

    @FXML
    private void searchCoaches(ActionEvent event)
    {
        String textOutput = "";

        try
        {
            Connection db = DriverManager.getConnection(dburl, dbusername, dbpassword);

            Statement st = db.createStatement();
            String query = "SELECT Persons.name, Persons.nickname, part_of.teamname "
                    + "FROM Persons NATURAL JOIN part_of "
                    + "WHERE Persons.role = 'coach' "
                    + "AND LOWER(Persons.name) LIKE LOWER('%"+textCoach.getText()+"%')";
            System.out.println(query);
            //       String query = "SELECT * FROM information_schema.tables";
            ResultSet rs = st.executeQuery(query);
            while (rs.next())
            {
                textOutput += rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3) + "\n";
                System.out.print(rs.getString(1) + " ");
                System.out.print(rs.getString(2) + " ");
                System.out.println(rs.getString(3));

            }
            output.setText(textOutput);
            rs.close();
            st.close();
            db.close();

        } catch (Exception e)
        {
            System.out.println(e);
        }
        
        
    }

    @FXML
    private void searchAllWiningTeams(ActionEvent event)
    {
        String textOutput = "";

        try
        {
            Connection db = DriverManager.getConnection(dburl, dbusername, dbpassword);

            Statement st = db.createStatement();
            String query
                    = // Inner join tager data fra 2 tabeller, kan også sammenligne
                    // ON HVordan vi skal joine
                    "SELECT Persons.name, part_of.teamname FROM Persons "
                    + "INNER JOIN part_of ON Persons.nickname = part_of.nickname " // Sammenligner Nickname, så vi kan få hold navnet
                    + "INNER JOIN Participates_in ON part_of.teamname = Participates_in.Teamname "
                    + "INNER JOIN Tournaments ON Participates_in.Tournamentname = Tournaments.name "
                    //WHERE Hvad vi skal have ud af data, krav til dataen vi bruger
                    + "WHERE Tournaments.Winner LIKE part_of.teamname "
                    // AND Logisk operator
                    + "AND LOWER(part_of.teamname) LIKE LOWER('%"+textWinningTeam.getText()+"%') "; //Sammen ligner værdien. 
            System.out.println(query);
            //       String query = "SELECT * FROM information_schema.tables";
            ResultSet rs = st.executeQuery(query);
            while (rs.next())
            {
                textOutput += rs.getString(1) + " " + rs.getString(2) + "\n";
                System.out.print(rs.getString(1) + " ");

            }
            output.setText(textOutput);
            rs.close();
            st.close();
            db.close();

        } catch (Exception e)
        {
            System.out.println(e);
        }

    }

    @FXML
    private void searchAllTeams(ActionEvent event)
    {
        String textOutput = "";

        try
        {
            Connection db = DriverManager.getConnection(dburl, dbusername, dbpassword);

            Statement st = db.createStatement();
            String query
                    = "SELECT part_of.teamname, COUNT(part_of.teamname) FROM persons "
                    + "INNER JOIN part_of ON Persons.nickname = part_of.nickname "
                    + "WHERE LOWER(part_of.teamname) LIKE LOWER('%"+textAllTeam.getText()+"%')"
                    
                    //GROUP BY Gruppere data på en eller anden måde, condition vi sætter, bruger en aggregate function
                    + "GROUP BY part_of.teamname ";
                     
            System.out.println(query);
            //       String query = "SELECT * FROM information_schema.tables";
            ResultSet rs = st.executeQuery(query);
            while (rs.next())
            {
                textOutput += rs.getString(1) + " " + rs.getString(2) + "\n";
                System.out.print(rs.getString(1) + " ");

            }
            output.setText(textOutput);
            rs.close();
            st.close();
            db.close();

        } catch (Exception e)
        {
            System.out.println(e);
        }
        
        
    }

    @FXML
    private void searchAllTournaments(ActionEvent event)
    {
        String textOutput = "";

        try
        {
            Connection db = DriverManager.getConnection(dburl, dbusername, dbpassword);

            Statement st = db.createStatement();
            String query
                    = "SELECT Tournaments.name, COUNT( Participates_in.Teamname) FROM Tournaments "
                    + "INNER JOIN Participates_in ON Tournaments.name =  Participates_in.Tournamentname "
                    + "GROUP BY Tournaments.name " // Sortere data
                    // HAVING, lidt ligesom WHERE Claus. Foregår altid efter gruop by, data skal altid være grupperet.
                    // CAST, Typecasting
                    + "HAVING COUNT( Participates_in.Teamname) >= CAST('"+textTournament.getText()+"' AS INTEGER)";
                     
            System.out.println(query);
            //       String query = "SELECT * FROM information_schema.tables";
            ResultSet rs = st.executeQuery(query);
            while (rs.next())
            {
                textOutput += rs.getString(1) + " " +rs.getString(2)+"\n";
                System.out.print(rs.getString(1) + " ");

            }
            output.setText(textOutput);
            rs.close();
            st.close();
            db.close();

        } catch (Exception e)
        {
            System.out.println(e);
        }
    }

}
