
package duke;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalTime;


public class TaskList {

    /**
     * Returns the String value of which task has been deleted from list and how many tasks are remaining.
     *
     * @param array  Arraylist of tasks present.
     * @param number Index of task to be deleted.
     */
    public String delete(ArrayList<Task> array, int number) {
        if (number <= array.size() && number > 0) {
            String result = "";
            result = result + "Noted. I've removed this task:" + "\n";
            result = result + array.get(number - 1).toString() + "\n";
            array.remove(number - 1);
            int num = array.size();
            result = result + "Now you have " + num + " tasks in the list" + "\n";
            Storage.clearFile();
            Storage.writeToFile(array);
            return result;
        }
        else {
            return "please return a proper number to delete";
        }
    }

    /**
     * Returns the String value of which task has been deleted from list and how many tasks are remaining.
     *
     * @param array  arraylist of tasks present.
     * @param arr String array containing description of todo task.
     * @throws DukeException if the input for todo task is not given.
     */
    public String todo(ArrayList<Task> array, String[] arr) throws DukeException{
        if (arr.length <= 1 || arr[1].strip().equals("")) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        boolean contains = false;
        int j = 0;
        for (int i = 0; i < array.size(); i++) {
            if (array.get(i) != null && array.get(i).toString().contains(arr[1])) {
                j++;
                contains = true;
            }
        }
        String result = "";


        if (contains) {
            return "You have already added this task to the list";

        } else {

            array.add(new Todo(arr[1],false));

            result = result + "Got it. I've added this task:" + "\n";
            result = result + array.get(array.size() - 1).toString() + "\n";


            result = result + "Now you have " + array.size() + " tasks in the list." + "\n";
            Storage.clearFile();
            Storage.writeToFile(array);
            return result;

        }
    }

    /**
     * Returns the String value of which task has been deleted from list and how many tasks are remaining.
     *
     * @param array  arraylist of tasks present.
     * @param item description of the deadline task.
     * @param deadline date the task is due by.
     * @param timing the time it is due by on that date.
     */
    public String deadline(ArrayList<Task> array,String item, String deadline, String timing){
        boolean contains = false;
        int j = 0;
        for (int i = 0; i < array.size(); i++) {
            if (array.get(i) != null && array.get(i).toString().contains(item)) {
                j++;
                contains = true;
            }
        }

        if(contains) {
            return "You have already added this task to the list";
        }
        try {
            String result = "";
            array.add(new Deadline(item, LocalDate.parse(deadline), LocalTime.parse(timing), false));
            result = result + "Got it. I've added this task:" + "\n";
            result = result + array.get(array.size() - 1).toString() + "\n";
            result = result + "Now you have " + array.size() + " tasks in the list." + "\n";
            Storage.clearFile();
            Storage.writeToFile(array);
            return result;
        }  catch (DateTimeParseException e) {
            return new DukeException("please format your date or time correctly").toString();
        }
    }

    /**
     * Returns the String value of which task has been deleted from list and how many tasks are remaining.
     *
     * @param array  arraylist of tasks present.
     * @param item description of the deadline task.
     * @param deadline date the task is due by.
     * @param timing the time it is due by on that date.
     */
    public String event(ArrayList<Task> array,String item, String deadline, String timing) {
        boolean contains = false;
        int j = 0;
        for (int i = 0; i < array.size(); i++) {
            if (array.get(i) != null && array.get(i).toString().contains(item)) {
                j++;
                contains = true;
            }
        }

        if(contains) {
            return "You have already added this task to the list";
        }
        try {

            String result = "";
            array.add(new Event(item, LocalDate.parse(deadline), LocalTime.parse(timing), false));
            result = result + "Got it. I've added this task:" + "\n";
            result = result + array.get(array.size() - 1).toString() + "\n";
            result = result + "Now you have " + array.size() + " tasks in the list." + "\n";
            Storage.clearFile();
            Storage.writeToFile(array);
            return result;
        }
        catch(DateTimeParseException e) {
            return new DukeException("please format your date or time correctly").toString();
        }


    }
}
