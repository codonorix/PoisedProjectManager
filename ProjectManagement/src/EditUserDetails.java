import java.sql.*;
import java.util.Scanner;

public class EditUserDetails {
    public static void editUserDetails() {
        try {
            Connection connection = DriverManager.getConnection(                    //Creates a connection using the driver manager to try get a connection to our ebookstore database
                    "jdbc:mysql://localhost:3306/poisepms?useSSL=false",
                    "logan",                                                //Username and password used to login to the DB
                    "pieisyum"
            );

            Statement statement = connection.createStatement();                     //Creates a connection statement
            ResultSet results;                                                      //Creates a resultset
            int rowsAffected;
            Scanner input = new Scanner(System.in);                                 //Creates an input scanner

            System.out.println("Please enter the persons ID");                      //Requests the users ID
            int id = input.nextInt();                                               //Gets the int

            results = statement.executeQuery("SELECT * FROM person WHERE person_id = " + id);   //Selects the person with that id


                //====================================| PRINT OUT DETAILS |====================================\\
            while (results.next()) {
                String output = ("\n+--------------| Contractor Data  |--------------+");   //Prints the details of the person
                output += ("\nThe Persons ID is\t\t\t\t| " + results.getInt("person_id"));
                output += ("\nPersons Name\t\t\t\t\t\t| " + results.getString("person_name"));
                output += ("\nPersons Surname\t\t\t\t| " + results.getString("person_sname"));
                output += ("\nPersons Tel num\t\t\t| " + results.getString("tel_num"));
                output += ("\nPersons email is\t| " + results.getString("email"));
                output += ("\nPersons address is\t\t\t| " + results.getString("address"));
                output += ("\nPersons role is\t\t\t| " + results.getString("role"));

                System.out.println(output);
            }                                                            //Adds a position so we can easily tell what line it's on

            //====================================| EDIT CONTRACTOR |====================================\\

            System.out.println("+--------------[PICK AN OPTION]-------------+");                            //Prints out the the choices the user can make
            System.out.println("1 - Change Phone Number");
            System.out.println("2 - Change email");
            System.out.println("3 - Change address");
            System.out.println("+-------------------------------------------+");
            input = new Scanner(System.in);                                                 //Clears the scanner
            String editChoice = input.nextLine();                                           //Waits for the user to enter the new text

            //====================================| CHANGE PHONE NUMBER |====================================\\

            if (editChoice.equals("1")) {                                       //If the edit choice is equal to 1 we run this code
                do
                {                                                                 //We create a do/while loop to keep the loop going
                    try {                                                         //We create a try/catch block so we don't crash the program
                        System.out.println("Please enter the customer telephone number: ");
                        String PhoneCheck = input.nextLine();                                 //Gets the users phone number

                        if (String.valueOf(PhoneCheck).length() == 9 || String.valueOf(PhoneCheck).length() == 10) {    //Cehcks if the value of their phone number is equal to 9 digets (0 not added) or equal to 10 (0 was added)
                            input = new Scanner(System.in);         //If the if matches, we empty our scanner

                            rowsAffected = statement.executeUpdate( //changes the telnum section of the person we edit
                                    "UPDATE person SET tel_num = " + "'" + PhoneCheck + "'" + " WHERE person_id=" + id
                            );

                            break;                                  //and break out the loop

                        } else                                       //Else we'll tell the user to enter a 9 digit phone number
                            System.out.println("Please enter a 9 digit phone number.");

                    } catch (Exception ex) {                        //If the user types anything other than a full number we'll get a crash
                        System.out.println("[ERROR] your phone number can only have numbers.");
                        input = new Scanner(System.in);             //We'll send them the error and clear our input variable
                    }
                } while (true);

                //====================================| CHANGE EMAIL |====================================\\
            } else if (editChoice.equals("2")) {                    //If the user selects option 2
                System.out.println("Please enter the new email:");  //We request the new email
                String newEmail = input.nextLine();                 //Waits for the new data

                rowsAffected = statement.executeUpdate(             //Edits the email section of the person we edit
                        "UPDATE person SET email = " + "'" + newEmail + "'" + " WHERE person_id=" + id
                );
                //====================================| CHANGE ADDRESS |====================================\\
            } else if (editChoice.equals("3")) {                    //If the user selects option 3
                System.out.println("Please enter the new address"); //Requests the user to enter their new address
                String newAddress = input.nextLine();               //Waits for the input

                rowsAffected = statement.executeUpdate(             //Edits the address section of the person we edit
                        "UPDATE person SET address = " + "'" + newAddress + "'" + " WHERE person_id=" + id
                );
            }

            statement.close();          //Closes the database
            connection.close();

            //====================================| WRITE DATA |====================================\\

        } catch (SQLException ex) {
            ex.printStackTrace();
        }


    }
}
