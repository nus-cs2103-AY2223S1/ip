import java.util.*;
public class Duke {
    public static void main(String[] args) {
        initProgram();
        program();
        exitProgram();
    }

    public static void initProgram() {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
        String logo = " _    _ ______ _      _      ____     ______ _____   ____  __  __    _____  _    _ _  ________ \n"
                    + "| |  | |  ____| |    | |    / __ \\   |  ____|  __ \\ / __ \\|  \\/  |  |  __ \\| |  | | |/ /  ____|\n"
                    + "| |__| | |__  | |    | |   | |  | |  | |__  | |__) | |  | | \\  / |  | |  | | |  | | ' /| |__   \n"
                    + "|  __  |  __| | |    | |   | |  | |  |  __| |  _  /| |  | | |\\/| |  | |  | | |  | |  < |  __| \n"
                    + "| |  | | |____| |____| |___| |__| |  | |    | | \\ \\| |__| | |  | |  | |__| | |__| | . \\| |____\n"
                    + "|_|  |_|______|______|______\\____/   |_|    |_|  \\_\\\\____/|_|  |_|  |_____/ \\____/|_|\\_\\______|\n";
        System.out.println(logo);
        System.out.println("How may I help you today?");
        newLine();
    }

    public static void program() {
        Scanner sc = new Scanner(System.in);
        String input = null;
        do {
            input = sc.nextLine();
            if (!input.equals("bye")) {
                System.out.println(" " + input);
                newLine();
            }
        } while (!input.equals("bye"));
    }


    public static void exitProgram() {
        System.out.println("Bye. Hope to see you again soon!");
        newLine();
    }

    public static void newLine() {
        System.out.println("____________________________________________________________");
    }
}
