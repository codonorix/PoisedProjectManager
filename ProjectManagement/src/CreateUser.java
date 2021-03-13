import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class CreateUser {
    public static void createuser() {

        try {
            Connection connection = DriverManager.getConnection(                    //Creates a connection using the driver manager to try get a connection to our ebookstore database
                    "jdbc:mysql://localhost:3306/poisepms?useSSL=false",
                    "logan",                                                //Username and password used to login to the DB
                    "pieisyum"
            );

            int rowsAffected;                                                       //Creates the rowaffected so we can edit the database
            Statement statement = connection.createStatement();                     //Creates a connection statment to allow us to change data

            System.out.println("+--------------------+");                           //Tells the used to select what staff
            System.out.println("1 - Architect");                                    //memeber they want to create
            System.out.println("2 - Structural Architect");
            System.out.println("3 - Project Manager");
            System.out.println("+--------------------+");

            Scanner input = new Scanner(System.in);                                 //Creates a new scanner called input
            String choice = input.nextLine();                                       //Waits for the users input


            if (choice.equals("1")) {                                               //If they select 1 we create a new architect
                System.out.println("+----------------| Architect Data |---------------+");
                Person architect = new Person("Architect", "", "", 0, "", "");  //Creates a new person called "contractor" with default values

                statement.executeUpdate(                                                                             //Creates the statment to execture the update to the database
                        "INSERT INTO person (person_name, person_sname, tel_num, email, address, role) VALUES ("     //Inserts a new person into the database using all the below values
                                + "'" + architect.getName() + "',"
                                + "'" + architect.getSurname() + "',"
                                + "'" + architect.getTelNum() + "',"
                                + "'" + architect.getEmail() + "',"
                                + "'" + architect.getAddress() + "',"
                                + "'" + architect.getPosition() + "'" + ")"
                );


            } else if (choice.equals("2")) {
                System.out.println("+----------------| Structural Architect |---------------+");
                Person structural_architect = new Person("Structural Architect", "", "", 0, "", "");  //Creates a new person called "contractor" with default values

                statement.executeUpdate(                                                                             //Refer to architect creation, same system
                        "INSERT INTO person (person_name, person_sname, tel_num, email, address, role) VALUES ("
                                + "'" + structural_architect.getName() + "',"
                                + "'" + structural_architect.getSurname() + "',"
                                + "'" + structural_architect.getTelNum() + "',"
                                + "'" + structural_architect.getEmail() + "',"
                                + "'" + structural_architect.getAddress() + "',"
                                + "'" + structural_architect.getPosition() + "'" + ")"
                );


            } else if (choice.equals("3")) {
                System.out.println("+----------------| Project Manager |---------------+");
                Person project_manager = new Person("Project Manager", "", "", 0, "", "");

                statement.executeUpdate(                                                                             //Please refer to the architect creation, same system.
                        "INSERT INTO person (person_name, person_sname, tel_num, email, address, role) VALUES ("
                                + "'" + project_manager.getName() + "',"
                                + "'" + project_manager.getSurname() + "',"
                                + "'" + project_manager.getTelNum() + "',"
                                + "'" + project_manager.getEmail() + "',"
                                + "'" + project_manager.getAddress() + "',"
                                + "'" + project_manager.getPosition() + "'" + ")"
                );

            }
            statement.close();          //Closes the database
            connection.close();

        } catch (SQLException ex) {     //If there is any error with the DB we print out a full error
            ex.printStackTrace();
        }
    }
}
