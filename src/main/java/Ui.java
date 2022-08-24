import java.util.ArrayList;

public class Ui {
    public static void greet() {
        String logo = " ____        _        \n" + "|  _ \\ _   _| | _____ \n" + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n" + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    public static void exitMessage() {
        Ui.printMessage("Bye. Hope to see you again soon!");
    }

    public static void printMessage(String[] strArray) {
        System.out.println("_______________________________________________________");
        System.out.println("\tHere are the tasks in your list:");
        for (String str : strArray) {
            System.out.println("\t" + str);
        }
        System.out.println("_______________________________________________________");
    }

    public static void printMessage(String str) {
        System.out.println("_______________________________________________________" + "\n\t" + str + "\n"
                + "_______________________________________________________");
    }

    public static String wrapMessage(String str, String taskDescription, ArrayList<Task> taskList) {
        return String.format(
                str + "\n\t\t" + taskDescription + "\n\tNow you have " + taskList.size() + " tasks in the list.");
    }

}
