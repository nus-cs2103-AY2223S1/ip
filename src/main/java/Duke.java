import java.util.*;

public class Duke {
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
        getUserInput();
    }

    private static void dukePrint(String str) {
        System.out.println("===========================================\n");
        System.out.println(str);
        System.out.println("===========================================\n");
    }

    private static void getUserInput() {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        switch (str){
            case "bye": {
                endService();
                break;
            } default : {
                dukePrint(str);
                getUserInput();
            }
        }
    }

    private static void endService() {
        dukePrint("Bye. Hope to see you again!");
        return;
    }
}
