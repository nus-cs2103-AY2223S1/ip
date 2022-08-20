import java.util.ArrayList;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import java.io.File;
import java.io.FileWriter;


public class DukeOperations {



    public DukeOperations() {

    }
    public void exit() {
        System.out.println("_________________________________________________________________________");
        System.out.println("        Bye. Hope to see you again soon!");
        System.out.println("_________________________________________________________________________");
    }

    public void displayList(ArrayList<Task> array,int counter) {
        System.out.println("_________________________________________________________________________");
        for (int i = 0; i < counter; i++) {
            if (array.get(i) != null) {
                int j = i + 1;
                System.out.println(j + ". " + array.get(i).toString());
            }
        }
        System.out.println("_________________________________________________________________________");
    }



    public void mark(ArrayList<Task> array, int number) {
        array.get(number-1).markAsDone();
        System.out.println("_________________________________________________________________________");
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(array.get(number-1).toString());
        System.out.println("_________________________________________________________________________");
    }

    public void unmark(ArrayList<Task>array, int number) {
        array.get(number-1).markAsNotDone();
        System.out.println("_________________________________________________________________________");
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(array.get(number-1).toString());
        System.out.println("_________________________________________________________________________");
    }

    public void randomword(String word) throws DukeException{
        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");

    }



}
