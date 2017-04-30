
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


//STRING ARRAY [Filename : Hotel.java]


import java.util.Scanner;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.Arrays;
import java.util.InputMismatchException;

public class Hotel {

    private static String fileName; //Variable to hold user declared filename to red and write hotel data

    public static void main(String[] args) throws Exception {
        String name; //Variable to hold name of customer
        String userInput; //Variable to hold user input from stdin
        String hotelRoom[] = new String[10]; //Initializing String array which can hold 10 elements(rooms)

        System.out.println("Priming Initialization Process...");

        initialize(hotelRoom); //Sets all hotelRoom array elements to "empty"

        System.out.println("Initialization Complete!");
        System.out.println("System running at Peak Performance...\n");

        do {
            Scanner option = new Scanner(System.in);

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
            userInput = option.next().toLowerCase(); //Assigning user input to a variable and converting it to lower case characters

            //Using a switch case for the user input, specific methods are called to serve the user's needs
            switch (userInput) {
                case "v":
                    System.out.println("\n            All Rooms");
                    System.out.println("--------------------------------------------------");
                    viewRooms(hotelRoom); //hotelRoom[] array is passed as an argument to the method so that all rooms can be displayed
                    break;
                case "a":
                    System.out.println("\n            Add Customer");
                    System.out.println("--------------------------------------------------");
                    addCustomer(hotelRoom); //hotelRoom[] array is passed as an argument to the method so that only rooms without owners can be used
                    break;
                case "e":
                    System.out.println("\n            Empty Rooms");
                    System.out.println("--------------------------------------------------");
                    emptyRooms(hotelRoom); //hotelRoom[] array is passed as an argument to the method so that all empty rooms can be displayed
                    break;
                case "d":
                    System.out.println("\n            Delete Customer");
                    System.out.println("--------------------------------------------------");
                    System.out.print("Name of customer to be deleted: ");
                    name = option.next();
                    deleteCustomer(hotelRoom, name);
                    /*hotelRoom[] array and customer name is passed as an argument 
                                                      *to the method so that the particular room with that name can be reinitialized*/
                    break;
                case "f":
                    System.out.println("\n            Find Room");
                    System.out.println("--------------------------------------------------");
                    System.out.print("Name of customer: ");
                    name = option.next();
                    System.out.println("Customer " + name + "'s room: " + (findRoomByName(hotelRoom, name) + 1));/*hotelRoom[] array and customer name is passed as an argument 
                                                                                                                  *to the method so that the particular room with that name can be retrieved*/
                    break;
                case "s":
                    System.out.println("\n            Writing to File");
                    System.out.println("--------------------------------------------------");
                    writeToFile(hotelRoom); //hotelRoom[] array is passed as an argument to the method so that current elements can be written to a file
                    break;
                case "l":
                    System.out.println("\n            Reading From File");
                    System.out.println("--------------------------------------------------");
                    readFromFile(); //hotelRoom[] array is passed as an argument to the method so that current elements can be read from a file
                    break;
                case "o":
                    System.out.println("\n          Rooms in Alphabetical Order");
                    System.out.println("--------------------------------------------------");
                    roomsInOrder(hotelRoom); //hotelRoom[] array is passed as an argument to the method so that it can be sorted in alphabetical order
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

    //Initializing array elements as "empty" to suggest vacant rooms
    private static void initialize(String roomArr[]) {
        for (int i = 0; i < roomArr.length; i++) {
            roomArr[i] = "empty";
        }
    }

    //Printing out array elements(rooms) after checking if they are occupied or not through a selection structure 
    private static void viewRooms(String roomArr[]) {
        for (int room = 0; room < roomArr.length; room++) {
            if (roomArr[room].equals("empty")) {
                System.out.println("Room " + (room + 1) + " is " + roomArr[room]);
            } else {
                System.out.println("Room " + (room + 1) + " is being occupied by " + roomArr[room]);
            }
        }
    }

    //Printing out array elements(rooms) only if they are "empty", through a selection structure 
    private static void emptyRooms(String roomArr[]) {
        for (int room = 0; room < roomArr.length; room++) {
            if (roomArr[room].equals("empty")) {
                System.out.println("Room " + (room + 1) + " is vacant and available for reservation!");
            }
        }
    }

    private static void addCustomer(String roomArr[]) {
        int roomNo = 0;
        String roomOwner;
        Scanner input = new Scanner(System.in);

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
        // To not let the program get interruptd by  for invalid inputs

        //Validating if the room is "empty"(vacant). Room is assigned to new customer only if it is vacant or else it will display that the room is occupied
        if (roomArr[roomNo - 1].equals("empty")) {
            System.out.print("\nEnter Name for Room " + roomNo + ": ");
            roomOwner = input.next();
            System.out.println("");

            roomArr[roomNo - 1] = roomOwner;

            System.out.println("Customer " + roomOwner + " has been successfully booked into Room " + roomNo);
        } else {
            System.out.println("\nRoom is already occupied");
        }

    }

    //Comparing user input name to the current index element in the array and reinitializing that room as "empty" once again     
    private static void deleteCustomer(String roomArr[], String name) {
        for (int room = 0; room < roomArr.length; room++) {
            if (roomArr[room].equals(name)) {
                roomArr[room] = "empty";
                System.out.println("\nDeletion Complete");
            }
        }
    }

    //Comparing user input name to the current index element in the array and returning the room number of that name to be displayed
    private static int findRoomByName(String roomArr[], String name) {
        int index = 0;
        for (int room = 0; room < roomArr.length; room++) {
            if (roomArr[room].equals(name)) {
                index = room;
            }
        }
        return index;
    }

    private static void writeToFile(String roomArr[]) throws Exception {
        Scanner writeScanner = new Scanner(System.in);
        System.out.print("Enter a filename(including .dat or .txt extension) to create a file: ");
        fileName = writeScanner.next();
        FileWriter hotelWrite = new FileWriter(fileName);
        //Created a new FileWriter object to write current String array elements onto the user defined file through a for loop
        hotelWrite.write("          Hotel Room Data\n");
        hotelWrite.write("-------------------------------------\n");

        for (int room = 0; room < roomArr.length; room++) {
            if (roomArr[room].equals("empty")) {
                hotelWrite.write("Room " + (room + 1) + " is " + roomArr[room] + "\n");
            } else {
                hotelWrite.write("Room " + (room + 1) + " is being occupied by " + roomArr[room] + "\n");
            }
        }
        hotelWrite.close();
        System.out.println("\nHotel Program Data successfully written to " + fileName + "  file");
    }

    private static void readFromFile() throws Exception {
        Scanner hotelRead = new Scanner(new BufferedReader(new FileReader(fileName)));
        //Reading user defined and buffering it into memory
        String fileLine;
        while (hotelRead.hasNext()) { //While there exists more lines it will print all lines in the file through the while loop
            fileLine = hotelRead.nextLine();
            System.out.println(fileLine);
        }
        hotelRead.close();
        System.out.println("\nHotel Program Data successfully read from " + fileName + " file");
    }

    private static void roomsInOrder(String roomArr[]) {
        int index = 0; //Variable to hold the index of the original array to display room number

        String hotelSorted[] = new String[roomArr.length];//Created a new array to undergo sorting
        for (int room = 0; room < roomArr.length; room++) {
            hotelSorted[room] = roomArr[room];
        }
        Arrays.sort(hotelSorted);

        //Nested for-loops to iterate and print room owners' names and particular room(now sorted)
        for (int room = 0; room < hotelSorted.length; room++) {
            if (!hotelSorted[room].equals("empty")) {
                for (int i = 0; i < roomArr.length; i++) {
                    if (hotelSorted[room].equals(roomArr[i])) {
                        index = i;
                    }
                }
                System.out.println("Room " + (index + 1) + ": " + hotelSorted[room]);
            }
        }
    }
}