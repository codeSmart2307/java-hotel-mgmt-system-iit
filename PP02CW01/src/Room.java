
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


//OBJECT ARRAY [ROOM CLASS - Filename : Room.java]


public class Room {

    private String mainName;

    public Room() {
        mainName = "empty"; //When initializing a room this constructor is called
        System.out.println("Room initialized");
    }

    public void setName(String Name) {
        mainName = Name;
    }

    public String getName() {
        return mainName; //Everytime getName() method is called the mainName will be returned
    }

}
