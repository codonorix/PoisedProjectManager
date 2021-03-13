import java.sql.*;

public class DisplayIncompProj {
    public static void displayIncomProj() {
        try {
            Connection connection = DriverManager.getConnection(                    //Creates a connection using the driver manager to try get a connection to our ebookstore database
                    "jdbc:mysql://localhost:3306/poisepms?useSSL=false",
                    "logan",                                                //Username and password used to login to the DB
                    "pieisyum"
            );

            Statement statement = connection.createStatement();                     //Creates a connection statement
            ResultSet results;                                                      //Creates a resultset
            int rowsAffected;

            results = statement.executeQuery("SELECT * FROM project WHERE comp = 'no'");    //Looks for the in the project DB where the 'comp' feild is 'no'

            while (results.next()) {                                                    //Creates a loop while the result has a next value
                System.out.println(                                                     //Prints the line
                        results.getInt("project_id")                         //Gets the int of the project ID
                                + ", " + results.getString("proj_name")     //Gets the string of the project name
                                + ", " + results.getString("house_type")    //Gets the string of the house type
                                + ", " + results.getString("address")       //Gets the string of the address
                                + ", " + results.getInt("erf_num")          //Gets the int of the erf number
                                + ", " + results.getInt("total_fee")        //Gets the total fee
                                + ", " + results.getInt("cur_paid")         //Gets the current paid
                                + ", " + results.getString("deadline")      //Gets the deadline
                                + ", " + results.getString("comp")          //Gets the completion status
                );
            }

            statement.close();          //Closes the database
            connection.close();

        } catch (SQLException ex) {    //Sends an error should there be an issue with the DB
            ex.printStackTrace();
        }
    }
}
