import java.util.*;
public class Duke {
    public static void main(String[] args) {
        initProgram();
        program();
        exitProgram();
    }

    public static void initProgram() {
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

    public static void program() {
        Scanner sc = new Scanner(System.in);
        String input = null, cmd = null, postCmd = null;
        String postSplit[];
        LinkedList<Task> lst = new LinkedList<>();
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
                    printList(lst);
                    break;
                case "todo":
                    handleTodo(lst, postCmd);
                    break;
                case "deadline":
                    handleDeadline(lst, postCmd);
                    break;
                case "event":
                    handleEvent(lst, postCmd);
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

    public static void printCommands(String cmd) {
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

    public static void handleTodo(List lst, String desc) {
        lst.add(new Todo(desc));
        System.out.println("  Added task todo: \n  " + lst.get(lst.size() - 1));
        printListCount(lst);
        newLine();
    }

    public static void handleDeadline(List lst, String desc) {
        String[] split = desc.split("/");
        lst.add(new Deadline(split[0], split[1].substring(3)));
        System.out.println("  Added the task with deadline: \n  " + lst.get(lst.size() - 1));
        printListCount(lst);
        newLine();
    }

    public static void handleEvent(List lst, String desc) {
        String[] split = desc.split("/");
        lst.add(new Event(split[0], split[1].substring(3)));
        System.out.println("  Added the event task: \n  " + lst.get(lst.size() - 1));
        printListCount(lst);
        newLine();
    }

    public static void printListCount(List lst) {
        System.out.println("  You have " + lst.size() + " tasks currently, " + Task.totalDone + " are completed");
    }

    public static void printList(List lst) {
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


    public static void exitProgram() {
        System.out.println("Bye:( Hope to see you again soon!");
        newLine();
    }

    public static void newLine() {
        System.out.println("________________________________________________________________________" +
                "_______________________");
    }
}
