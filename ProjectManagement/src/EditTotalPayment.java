import java.sql.*;
import java.util.Scanner;

public class EditTotalPayment {
    public static void editTotalPayment() {                                         //Creates the editTotalPayment method

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

            System.out.printf("Please enter the new total paid");                   //Tells the user to enter the new total paid
            input = new Scanner(System.in);                                         //Clears the scanner
            int newTotal = input.nextInt();                                         //Waits for the user input

            rowsAffected = statement.executeUpdate(                                                             //Changes the rows so we can do an update
                    "UPDATE project SET cur_paid= " + "'" + newTotal + "'" + " WHERE project_id =" + projID  //Updates the books title where the ID matches the ID the user entered at the start
            );

            statement.close();          //Closes the database
            connection.close();

        } catch (SQLException ex) {                         //If there is an error with the DB
            ex.printStackTrace();                           //We print a stacktrace error
        }
    }
}
