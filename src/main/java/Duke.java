import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    //Initialise a list variable to track the list of tasks
    static List<Task> list = new ArrayList<>();
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        //Print the starting statement
        System.out.println(dialog("Hello! I'm Duke" + "\n" + "   What can I do for you?"));

        //Initialise the scanner used.
        Scanner sc = new Scanner(System.in);
        //Initialise a variable to receive the text entered.
        String message;
        while(true) {
            //Update the message variable
            message = sc.nextLine();

            switch (message) {
                case "bye":
                    System.out.println(dialog("Bye. Hope to see you again soon!"));
                    sc.close();
                    break;

                case "list":
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < list.size(); i++) {
                        if (i == list.size() - 1) {
                            sb.append(i + 1 + ". " + String.format("[%s] ", list.get(i).getStatusIcon()) + list.get(i).description);
                        } else {
                            sb.append(i + 1 + ". " + String.format("[%s] ", list.get(i).getStatusIcon()) + list.get(i).description + "\n" + "   ");
                        }
                    }
                    System.out.println(dialog(sb.toString()));
                    break;

                default:
                    if (message.startsWith("mark")) {
                        Integer index = Integer.valueOf(message.substring(5)) - 1;
                        Task curr = list.get(index);
                        curr.markAsDone();
                        System.out.println(dialog(
                                "Nice! I've marked this task as done:\n" +
                                "   " + String.format("[%s] %s", curr.getStatusIcon(), curr.description)
                        ));
                    } else if (message.startsWith("unmark")) {
                        Integer index = Integer.valueOf(message.substring(7)) - 1;
                        Task curr = list.get(index);
                        curr.unMark();
                        System.out.println(dialog(
                                "OK, I've marked this task as not done yet:\n" +
                                        "   " + String.format("[%s] %s", curr.getStatusIcon(), curr.description)
                        ));
                    } else {
                        Task task = new Task(message);
                        list.add(task);
                        System.out.println(dialog("added: " + message));
                    }

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
