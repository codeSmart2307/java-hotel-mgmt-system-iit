
/**
 *
 * @author Dinuka Raneesh Anton Gomez
 * @studentID 2016087
 * @uowID 16266986
 * @module PP02
 * @group A
 * @semester 02
 * @coursework# 01
 * @level 04
 */
//OBJECT ARRAY [MAIN CLASS - Filename : Main.java]
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static Scanner input; //New Scanner object
    private static String[] hotelRoom;//Declared Object array as a static variable so that it can be called from anywhere in the class
    private static String name; //Variable to hold name of customer
    private static String userInput; //Variable to hold user input from stdin
    private static String fileName; //Variable to hold user declared filename to red and write hotel data

    public static void main(String[] args) throws Exception {

        input = new Scanner(System.in);

        System.out.println("Priming Initialization Process...");

        hotelRoom = new String[10];
        for (int room = 0; room < hotelRoom.length; room++) {
            hotelRoom[room] = "empty";
        }

        System.out.println("Initialization Complete!");
        System.out.println("System running at Peak Performance...\n");

        do {
            //Main Menu for Hotel Program
            System.out.println("\n            Hotel Reservation Program");
            System.out.println("===================================================");
            System.out.println("V: View all rooms");
            System.out.println("A: Add customer to a room");
            System.out.println("E: Display empty rooms");
            System.out.println("D: Delete customer from a room");
            System.out.println("F: Find room from customer name");
            System.out.println("S: Store program data in to file");
            System.out.println("L: Load program data from file");
            System.out.println("O: View rooms alphabetically by name");
            System.out.println("X: Exit");

            System.out.print("\nSelect Option: ");
            userInput = input.next().toLowerCase(); //Assigning user input to a variable and converting it to lower case characters

            //Using a switch case for the user input, specific methods are called to serve the user's needs
            switch (userInput) {
                case "v":
                    System.out.println("\n            All Rooms");
                    System.out.println("--------------------------------------------------");
                    viewRooms(); //Method to view all rooms
                    break;
                case "a":
                    System.out.println("\n            Add Customer");
                    System.out.println("--------------------------------------------------");
                    addCustomer(); //Method to add a customer to a room
                    break;
                case "e":
                    System.out.println("\n            Empty Rooms");
                    System.out.println("--------------------------------------------------");
                    emptyRooms(); //Method to display all empty rooms
                    break;
                case "d":
                    System.out.println("\n            Delete Customer");
                    System.out.println("--------------------------------------------------");
                    System.out.print("Name of customer to be deleted: ");
                    name = input.next();
                    deleteCustomer(name);
                    //Customer name is passed as an argument to the method so that the particular room with that name can be reinitialized
                    break;
                case "f":
                    System.out.println("\n            Find Room");
                    System.out.println("--------------------------------------------------");
                    System.out.print("Name of customer: ");
                    name = input.next();
                    System.out.println("Customer " + name + "'s room: " + (findRoomByName(name) + 1));/*Customer name is passed as an argument 
                                                                                                       *to the method so that the particular room with that name can be retrieved*/
                    break;
                case "s":
                    System.out.println("\n            Writing to File");
                    System.out.println("--------------------------------------------------");
                    writeToFile(); //Method to write room details to user defined file
                    break;
                case "l":
                    System.out.println("\n            Reading From File");
                    System.out.println("--------------------------------------------------");
                    readHotelFromFile(); //Method to read and display room details from user defined file
                    break;
                case "o":
                    System.out.println("\n         Rooms in Alphabetical Order");
                    System.out.println("--------------------------------------------------");
                    roomsInOrder();//Method to sort room owners in alphabetical order
                    break;
                case "x":
                    System.out.println("\n            Have a Nice Day!");
                    System.out.println("--------------------------------------------------");
                    System.out.println("Thank You for using the Hotel Reservation System");
                    System.exit(0);
                    break;
                default:
                    System.err.println("Error encountered! Invalid Input\n"); //Default case is given for unspecified inputs which generate an error message
                    break;

            }
        } while (!userInput.equals("x")); //If user inputs "x" the program will terminate
    }

    //Printing out array elements(rooms) after checking if they are occupied or not through a selection structure 
    private static void viewRooms() {
        for (int room = 0; room < hotelRoom.length; room++) {
            if (hotelRoom[room]=="empty") {
                System.out.println("Room " + (room + 1) + " is " + hotelRoom[room]);
            } else {
                System.out.println("Room " + (room + 1) + " is being occupied by " + hotelRoom[room]);
            }
        }
    }

    //Printing out array elements(rooms) only if they are "empty", through a selection structure 
    private static void emptyRooms() {
        for (int room = 0; room < hotelRoom.length; room++) {
            if (hotelRoom[room]=="empty") {
                System.out.println("Room " + (room + 1) + " is vacant and available for reservation!");
            }
        }
    }

    private static void addCustomer() {
        int roomNo = 0;
        String roomOwner;

        // Validating invalid input for room number given by the user
        try {
            //Requesting user to select a room of choice
            System.out.print("\nEnter Room No. (1-10) or enter 11 to stop: ");
            roomNo = input.nextInt();

            while (roomNo > 10) {
                System.out.println("Enter valid Room No.!");
                System.out.print("\nEnter Room No. (1-10) or enter 11 to stop: ");
                roomNo = input.nextInt();
            }
        } catch (InputMismatchException | ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid Input!"); // To not let the program get interruptd by InputMismatchException and ArrayIndexOutOfBoundsException for invalid inputs
        }

        //Validating if the room is "empty"(vacant). Room is assigned to new customer only if it is vacant or else it will display that the room is occupied
        if (hotelRoom[roomNo - 1]=="empty") {
            System.out.print("\nEnter Name for Room " + roomNo + ": ");
            roomOwner = input.next();
            System.out.println("");

            hotelRoom[roomNo - 1]=roomOwner;

            System.out.println("Customer " + roomOwner + " has been successfully booked into Room " + roomNo);
        } else {
            System.out.println("\nRoom is already occupied");
        }

    }

    //Comparing user input name to the current index element in the array and reinitializing that room as "empty" once again     
    private static void deleteCustomer(String name) {
        for (int room = 0; room < hotelRoom.length; room++) {
            if (hotelRoom[room].equals(name)) {
                hotelRoom[room] = "empty";
                System.out.println("\nDeletion Complete");
            }
        }
    }

    //Comparing user input name to the current index element in the array and returning the room number of that name to be displayed
    private static int findRoomByName(String name) {
        int index = 0;
        for (int room = 0; room < hotelRoom.length; room++) {
            if (hotelRoom[room].equals(name)) {
                index = room;
            }
        }
        return index;
    }

    private static void writeToFile() throws Exception {
        System.out.print("Enter a filename(including .dat or .txt extension) to create a file: ");
        fileName = input.next();
        FileWriter hotelWrite = new FileWriter(fileName);
        //Created a new FileWriter object to write current String array elements onto the user defined file through a for loop
        hotelWrite.write("          Hotel Room Data\n");
        hotelWrite.write("-------------------------------------\n");

        for (int room = 0; room < hotelRoom.length; room++) {
            if (hotelRoom[room].equals("empty")) {
                hotelWrite.write("Room " + (room + 1) + " is " + hotelRoom[room] + "\n");
            } else {
                hotelWrite.write("Room " + (room + 1) + " is being occupied by " + hotelRoom[room] + "\n");
            }
        }
        hotelWrite.close();
        System.out.println("\nHotel Program Data successfully written to " + fileName + "   file");
    }

    private static void readHotelFromFile() throws Exception {
        Scanner hotelRead = new Scanner(new BufferedReader(new FileReader(fileName)));
        String fileLine;
        while (hotelRead.hasNext()) { 
            fileLine = hotelRead.nextLine();
            System.out.println(fileLine);
        }
        hotelRead.close();
        System.out.println("\nHotel Program Data successfully read from " + fileName + " file");
    }

    private static void roomsInOrder() {
        int index = 0;

        String hotelSorted[] = new String[hotelRoom.length];
        for (int room = 0; room < hotelRoom.length; room++) {
            hotelSorted[room] = hotelRoom[room];
        }
        Arrays.sort(hotelSorted);

        for (int room = 0; room < hotelSorted.length; room++) {
            if (!hotelSorted[room].equals("empty")) {
                for (int i = 0; i < hotelRoom.length; i++) {
                    if (hotelSorted[room].equals(hotelRoom[i])) {
                        index = i;
                    }
                }
                System.out.println("Room " + (index + 1) + ": " + hotelSorted[room]);
            }
        }
    }
}
