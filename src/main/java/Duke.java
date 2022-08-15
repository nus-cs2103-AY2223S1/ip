/**
 * Project done by Hong Jin.
 */
import java.util.*;

/**
 * Main class that runs chat bot Duke.
 */
public class Duke {

    public static final String initText = "Hello! I'm Duke\n    What can I do for you?";
    public static final String endText = "Bye bye! Hope to see you again soon!";

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Scanner scan = new Scanner(System.in);
        printMsg(initText);
        TaskList ls = new TaskList();

        while (true) {
            System.out.println("Say something: ");
            String input = scan.nextLine();
            String[] com = input.split(" ", 2);

            //command to terminate Duke.
            if (input.equals("bye") || com[0] == "bye") {
                printMsg(endText);
                break;
            }

            //if else ladder.
            switch (com[0]) {
                case "list":
                    printMsg(ls.enumerate());
                    break;

                case "mark":
                    printMsg(ls.updateMark(Integer.parseInt(com[1])));
                    break;

                case "unmark":
                    printMsg(ls.updateUnmark(Integer.parseInt(com[1])));
                    break;

                case "todo":
                    printMsg(ls.addTask(new Task(com[1], "[T]")));
                    break;

                case "deadline":
                    printMsg(ls.addTask(new Task(com[1], "[D]")));
                    break;

                case "event":
                    printMsg(ls.addTask(new Task(com[1], "[E]")));
                    break;

                default:
                    printMsg("Invalid command,,,I can't understand :( Try again.");
            }
        }
    }
    /**
     * Class method to print message with horizontal line.
     * @param str
     */
    public static void printMsg (String str){
        System.out.println("  ____________________________________________________________");
        System.out.println("    " + str);
        System.out.println("  ____________________________________________________________");
    }
}
