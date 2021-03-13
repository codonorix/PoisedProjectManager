import java.util.Scanner;

public class Person {

    String position;
    String name;
    String surname;
    int telNum;
    String email;
    String address;

    public Person(String position, String name, String surname, int telNum, String email, String address) {

        this.position = position;
        this.name = name;
        this.surname = surname;
        this.telNum = telNum;
        this.email = email;
        this.address = address;

        Scanner input = new Scanner(System.in);

        //=================================| Name |=================================\\
        System.out.println("Please enter the persons name: ");           //Each of these lines asks a question and then waits for the users input
        name = input.nextLine();                                          //We store the user input in their own variable which will be used later
        setName(name);

        //=================================| Surname |=================================\\
        System.out.println("Please enter the persons surname: ");           //Each of these lines asks a question and then waits for the users input
        surname = input.nextLine();                                          //We store the user input in their own variable which will be used later
        setSurname(surname);

        //=================================| Tel Num |=================================\\
        do {                                                              //We create a do/while loop to keep the loop going
            try {                                                         //We create a try/catch block so we don't crash the program
                System.out.println("Please enter the persons telephone number: ");
                telNum = input.nextInt();                                 //Gets the users phone number

                if (String.valueOf(telNum).length() == 9 || String.valueOf(telNum).length() == 10) {    //Cehcks if the value of their phone number is equal to 9 digets (0 not added) or equal to 10 (0 was added)
                    input = new Scanner(System.in);         //If the if matches, we empty our scanner
                    setTelNum(telNum);
                    break;                                  //and break out the loop

                }else                                       //Else we'll tell the user to enter a 9 digit phone number
                    System.out.println("Please enter a 9 digit phone number.");

            } catch (Exception ex) {                        //If the user types anything other than a full number we'll get a crash
                System.out.println("[ERROR] your phone number can only have numbers.");
                input = new Scanner(System.in);             //We'll send them the error and clear our input variable
            }
        } while (true);                                     //Will keep the loop running even if the user enters a wrong value


        //=================================| Email |=================================\\
        System.out.println("Please enter the persons e-mail: ");
        email = input.nextLine();                           //Gets the user to enter their email
        setEmail(email);

        //=================================| Address |=================================\\
        System.out.println("Please enter the persons address: ");
        address = input.nextLine();                     //Gets the user to enter their address
        setAddress(address);

        //=================================| output |=================================\\
        String output = ("Their name is: " + name + "\n");              //Takes all the data we've written into the
        output += ("Their surname is: " + surname + "\n");              //Variables and prints it out in a nice
        output += ("Position is: " + position + "\n");                  //format
        output += ("Their telephone number is: " + telNum + "\n");
        output += ("Their e-mail is: " + email + "\n");
        output += ("Their address is: " + address + "\n");

        System.out.println(output);

    }
    //=======================================================| Getters and Setters |=======================================================\\
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getTelNum() {
        return telNum;
    }

    public void setTelNum(int telNum) {
        this.telNum = telNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
