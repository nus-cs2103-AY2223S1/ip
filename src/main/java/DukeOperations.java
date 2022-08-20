import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class DukeOperations {
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

    public void todo(ArrayList<Task> array, String[] arr, int counter) throws DukeException{
        if (arr.length <= 1) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        else {
            array.add(counter, new Todo(arr[1]));
            System.out.println("Got it. I've added this task:");
            System.out.println(array.get(counter).toString());
            counter++;
            System.out.println("Now you have " + counter + " tasks in the list.");
        }
    }

    public void deadline(ArrayList<Task> array,String item, String deadline, String timing,  int counter){

        array.add(counter,new Deadline(item, deadline, timing));
        System.out.println("Got it. I've added this task:");
        System.out.println(array.get(counter).toString());
        counter ++;
        System.out.println("Now you have " + counter + " tasks in the list.");
    }

    public void event(ArrayList<Task> array,String item, String deadline, String timing, int counter) {
        array.add(counter,new Event(item,deadline, timing));
        System.out.println("Got it. I've added this task:");
        System.out.println(array.get(counter).toString());
        counter ++;
        System.out.println("Now you have " + counter + " tasks in the list.");

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

    public void delete(ArrayList<Task> array, int number,int counter) {

        System.out.println("Noted. I've removed this task:");
        System.out.println(array.get(number-1).toString());
        int num = counter - 1;
        System.out.println("Now you have " + num + " tasks in the list");
        array.remove(number - 1);


    }

}
