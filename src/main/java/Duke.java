import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    /*
    Level 2. Add, List
    Add the ability to store whatever text entered by the user and display them back to the user when requested.
     */
    static List<String> list = new ArrayList<>();
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
        System.out.println(dialog("Hello! I'm Duke" + "\n" + "   What can I do for you?"));

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
            } else if (message.equals("list")){
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < list.size(); i++) {
                    if (i == list.size() - 1) {
                        sb.append(i + 1 + ". " + list.get(i));
                    } else {
                        sb.append(i + 1 + ". " + list.get(i) + "\n" + "   ");
                    }
                }
                System.out.println(dialog(sb.toString()));
            } else {
                list.add(message);
                System.out.println(dialog("added: " + message));
            }
        }
    }

    //To wrap the string in a dialog frame
    public static String dialog(String message) {
        return "  ____________________________________________________________\n" +
                "   " + message + "\n" +
               "  ____________________________________________________________\n";
    }
}
