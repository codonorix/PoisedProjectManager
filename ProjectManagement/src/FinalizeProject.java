import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class FinalizeProject {
    public static void finalizeProject() {
        try {
            Connection connection = DriverManager.getConnection(                    //Creates a connection using the driver manager to try get a connection to our ebookstore database
                    "jdbc:mysql://localhost:3306/poisepms?useSSL=false",
                    "logan",                                                //Username and password used to login to the DB
                    "pieisyum"
            );

            Statement statement = connection.createStatement();                     //Creates a connection statement
            ResultSet resultsProject;                                               //Creates a resultSet for our projects
            int rowsAffected;                                                       //Creates rowsAffected variable

            Scanner input = new Scanner(System.in);                                 //Creates a new scanner for input

            System.out.println("Please enter the project ID that you want to complete");    //Tells the user to enter their project ID
            int taskSelect = input.nextInt();                                               //Waits for the user input


            resultsProject = statement.executeQuery("SELECT * FROM project WHERE project_id = " + taskSelect + " AND comp = 'no'"); //We select all projects where the project ID matches the users input and the comp status is no


            while (resultsProject.next()) {                                 //While the database has a next entry is how many times this while loop loops
                String output = ("\n+------------| Project number |------------+");
                output += ("\nThe project number is\t\t\t\t| " + resultsProject.getString("project_id"));
                output += ("\nThe project name is\t\t\t\t\t| " + resultsProject.getString("proj_name"));
                output += ("\nThe house type is\t\t\t\t\t| " + resultsProject.getString("house_type"));
                output += ("\nThe address is\t\t\t\t\t\t| " + resultsProject.getString("address"));
                output += ("\nThe ERF number is\t\t\t\t\t| " + resultsProject.getInt("erf_num"));
                output += ("\nThe total fee is\t\t\t\t\t| " + resultsProject.getFloat("total_fee"));
                output += ("\nThe amount currently paid off is\t| " + resultsProject.getFloat("cur_paid"));
                output += ("\nThe project deadline is\t\t\t\t| " + resultsProject.getString("deadline"));
                output += ("\nIs the project complete\t\t\t\t| " + resultsProject.getString("comp") + "\n");

                System.out.println(output);                                                     //We print out the above data


                //============================| Date |============================\\
                SimpleDateFormat simpleDate = new SimpleDateFormat("dd/MM/yyyy");               //Creates a date format
                Date date = new Date();                                                                 //Creates a new date
                String datePrint = simpleDate.format(date);                                             //Formats the date

                rowsAffected = statement.executeUpdate(                                                             //Changes the rows so we can do an update
                        "UPDATE project SET comp= " + " 'Yes'  WHERE project_id =" + taskSelect);  //Updates the books title where the ID matches the ID the user entered at the start


                rowsAffected = statement.executeUpdate(                                                          //Changes date completed to the day the project was finalized
                        "UPDATE project SET comp_date= " + "'" + datePrint + "'" + " WHERE project_id =" + taskSelect);

                resultsProject.close(); //We then close the database

                resultsProject = statement.executeQuery("SELECT * FROM project WHERE project_id = " + taskSelect + " AND comp = 'yes'");    //We then search for all rows where the project ID matches the input and is deemed completed

                while (resultsProject.next()) {                             //Wthile that has a next result
                    //============================| Totals |============================\\
                    float totalDue = resultsProject.getFloat("total_fee");
                    float totalPaid = resultsProject.getFloat("cur_paid");    //Then we get position 6 (the total paid) and set it to a double
                    float totalOutStanding = totalDue - totalPaid;                 //Then we get the total outstanding which is the totalDue - TotalPAid
                    float totalToClient = totalPaid - totalDue;                    //Then we get the total the client gets by reversing the above

                    //============================| WRITE NEW DATA |============================\\


                    //While the fileScanner has a next line
                    if (totalDue >= totalPaid) {                        //If the totalDue is greater than or equal to the total paid
                        System.out.println("\n+====================================+");  //Prints all the data
                        System.out.println("\nProject Name | " + resultsProject.getString("proj_name"));
                        System.out.println("\nProject Number | " + resultsProject.getInt("project_id"));
                        System.out.println("\n+====================================+");
                        System.out.println("\nTotal Due | " + totalDue + "         +");
                        System.out.println("\nTotal Paid | " + totalPaid + "       +");
                        System.out.println("\n-------------------------------------+");
                        System.out.println("\nTotal Outsanding | " + totalOutStanding);
                        System.out.println("\n+====================================+");
                    } else {
                        System.out.println("\n+====================================+");  //Else this will be printed
                        System.out.println("\nProject Name | " + resultsProject.getString("proj_name"));
                        System.out.println("\nProject Number | " + resultsProject.getInt("project_id"));
                        System.out.println("\n+====================================+");
                        System.out.println("\nProject has been completely paid. Thank you :)");
                        System.out.println("\nTotal due to client: " + totalToClient);
                        System.out.println("\n+====================================+");
                    }
                }
                statement.close();          //Closes the database
                connection.close();
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}


