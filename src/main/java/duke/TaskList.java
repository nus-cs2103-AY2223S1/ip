
package duke;
import java.util.ArrayList;


public class TaskList {

    public void delete(ArrayList<Task> array, int number, int counter) {

        System.out.println("Noted. I've removed this task:");
        System.out.println(array.get(number-1).toString());
        int num = counter - 1;
        System.out.println("Now you have " + num + " tasks in the list");
        array.remove(number - 1);


    }
    public void todo(ArrayList<Task> array, String[] arr, int counter) throws DukeException{
        if (arr.length <= 1) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        else {
            array.add(counter, new Todo(arr[1]));
            System.out.println("_________________________________________________________________________");
            System.out.println("Got it. I've added this task:");
            System.out.println(array.get(counter).toString());

            counter++;
            System.out.println("Now you have " + counter + " tasks in the list.");
            System.out.println("_________________________________________________________________________");
        }
    }

    public void deadline(ArrayList<Task> array,String item, String deadline, String timing,  int counter){

        array.add(counter,new Deadline(item, deadline, timing));
        System.out.println("_________________________________________________________________________");
        System.out.println("Got it. I've added this task:");
        System.out.println(array.get(counter).toString());
        counter ++;
        System.out.println("Now you have " + counter + " tasks in the list.");
        System.out.println("_________________________________________________________________________");

    }

    public void event(ArrayList<Task> array,String item, String deadline, String timing, int counter) {
        array.add(counter,new Event(item,deadline, timing));
        System.out.println("_________________________________________________________________________");
        System.out.println("Got it. I've added this task:");
        System.out.println(array.get(counter).toString());
        counter ++;
        System.out.println("Now you have " + counter + " tasks in the list.");
        System.out.println("_________________________________________________________________________");

    }
}
