package data;
import java.sql.*;
/*
	Run with:
	java -cp postgresql-42.2.1.jar:. DBTest
*/

public class DBTest {

    public static void main(String[] args) {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (java.lang.ClassNotFoundException e) {
            System.out.println(e);
        }


        String url = "jdbc:postgresql://horton.elephantsql.com:5432/sywigydc";
        String username = "sywigydc";
        String password = "U61YX4R8TCresQKKjJvTpcot4dQubxdV";





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
    }

}
