package duke;
import java.util.List;

public class TaskList {
    public static void todo(List<Task> storage,ToDos toadd,String remainingmessage) throws DukeException {

        //Fix this to try and catch blocks?
        if (remainingmessage.isEmpty()) { //Message is only todo
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }

        storage.add(toadd);
        String GotIt = "Got it. I've added this task: ";
        String DisplayItem = toadd.getItem();
        String getStatus = toadd.getStatusIcon();
        String DisplayItemWithTask = "  " + DisplayItem + "[" + getStatus + "]" + remainingmessage;
        System.out.println(GotIt);
        System.out.println(DisplayItemWithTask);
        System.out.println(NumberOfItemsInList(storage));
    }

    public static void deadline(List<Task> storage,Deadlines toadd,String firsthalf,String secondhalf) {
        storage.add(toadd);
        String GotIt = "Got it. I've added this task: ";
        System.out.println(GotIt);
        //Display item with task
        String DisplayItem = "  " + toadd.getItem();
        String getStatus = toadd.getStatusIcon();
        getStatus = "[" + getStatus + "] ";
        String DisplayItemWithTask = DisplayItem + getStatus + firsthalf + "(" + "by: " + secondhalf + ")";
        System.out.println(DisplayItemWithTask);
        System.out.println(NumberOfItemsInList(storage));
    }

    public static void event(List<Task> storage,Events toadd,String firsthalf,String secondhalf) {
        storage.add(toadd);
        String GotIt = "Got it. I've added this task: ";
        System.out.println(GotIt);
        //Display item with task
        String DisplayItem = "  " + toadd.getItem();
        String getStatus = toadd.getStatusIcon();
        getStatus = "[" + getStatus + "] ";
        String DisplayItemWithTask = DisplayItem + getStatus + firsthalf + "(" + "at: " + secondhalf + ")";
        System.out.println(DisplayItemWithTask);
        System.out.println(NumberOfItemsInList(storage));
    }

    public static void deletetodo(int nextvalue,String task,String item,List<Task> storage) {
        System.out.println("Noted. I've removed this task:");
        String Tobedisplayed = "  " + task + "[ ]" + item;
        System.out.println(Tobedisplayed);
        removeItem(nextvalue,storage);
        System.out.println(NumberOfItemsInList(storage));

    }

    public static void deletedeadline(int nextvalue,String item,String deadlinetask,String deadline,List<Task> storage) {
        System.out.println("Noted. I've removed this task:");
        String Tobedisplayed = "  " + item + "[ ]" + " " + deadlinetask + " (by: "+ deadline + ")";
        System.out.println(Tobedisplayed);
        removeItem(nextvalue,storage);
        System.out.println(NumberOfItemsInList(storage));

    }

    public static void deleteevents (int nextvalue,String symbol, String eventdescription,String item,List<Task> storage) {
        System.out.println("Noted. I've removed this task:");
        String Tobedisplayed = "  " + symbol + "[ ]" + " " + eventdescription + "(at: "+ item + ")";
        System.out.println(Tobedisplayed);
        removeItem(nextvalue,storage);
        System.out.println(NumberOfItemsInList(storage));
    }


    public static void mark () {}




    public static String NumberOfItemsInList(List<Task> storage) {
        int numberOfItems = storage.size();
        String NumberOfItems = "Now you have " + numberOfItems + " tasks in the list.";
        return NumberOfItems;
    }


    public static void removeItem(int ItemIndex, List<Task> storage) {
        storage.remove(ItemIndex);
    }
}

