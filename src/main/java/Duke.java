import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Duke {
    private static List<Task> dukeTasks;
    private static Scanner sc;

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        startService();
    }

    private static void startService() {
        dukePrint("Hello! I'm Duke\nWhat can I do for you?\n");
        sc = new Scanner(System.in);
        dukeTasks = SaveManager.loadData();
        getUserInput();
    }

    private static void dukePrint(String str) {
        System.out.println("===========================================\n");
        System.out.println(str);
        System.out.println("===========================================\n");
    }

    private static void dukeStoreTask(String str, char type, String dateTime) {
        if (str.isEmpty()) {
            dukePrint("Description cannot be empty\n");
            return;
        }
        if (dateTime != null && dateTime.isEmpty()) {
            dukePrint("Date/Time cannot be empty\n");
            return;
        }
        Task newTask;
        switch (type) {
            case 'T': {
                newTask = new Todo(str);
                break;
            } case 'E': {
                newTask = new Event(str,dateTime);
                break;
            } case 'D': {
                newTask = new Deadline(str, dateTime);
                break;
            } default: {
                newTask = new Todo(str);
            }
        }
        dukeTasks.add(newTask);
        dukePrint(String.format("Got it. I've added this task: \n %s\n %s\n",newTask.toString(), getNoOfTasks()));
    }

    private static String getNoOfTasks() {
        int size = dukeTasks.size();
        if (size <= 1) {
            return String.format("Now you have %d task in the list\n",size);
        } else {
            return String.format("Now you have %d tasks in the list\n",size);
        }
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
            dukePrint("Error. Task is not in the list\n");
        }
    }

    private static void dukeUnmarkTask(int i) {
        if ((0 <= i) && (i < dukeTasks.size())) {
            dukeTasks.get(i).markIncomplete();
            String str = dukeTasks.get(i).toString();
            dukePrint(String.format("OK, I've marked this task as not done yet:\n %s\n", str));
        } else {
            dukePrint("Error. Task is not in the list\n");
        }
    }

    private static void dukeRemoveTask(int i) {
        if ((0 <= i) && (i < dukeTasks.size())) {
            Task remove = dukeTasks.remove(i);
            String str = remove.toString();
            dukePrint(String.format("OK, I've remove this task:\n %s\n %s", str, getNoOfTasks()));
        } else {
            dukePrint("Error. Task is not in the list\n");
        }
    }

    //TODO: Remove duplicated code
    private static void getUserInput() {
        /*
        String str;
        try {
            str = sc.nextLine();
        } catch (NoSuchElementException exception) {
            System.out.println("No more commands found...ending now");
            endService();
            return;
        }*/

        String str = sc.nextLine().replaceAll("( )+", " ");
        //System.out.println(String.format("The command is: %s",str));
        String command = str.split(" ")[0];
        switch (command){
            case "exit":
            case "quit":
            //Fallthrough
            case "bye": {
                endService();
                return;
            } case "list": {
                dukeShowList();
                break;
            } case "mark": {
                int index = Integer.parseInt(str.split(" ")[1]);
                dukeMarkTask(index - 1);
                break;
            } case "unmark": {
                int index = Integer.parseInt(str.split(" ")[1]);
                dukeUnmarkTask(index - 1);
                break;
            } case "delete": {
                //Fallthrough
            } case "remove": {
                int index = Integer.parseInt(str.split(" ")[1]);
                dukeRemoveTask(index - 1);
                break;
            } case "todo": {
                try {
                    Pattern p = Pattern.compile("todo (.*)");
                    Matcher m = p.matcher(str);
                    m.find();
                    dukeStoreTask(m.group(1).trim(), 'T', null);
                } catch (IllegalStateException e) {
                    dukePrint("Are you missing a description?\n");
                }
                break;
            } case "deadline": {
                try {
                    Pattern p = Pattern.compile("deadline(.*)/by(.*)");
                    Matcher m = p.matcher(str);
                    m.find();
                    dukeStoreTask(m.group(1).trim(), 'D', m.group(2).trim());

                } catch (IllegalStateException e) {
                    dukePrint("Are you missing a /by ?\n");
                }
                break;
            } case "event": {
                try {
                    Pattern p = Pattern.compile("event(.*)/at(.*)");
                    Matcher m = p.matcher(str);
                    m.find();
                    dukeStoreTask(m.group(1).trim(), 'E', m.group(2).trim());
                } catch (IllegalStateException e) {
                    dukePrint("Are you missing a /at ?\n");
                }
                break;
            } default: {
                dukePrint("Invalid Command. Please try again\n");
            }
        }
        getUserInput();

    }

    private static void endService() {
        dukePrint("Bye. Hope to see you again!\n");
        SaveManager.saveData(dukeTasks);
        sc.close();
        return;
    }
}
