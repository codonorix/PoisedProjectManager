import java.sql.*;
import java.util.Scanner;

public class SearchForProject {
    public static void searchForProject() {

        try {
            Connection connection = DriverManager.getConnection(                    //Creates a connection using the driver manager to try get a connection to our ebookstore database
                    "jdbc:mysql://localhost:3306/poisepms?useSSL=false",
                    "logan",                                                //Username and password used to login to the DB
                    "pieisyum"
            );

            Statement statement = connection.createStatement();                     //Creates a connection statement
            ResultSet results;                                                      //Creates a resultset
            int rowsAffected;

            Scanner input = new Scanner(System.in);

            System.out.println("Please enter the project number OR project name");              //Asks the user to enter the project name or number
            input = new Scanner(System.in);                                                     //Clears the scanner
            int userSearch = input.nextInt();                                               //Waits for the user input

            results = statement.executeQuery("SELECT * FROM project WHERE project_id = " + userSearch);

            while (results.next()) {                                 //While the fileScanner has a next line

                    String output = ("\n+------------| Project number |------------+");                             //Prints the project data
                    output += ("\nThe project number is\t\t\t\t| " + results.getString("project_id"));
                    output += ("\nThe project name is\t\t\t\t\t| " + results.getString("proj_name"));
                    output += ("\nThe house type is\t\t\t\t\t| " + results.getString("house_type"));
                    output += ("\nThe address is\t\t\t\t\t\t| " + results.getString("address"));
                    output += ("\nThe ERF number is\t\t\t\t\t| " + results.getInt("erf_num"));
                    output += ("\nThe total fee is\t\t\t\t\t| " + results.getInt("total_fee"));
                    output += ("\nThe amount currently paid off is\t| " + results.getInt("cur_paid"));
                    output += ("\nThe project deadline is\t\t\t\t| " + results.getString("deadline"));
                    output += ("\nIs the project complete\t\t\t\t| " + results.getString("comp") + "\n");

                    System.out.println(output);


            }
            statement.close();          //Closes the database
            connection.close();

        } catch (SQLException ex) {
            ex.printStackTrace();       //If there is an error with the DB we print a full error
        }
    }
}
