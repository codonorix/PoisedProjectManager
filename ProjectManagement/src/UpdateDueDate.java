import java.sql.*;
import java.util.Scanner;

public class UpdateDueDate {
    public static void updateduedate() {

        try {
            Connection connection = DriverManager.getConnection(                    //Creates a connection using the driver manager to try get a connection to our ebookstore database
                    "jdbc:mysql://localhost:3306/poisepms?useSSL=false",
                    "logan",                                                //Username and password used to login to the DB
                    "pieisyum"
            );

            Scanner input = new Scanner(System.in);                                 //Creates a new scanner
            Statement statement = connection.createStatement();                     //Creates a new connection statment
            ResultSet results;                                                      //Creates new results
            int rowsAffected;                                                       //Creates the rowsaffected

            System.out.println("Please enter the project ID");                      //Promts the user to enter the project ID they wish to edit
            String projID = input.nextLine();                                       //Waits for the users input

            //========================| DUE DATE|========================\\
            //                                                            \\
            //========================|  DAY DUE  |========================\\
            System.out.println("Please enter the day this project is due: ");   //Requests the user to enter the day they want the project due
            input = new Scanner(System.in);                                     //Creates a new scanner to empty out and stored data from before
            String dayDue = input.nextLine();                                   //Waits for user input

            //========================| MONTH DUE |========================\\
            System.out.println("Please enter the month it's due (as a number, eg January = 1)"); //Tells the user to enter the month it's due
            input = new Scanner(System.in);                                                     //Clears the scanner again
            String monthDue = input.nextLine();                                                 //Waits for the user to enter their input

            //========================| YEAR DUE |========================\\
            System.out.println("Please enter the year it's due:");                              //Tells the user to enter the year it's due
            input = new Scanner(System.in);                                                     //Clears the scanner
            String yearDue = input.nextLine();                                                  //Waits for the users input.

            //========================| DEADLINE |========================\\
            String deadline = (dayDue + "/" + monthDue + "/" + yearDue);                               //Creates the deadline by adding all the data and putting it in the format we'll be using in the main clase

            rowsAffected = statement.executeUpdate(                                                             //Changes the rows so we can do an update
                    "UPDATE project SET deadline= " + "'" + deadline + "'" + " WHERE project_id =" + projID  //Updates the books title where the ID matches the ID the user entered at the start
            );

            statement.close();          //Closes the the database
            connection.close();

        } catch (SQLException ex) {                         //If there is an error with the DB
            ex.printStackTrace();                           //We print a stacktrace error
        }
    }
}

