import java.util.*;
public class Duke {

    public static void main(String[] args) {
        Duke program = new Duke();
        program.init();
    }

    /**
     * List to store all tasks entered by the user.
     */
    private LinkedList<Task> lst;

    /**
     * Starts and ends the Duke program.
     */
    private void init() {
        printIntro();
        program();
        exitProgram();
    }

    /**
     * Prints the hello introduction to Duke.
     */
    private void printIntro() {
        String logo = " _    _ ______ _      _      ____     ______ _____   ____  __  __    _____  _    _ _  ________ \n"
                + "| |  | |  ____| |    | |    / __ \\   |  ____|  __ \\ / __ \\|  \\/  |  |  __ \\| |  | | |/ /  ____|\n"
                + "| |__| | |__  | |    | |   | |  | |  | |__  | |__) | |  | | \\  / |  | |  | | |  | | ' /| |__   \n"
                + "|  __  |  __| | |    | |   | |  | |  |  __| |  _  /| |  | | |\\/| |  | |  | | |  | |  < |  __| \n"
                + "| |  | | |____| |____| |___| |__| |  | |    | | \\ \\| |__| | |  | |  | |__| | |__| | . \\| |____\n"
                + "|_|  |_|______|______|______\\____/   |_|    |_|  \\_\\\\____/|_|  |_|  |_____/ \\____/|_|\\_\\______|\n";
        System.out.println(logo);
        System.out.println("  How may I help you today?");
        newLine();
    }

    /**
     * Runs the chatbot, taking in inputs from the user.
     */
    private void program() {
        Scanner sc = new Scanner(System.in);
        lst = new LinkedList<>();
        String input = null, cmd = null, postCmd = null;
        String postSplit[];

        do {
            input = sc.nextLine();
            newLine();
            postSplit = input.split(" ");
            cmd = postSplit[0];
            if (cmd.length() != input.length()) {
                postCmd = input.substring(cmd.length() + 1);
            }

            switch(cmd) {
                case "bye":
                    break;
                case "list":
                    printList();
                    break;
                case "todo":
                    handleTodo(postCmd);
                    break;
                case "deadline":
                    handleDeadline(postCmd);
                    break;
                case "event":
                    handleEvent(postCmd);
                    break;
                case "mark":
                    lst.get(Integer.parseInt(postSplit[1]) - 1).setDone();
                    System.out.println("  Nice! Task " + postSplit[1] + " done!\n  "
                            + lst.get(Integer.parseInt(postSplit[1]) - 1));
                    newLine();
                    break;
                case "unmark":
                    lst.get(Integer.parseInt(postSplit[1]) - 1).setUnDone();
                    System.out.println("  Ok! Task " + postSplit[1] + " marked as not done!\n  "
                            + lst.get(Integer.parseInt(postSplit[1]) - 1));
                    newLine();
                    break;
                default:
                    printCommands(cmd);
            }
        } while (!input.equals("bye"));
    }

    /**
     * Prints a list of available commands to the user.
     * @param cmd The invalid cmd that was entered by the user.
     */
    private void printCommands(String cmd) {
        String commands = "\ttodo - adds the task to the list\n" +
                "\tdeadline - adds the task with a deadline, e.g. deadline x /by Sunday\n" +
                "\tevent - adds the task that happens at a specific time, e.g. event x /at Mon 2-4pm\n" +
                "\tmark - marks task number x as completed, e.g. mark x\n" +
                "\tunmark - marks task number x as uncompleted, e.g. unmark x" +
                "\tlist - lists out all your current tasks\n" +
                "\tbye - exits the program:(\n";
        System.out.println("  " + cmd + " is not a valid command. Here are the list of commands:\n\n" + commands);
        newLine();
    }

    /**
     * Handles what to do with a "Todo" task.
     * @param desc The description of the task.
     */
    private void handleTodo(String desc) {
        lst.add(new Todo(desc));
        System.out.println("  Added task todo: \n  " + lst.get(lst.size() - 1));
        printListCount();
        newLine();
    }

    /**
     * Handles what to do with a "Deadline" task.
     * @param desc The description of the task.
     */
    private void handleDeadline(String desc) {
        String[] split = desc.split("/");
        lst.add(new Deadline(split[0], split[1].substring(3)));
        System.out.println("  Added the task with deadline: \n  " + lst.get(lst.size() - 1));
        printListCount();
        newLine();
    }

    /**
     * Handles what to do with a "Event" task.
     * @param desc The description of the task.
     */
    private void handleEvent(String desc) {
        String[] split = desc.split("/");
        lst.add(new Event(split[0], split[1].substring(3)));
        System.out.println("  Added the event task: \n  " + lst.get(lst.size() - 1));
        printListCount();
        newLine();
    }

    /**
     * Prints the number of current tasks, as well as how many are completed.
     */
    private  void printListCount() {
        System.out.println("  You have " + lst.size() + " tasks currently, " + Task.totalDone + " are completed");
    }

    /**
     * Prints a list of all the tasks.
     */
    private void printList() {
        if (lst.size() == 0) {
            System.out.println("  List is empty!");
        } else {
            System.out.println("  List of tasks: \n");
        }
        for (int i = 1; i <= lst.size(); i++) {
            System.out.println("  " + i + ": " + lst.get(i - 1));
        }
        newLine();
    }

    /**
     * Prints the exit program text.
     */
    private void exitProgram() {
        System.out.println("Bye:( Hope to see you again soon!");
        newLine();
    }

    /**
     * Prints a new line.
     */
    private void newLine() {
        System.out.println("________________________________________________________________________" +
                "_______________________");
    }
}
