import java.util.*;

public class Duke {
    private static List<String> dukeTasks;

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        dukeTasks = new ArrayList<String>();
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
        dukeTasks.add(str);
        dukePrint(String.format("added: %s\n",str));
    }

    private static void dukeShowList() {
        String tasks = "List of tasks to be done:\n";
        for (int i = 1; i <= dukeTasks.size(); i ++) {
            tasks += String.format("%d. %s\n", i, dukeTasks.get(i-1));
        }
        dukePrint(tasks);
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
                dukeStoreTask(str);
                getUserInput();
            }
        }
    }

    private static void endService() {
        dukePrint("Bye. Hope to see you again!");
        return;
    }
}
