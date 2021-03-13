import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;
import java.util.List;
import java.util.Scanner;

public class PoisedMain {
    public static void main(String[] args) {

        /**
         * Project Management System
         * The project design is to help a construction company
         * manage their projects in an easy to read and simple
         * manner
         *
         * @author Logan2971
         * @version 3.0.0 Alpha Build
         *
         * Change log
         *
         * ► Converted all text file usage into a database useage (goodbye text files!)
         * ► Revamped the main class so that all the selections are part of their own classes/methods for easier readability
         */


        try {                                                                       //Creates a try catch incase we're not able to conntect to the database
            Connection connection = DriverManager.getConnection(                    //Creates a connection using the driver manager to try get a connection to our ebookstore database
                    "jdbc:mysql://localhost:3306/poisepms?useSSL=false",
                    "logan",                                                //Username and password that we created during the documentation because
                    "pieisyum"                                             //I was too lazy to create a new one (hey if it aint broke don't fix it)
            );

            int rowsAffected;                                                       //Creates the rowaffected so we can edit the database
            Statement statement = connection.createStatement();

            Scanner input = new Scanner(System.in);         //Creates a new scanner to get the users input

            while (true) {                                  //While this data is true (which it always is till it's broke)

                System.out.println("+-------------------------------------------+");    //Prints out the user menu
                System.out.println("1 - Create a project");
                System.out.println("2 - Create a user");
                System.out.println("3 - Edit project Due date");
                System.out.println("4 - Edit total payment on a project");
                System.out.println("5 - Edit Persons details");
                System.out.println("6 - Display incomplete projects");
                System.out.println("7 - Display overdue projects");
                System.out.println("8 - Finalise a project");
                System.out.println("9 - Search for a project");
                System.out.println("0 - Exit the program");
                System.out.println("+-------------------------------------------+");

                String selection = input.next();        //Creates a string that gets the users input

                /**
                 * Choice 1 Creates a new project running through
                 * the persons and projects class
                 *
                 * Changes - Moved creation system from main file
                 *
                 * @since version 1
                 */
                //============================| CREATE NEW PROJECT |============================\\
                if (selection.equals("1")) {            //If the user enters number 1 we create a new project
                    Project project = new Project("", "", "", "", 0, 0, 0, "", 0, 0, 0, 0); //We then go into the project class and run all the code in there

                    /**
                     * Choice two allows the user to
                     * update the due date of a project
                     */

                } else if (selection.equals("2")) {         //If the user selects 2
                    CreateUser.createuser();                //Call the createuser class method


                    //============================| UPDATE DUE DATE |============================\\
                } else if (selection.equals("3")) {         //If the user selects numbers 2 we'll update the due date
                    UpdateDueDate.updateduedate();          //We call the update duedate class method


                    /**
                     * Choice three allows the user to edit the total payment
                     * paid to the project.
                     *
                     * Changes
                     * Error handling
                     * File write
                     *
                     * @since Version 1
                     */
                    //============================| EDIT TOTAL PAYMENT |============================\\
                } else if (selection.equals("4")) {             //Checks if the user selected "3" we move to the edit total payment
                    EditTotalPayment.editTotalPayment();        //We call the edittotalpayment class method

                    /**
                     * Option 4 Allows us to edit the contractors details
                     *
                     * Changes
                     * File writing
                     *
                     * @since Version 1
                     */

                    //====================================| UPDATE CONTRACTOR DETAILS |====================================\\
                } else if (selection.equals("5")) {          //Checks if the user entered 4
                    EditUserDetails.editUserDetails();      //Calls the edituserdetails class method

                    //====================================| DISPLAY INCOMPLETED TASKS |====================================\\
                } else if (selection.equals("6")) {             //If the user chose choice 5
                    DisplayIncompProj.displayIncomProj();       //Calls the display incompProj class method

                    /**
                     * Choice six allows the user to show all
                     * over due projects
                     *
                     * @since Version 2
                     */

                    //=============| Over Due Projects |=============\\
                } else if (selection.equals("7")) {             //If the user selects options 6
                    DisplayOverDueProj.displayOverDueProj();    //Calls the overdueproj class method
                    /**
                     * Choice seven allows the user
                     * to finalize a project and
                     * write an invoice
                     *
                     * @since Version 2
                     */

                    //=============| Finalize Projects |=============\\
                } else if (selection.equals("8")) {         //If the choice is "7"
                    FinalizeProject.finalizeProject();      //Calls the dinalizerProject class method

                    /**
                     * Choice eight allows the user
                     * to search for a specific task
                     * in the projects file using the
                     * project name or number
                     *
                     * @since Version 2
                     */
                    //=============| Search for a project |=============\\
                } else if (selection.equals("9")) {         //If the user selections 8
                    SearchForProject.searchForProject();    //Calls the searchForProject class method

                    /**
                     * choice zero allows the user
                     * to exit the program
                     *
                     * @since Version 2
                     */
                } else if (selection.equals("0")) {
                    break;      //If the user selects 0 we break the program
                }


                statement.close();          //Closes the file
                connection.close();

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
