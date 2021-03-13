import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class DisplayOverDueProj {
    public static void displayOverDueProj() {
        try {
            Connection connection = DriverManager.getConnection(                    //Creates a connection using the driver manager to try get a connection to our ebookstore database
                    "jdbc:mysql://localhost:3306/poisepms?useSSL=false",
                    "logan",                                                //Username and password used to login to the DB
                    "pieisyum"
            );

            Statement statement = connection.createStatement();
            ResultSet results;
            int rowsAffected;                                                                        //Gets the position of the line in the file
            Scanner input = new Scanner(System.in);

            System.out.println("Please enter the project ID");                                              //Tells the user to enter their project ID
            int userSearch = input.nextInt();                                                               //Waits for the user input


            results = statement.executeQuery("SELECT * FROM project WHERE project_id = " + userSearch); //Selects the project that the user entered the ID fore

            while (results.next()) {                                                                    //While the result in the DB has a next value

                //============================| Date |============================\\
                SimpleDateFormat simpleDate = new SimpleDateFormat("dd/MM/yyyy");               //Creates a date format
                Date date = new Date();                                                                 //Creates a new date
                String datePrint = simpleDate.format(date);                                             //Formats the date

                Date dueDateCheck = new SimpleDateFormat("dd/MM/yyyy").parse(results.getString("deadline"));    //Gets the date from the database and converts it into a date to do a check

                if (results.getString("comp").contains("no") && date.after(dueDateCheck)) {                          //If data 8 (task completed) is no and the date of the project is before todays date
                    String output = ("\n+------------| Project number |------------+");
                    output += ("\nThe project number is\t\t\t\t| " + results.getString("project_id"));
                    output += ("\nThe project name is\t\t\t\t\t| " + results.getString("proj_name"));
                    output += ("\nThe house type is\t\t\t\t\t| " + results.getString("house_type"));
                    output += ("\nThe address is\t\t\t\t\t\t| " + results.getString("address"));
                    output += ("\nThe ERF number is\t\t\t\t\t| " + results.getString("erf_num"));
                    output += ("\nThe total fee is\t\t\t\t\t| " + results.getString("total_fee"));
                    output += ("\nThe amount currently paid off is\t| " + results.getString("cur_paid"));
                    output += ("\nThe project deadline is\t\t\t\t| " + results.getString("deadline"));
                    output += ("\nIs the project complete\t\t\t\t| " + results.getString("comp") + "\n");

                    System.out.println(output);


                }
                statement.close();          //Closes the database
                connection.close();
            }

        } catch (SQLException | ParseException ex) {
            ex.printStackTrace();                             //If there is an error with the DB we print a full error
        }
    }
}
