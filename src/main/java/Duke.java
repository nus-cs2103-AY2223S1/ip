import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Duke {
    private static List<Task> dukeTasks;
    private static String MARK_DONE_REGEX = "\\bmark \\b[0-9]+";
    private static String MARK_UNDONE_REGEX = "\\bunmark \\b[0-9]+";

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        dukeTasks = new ArrayList<Task>();
        startService();
    }
    private static void startService() {
        dukePrint("Hello! I'm Duke\nWhat can I do for you?\n");
        getUserInput();
    }

    private static void dukePrint(String str) {
        System.out.println("===========================================\n");
        System.out.println(str);
        System.out.println("===========================================\n");
    }
    private static void dukeStoreTask(String str) {
        dukeTasks.add(new Task(str));
        dukePrint(String.format("added: %s\n",str));
    }

    private static void dukeShowList() {
        String tasks = "List of tasks to be done:\n";
        for (int i = 0; i < dukeTasks.size(); i ++) {
            tasks += String.format("%d. %s\n", i + 1, dukeTasks.get(i));
        }
        dukePrint(tasks);
    }

    private static void dukeMarkTask(int i) {
        if ((0 <= i) && (i < dukeTasks.size())) {
            dukeTasks.get(i).markComplete();
            String str = dukeTasks.get(i).toString();
            dukePrint(String.format("Nice! I've marked this task as done:\n %s\n", str));
        } else {
            dukePrint("Error. Task is not in the list");
        }
    }

    private static void dukeUnmarkTask(int i) {
        if ((0 <= i) && (i < dukeTasks.size())) {
            dukeTasks.get(i).markIncomplete();
            String str = dukeTasks.get(i).toString();
            dukePrint(String.format("OK, I've marked this task as not done yet:\n %s\n", str));
        } else {
            dukePrint("Error. Task is not in the list");
        }
    }

    private static void getUserInput() {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        switch (str){
            case "bye": {
                endService();
                break;
            } case "list": {
                dukeShowList();
                getUserInput();
                break;
            } default : {
                if (str.matches(MARK_DONE_REGEX)){
                    int index = Integer.parseInt(str.split(" ")[1]);
                    dukeMarkTask(index - 1);
                } else if (str.matches(MARK_UNDONE_REGEX)) {
                    int index = Integer.parseInt(str.split(" ")[1]);
                    dukeUnmarkTask(index - 1);
                } else {
                    dukeStoreTask(str);
                }
                getUserInput();
            }
        }
    }

    private static void endService() {
        dukePrint("Bye. Hope to see you again!");
        return;
    }
}
