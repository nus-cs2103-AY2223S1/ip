import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        /*
        Level 1: Level 1. Greet, Echo, Exit
        Implement a skeletal version of Duke that starts by greeting the user,
        simply echos commands entered by the user, and exits when the user types bye.
         */
        System.out.println(dialog("Hello! I'm Duke" + "\n" + "  What can I do for you?"));

        //Initialise the scanner used.
        Scanner sc = new Scanner(System.in);
        //Initialise a variable to receive the text entered.
        String message;
        while(true) {
            //Update the message variable
            message = sc.nextLine();
            /*
            Check if exit command is being entered
            If true, print the exit statement and exit the loop, else repeat the statement entered.
             */
            if (message.equals("bye")) {
                System.out.println(dialog("Bye. Hope to see you again soon!"));
                sc.close();
                break;
            } else {
                System.out.println(dialog(message));
            }
        }
    }

    //A wrap the string in a dialog frame
    public static String dialog(String message) {
        return "  ____________________________________________________________\n" +
                "  " + message + "\n" +
               "  ____________________________________________________________\n";
    }
}
