package duke;
import java.util.List;

public class TaskList {
    /**
     * creation of todos object
     * @param storage
     * @param toadd
     * @param remainingmessage
     * @throws DukeException
     */
    public static String todo(List<Task> storage,ToDos toadd,String remainingmessage) throws DukeException {
        String response = "";
        //Fix this to try and catch blocks?
        if (remainingmessage.isEmpty()) { //Message is only todo
           throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }else {
            storage.add(toadd);
            String GotIt = "Got it. I've added this task: ";
            String DisplayItem = toadd.getItem();
            String getStatus = toadd.getStatusIcon();
            String DisplayItemWithTask = "  " + DisplayItem + "[" + getStatus + "]" + remainingmessage;

            response += GotIt;
            response += "\n";
            response += DisplayItemWithTask;
            response += "\n";
            response += NumberOfItemsInList(storage);
        }
        return response;
        //return Gotit + "\n" + DisplayItemWithTask + "\n" + NumberOfItemsInList(storage) + "\n"
    }

    /**
     * deadline object when creating
     * @param storage
     * @param toadd
     * @param firsthalf
     * @param secondhalf
     */

    public static String deadline(List<Task> storage,Deadlines toadd,String firsthalf,String secondhalf) {
        String response = "";
        storage.add(toadd);
        String GotIt = "Got it. I've added this task: ";
        //Display item with task
        String DisplayItem = "  " + toadd.getItem();
        String getStatus = toadd.getStatusIcon();
        getStatus = "[" + getStatus + "] ";
        String DisplayItemWithTask = DisplayItem + getStatus + firsthalf + "(" + "by: " + secondhalf + ")";

        response += GotIt;
        response += "\n";
        response += DisplayItemWithTask;
        response += "\n";
        response += NumberOfItemsInList(storage);
        return response;

    }

    /**
     * Creation of events object
     * @param storage
     * @param toadd
     * @param firsthalf
     * @param secondhalf
     */
    public static String event(List<Task> storage,Events toadd,String firsthalf,String secondhalf) {
        String response = "";
        storage.add(toadd);
        String GotIt = "Got it. I've added this task: ";
        //Display item with task
        String DisplayItem = "  " + toadd.getItem();
        String getStatus = toadd.getStatusIcon();
        getStatus = "[" + getStatus + "] ";
        String DisplayItemWithTask = DisplayItem + getStatus + firsthalf + "(" + "at: " + secondhalf + ")";

        response += GotIt;
        response += "\n";
        response += DisplayItemWithTask;
        response += "\n";
        response += NumberOfItemsInList(storage);
        return response;

    }

    /**
     * When deleting todos object
     * @param nextvalue
     * @param task
     * @param item
     * @param storage
     */
    public static String deletetodo(int nextvalue,String task,String item,List<Task> storage) {
        String response = "";
        String Tobedisplayed = "  " + task + "[ ]" + item;
        removeItem(nextvalue,storage);

        response += "Noted. I've removed this task:";
        response += "\n";
        response += Tobedisplayed;
        response += "\n";
        response += NumberOfItemsInList(storage);
        return response;
    }

    /**
     * deletion of deadline object
     * @param nextvalue
     * @param item
     * @param deadlinetask
     * @param deadline
     * @param storage
     */
    public static String deletedeadline(int nextvalue,String item,String deadlinetask,String deadline,List<Task> storage) {
        String response = "";
        String Tobedisplayed = "  " + item + "[ ]" + " " + deadlinetask + " (by: "+ deadline + ")";
        removeItem(nextvalue,storage);
        response += "Noted. I've removed this task:";
        response += "\n";
        response += Tobedisplayed;
        response += "\n";
        response += NumberOfItemsInList(storage);
        return response;

    }

    /**
     * Deletion of deleteevents object
     * @param nextvalue
     * @param symbol
     * @param eventdescription
     * @param item
     * @param storage
     */
    public static String deleteevents (int nextvalue,String symbol, String eventdescription,String item,List<Task> storage) {
        String response = "";
        String Tobedisplayed = "  " + symbol + "[ ]" + " " + eventdescription + "(at: "+ item + ")";
        removeItem(nextvalue,storage);
        response += "Noted. I've removed this task:";
        response += "\n";
        response += Tobedisplayed;
        response += "\n";
        response += NumberOfItemsInList(storage);
        return response;

    }


    public static void mark () {}

    /**
     * Check number of items in the list
     * @param storage
     * @return String
     */
    public static String NumberOfItemsInList(List<Task> storage) {
        int numberOfItems = storage.size();
        String NumberOfItems = "Now you have " + numberOfItems + " tasks in the list.";
        return NumberOfItems;
    }


    public static void removeItem(int ItemIndex, List<Task> storage) {
        storage.remove(ItemIndex);
    }
}

