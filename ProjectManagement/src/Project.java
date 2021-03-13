import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Project {

    String projectNum;      //Variables needed to produce the project and create getters and setters
    String projectName;
    String houseType;
    String address;
    int erfNum;
    double totalFee;
    double currentPaid;
    String deadline;
    int customer_id;
    int architect_id;
    int structualarci_id;
    int projectmanager_id;


    public Project(String projectNum, String projectName, String houseType, String address, int erfNum, double totalFee, double currentPaid, String deadline, int customer_id, int architect_id, int structualarci_id, int projectmanager_id) {

        try {
            Connection connection = DriverManager.getConnection(                    //Creates a connection using the driver manager to try get a connection to our ebookstore database
                    "jdbc:mysql://localhost:3306/poisepms?useSSL=false",
                    "logan",                                                //Username and password used to login to the DB
                    "pieisyum"
            );

            int rowsAffected;                                                       //Creates the rowaffected so we can edit the database
            Statement statement = connection.createStatement();

            this.projectNum = projectNum;       //Our constructor class, this gets all the variables from above which will help us
            this.projectName = projectName;     //Create the project when the questions are asked
            this.houseType = houseType;
            this.address = address;
            this.erfNum = erfNum;
            this.totalFee = totalFee;
            this.currentPaid = currentPaid;
            this.deadline = deadline;
            this.customer_id = customer_id;
            this.architect_id = architect_id;
            this.structualarci_id = structualarci_id;
            this.projectmanager_id = projectmanager_id;

            Scanner input = new Scanner(System.in); //Creates a new scanner which we'll be using to get user inputs


            System.out.println("+----------------| Customer Data |---------------+");
            Person customer = new Person("Customer", "", "", 0, "", "");      //Creates a new person called "customer" with default values
            String customerSurname = customer.getSurname();

            statement.executeUpdate(                                                                             //Creates the statment to execture the update to the database
                    "INSERT INTO person (person_name, person_sname, tel_num, email, address, role) VALUES ("
                            + "'" + customer.getName() + "',"
                            + "'" + customer.getSurname() + "',"
                            + "'" + customer.getTelNum() + "',"
                            + "'" + customer.getEmail() + "',"
                            + "'" + customer.getAddress() + "',"
                            + "'" + customer.getPosition() + "'" + ")"
            );


            System.out.println("+--------------| Project Information |--------------+");
            System.out.println();

            System.out.println("Please enter the type of house it will be: ");                  //Same system here, we ask for the house type
            houseType = input.nextLine();                                                       //We then set the house type to the users input

            System.out.println("Please enter the project name (Leave blank for auto generate): ");  //We request the user to enter the project name (however, if they leave it blank we auto generate a project name)
            projectName = input.nextLine();                                                         //Waits for the users input

            if (projectName.equals("")) {                                                       //If the user does not enter a value
                projectName = (customerSurname + " " + houseType);                              //We then set the project name to the customers surname and add the house type the project is
            }


            System.out.println("Please enter the address the house will be built: ");           //Requests the user to enter the address of the house
            address = input.nextLine();                                                         //Gets the users input


            //========================| ERF NUMBER |========================\\
            do {                                                            //Creates a do/while loop
                try {                                                       //Creates a try/catch block
                    System.out.println("Please enter the erf Number: ");    //Gets the user input for an erf number
                    erfNum = input.nextInt();
                    break;                                                  //If it's a valid number it breaks out the loop
                } catch (Exception ex) {                                    //Else it will catch the error
                    System.out.println("You did not enter a valid number, please try again.");
                    input = new Scanner(System.in);
                }
            } while (true);                                                 //It will do all this while true

            //========================| Total Fee |========================\\
            do {                                                            //Refer to ERF number [Same concept]
                try {
                    System.out.println("Please enter the total fee: ");
                    totalFee = input.nextDouble();
                    break;
                } catch (Exception ex) {
                    System.out.println("You did not enter a valid number, please try again.");
                    input = new Scanner(System.in);
                }
            } while (true);

            //========================| Current Paid |========================\\
            do {                                                                //Refer to ERF number [Same concept]
                try {
                    System.out.println("Please enter how much has currently been paid: ");
                    currentPaid = input.nextDouble();
                    break;

                } catch (Exception ex) {
                    System.out.println("You did not enter a valid number, please try again.");
                    input = new Scanner(System.in);
                }
            } while (true);

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
            deadline = (dayDue + "/" + monthDue + "/" + yearDue);                               //Creates the deadline by adding all the data and putting it in the format we'll be using in the main clase

            //========================| COMPLETED |========================\\
            String completed = "no";                                            //By default projects are not completed and auto make it incompleted

            //======================| CLIENT IDS |========================\\    //All the data below requests the staff
            System.out.println("Please enter the client ID (Generated in DB)"); //members IDs so we're able to see which
            input = new Scanner(System.in);                                     //staff members are part of the project
            customer_id = input.nextInt();

            System.out.println("Please enter the architect ID");
            input = new Scanner(System.in);
            architect_id = input.nextInt();

            System.out.println("Please enter the Structual architect ID");
            input = new Scanner(System.in);
            structualarci_id = input.nextInt();

            System.out.println("Please enter the project Managers ID");
            input = new Scanner(System.in);
            architect_id = input.nextInt();



            statement.executeUpdate(                                                                             //Creates the statment to execture the update to the database
                    "INSERT INTO project (proj_name, house_type, address, erf_num, total_fee, cur_paid, deadline, comp, customer_id, architect_id,  structualarci_id, projectmanager_id) VALUES ("
                            + "'" + projectName + "',"
                            + "'" + houseType + "',"
                            + "'" + address + "',"
                            + erfNum + ","
                            + totalFee + ","
                            + currentPaid + ","
                            + "'" + deadline + "',"
                            + "'" + completed + "',"
                            + customer_id + ","
                            + architect_id + ","
                            + structualarci_id + ","
                            + projectmanager_id + ")"
            );

            statement.close();          //Closes the database
            connection.close();

        } catch (SQLException ex) {                    //If there is an issue with the database we print a full error
            ex.printStackTrace();
        }
    }
}