
package duke;
import java.util.ArrayList;


public class TaskList {

    public String delete(ArrayList<Task> array, int number) {
        String result = "";
        result = result + "Noted. I've removed this task:" + "\n";
        result = result + array.get(number-1).toString() + "\n";
        array.remove(number - 1);
        int num = array.size();
        result = result + "Now you have " + num + " tasks in the list" + "\n";
        Storage.clearFile();
        Storage.writeToFile(array);
        return result;


    }
    public String todo(ArrayList<Task> array, String[] arr) throws DukeException{
        boolean contains = false;
        int j = 0;
        for (int i = 0; i < array.size(); i++) {
            if (array.get(i) != null && array.get(i).toString().contains(arr[1])) {
                j++;
                contains = true;
            }
        }
        String result = "";
        if (arr.length <= 1) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }

        else if (contains) {
            return "You have already added this task to the list";

        } else {

            array.add(new Todo(arr[1]));

            result = result + "Got it. I've added this task:" + "\n";
            result = result + array.get(array.size() - 1).toString() + "\n";


            result = result + "Now you have " + array.size() + " tasks in the list." + "\n";
            Storage.clearFile();
            Storage.writeToFile(array);
            return result;

        }
    }

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

        String result = "";
        array.add(new Deadline(item, deadline, timing));
        result = result + "Got it. I've added this task:" + "\n";
        result = result + array.get(array.size() - 1).toString() + "\n";
        result = result + "Now you have " + array.size() + " tasks in the list." + "\n";
        Storage.clearFile();
        Storage.writeToFile(array);
        return result;


    }

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
        String result = "";
        array.add(new Event(item,deadline, timing));
        result = result + "Got it. I've added this task:" + "\n";
        result = result + array.get(array.size() - 1).toString() + "\n";
        result = result + "Now you have " + array.size() + " tasks in the list." + "\n";
        Storage.clearFile();
        Storage.writeToFile(array);
        return result;


    }
}
