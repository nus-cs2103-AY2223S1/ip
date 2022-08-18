import java.util.ArrayList;
import java.util.Scanner;

// This class is the main logic unit for Duke
public class Duke {

    private static ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Displays the Greeting message.
     */
    private static void Greet() {
        String logo = " _______               \n"
                + "|  _____|  _   _____   \n"
                + "|  |____  | | |  __ |  \n"
                + "|   ____| | | |  ___|  \n"
                + "|  |____  | | | |      \n"
                + "|_______| |_| |_|";
        System.out.println("Greetings from Elp\n" + logo);
        System.out.println("What can I help you with?\n");
    }

    /**
     * Handles Printing of the task list.
     */
    private static void printTaskList() {
        if (!tasks.isEmpty()) {
            for (int i = 1; i <= tasks.size(); i++) {
                System.out.println(i + ". " + tasks.get(i - 1).toString());
            }
            System.out.println("");
        } else {
            System.out.println("No tasks have been added!\n");
        }
    }

    /**
     * Handles the addition of tasks.
     *
     * @param type        type of task added.
     * @param description description of the task.
     * @throws
     */
    private static void addTask(Command type, String description) throws DukeException {
        Task task;
        if (type == Command.TODO) {
            task = new ToDo(description);
        } else if (type == Command.DEADLINE) {
            task = new Deadline(description);
        } else {
            task = new Event(description);
        }
        System.out.println("Added: " + task.toString() + "\n");
        tasks.add(task);
    }

    /**
     * Marks/Unmarks Tasks.
     *
     * @param markStatus input to mark/unmark a task.
     * @param inputArr   input of the user.
     */
    private static void taskMarker(Command markStatus, String[] inputArr) {
        try {
            int taskNo = Integer.parseInt(inputArr[1]);
            Task currTask = tasks.get(taskNo - 1);
            if (markStatus == Command.MARK) {
                currTask.markDone();
                System.out.println("Task successfully marked!");
            } else {
                currTask.markUndone();
                System.out.println("Task successfully unmarked!");
            }
            System.out.println(currTask + "\n");
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            System.out.println("Invalid input, please input an available integer index!\n");
        }
    }

    /**
     * Deletes the task with the specified index.
     *
     * @param inputIndex index to be deleted.
     */
    private static void deleteTask(String inputIndex) {
        try {
            int taskNo = Integer.parseInt(inputIndex);
            Task task = tasks.get(taskNo - 1);
            System.out.println("Ok, I've removed this task:");
            System.out.println(task);
            tasks.remove(taskNo - 1);
            System.out.println("You have " + tasks.size() + " tasks left currently\n");
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            System.out.println("Invalid index, please input the index of an available task!\n");
        }
    }

    /**
     * Handles the logic for the task manager.
     */
    private static void taskHandler() {
        Scanner sc = new Scanner(System.in);
        String in = sc.nextLine();

        while (true) {
            // Splits the input to retrieve possible commands.
            String[] inputArr = in.split(" ", 2);
            String inputCommand = inputArr[0];
            Command command = null;

            try {
                command = Command.getCommand(inputCommand);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }

            // Break out of loop
            if (command == Command.BYE) {
                break;
            }

            // List out current tasks in the list
            if (command == Command.LIST) {
                printTaskList();
            }

            if (command == Command.MARK || command == Command.UNMARK) {
                taskMarker(command, inputArr);
            }

            if (command == Command.DELETE) {
                try {
                    deleteTask(inputArr[1]);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Please add an index to delete a task!\n");
                }
            }

            if (command == Command.TODO || command == Command.DEADLINE || command == Command.EVENT) {
                try {
                    addTask(command, inputArr[1]);
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                } catch (IndexOutOfBoundsException p) {
                    System.out.println("Please add a description to your task!\n");
                }
            }
            in = sc.nextLine();
        }
        System.out.println("Have a nice day!");
    }

    public static void main(String[] args) {
        Greet();
        taskHandler();
    }

    private enum Command {
        TODO("todo"),
        DEADLINE("deadline"),
        EVENT("event"),
        LIST("list"),
        MARK("mark"),
        UNMARK("unmark"),
        DELETE("delete"),
        BYE("bye");

        private final String inputCommand;

        /**
         * Constructor for the command enum.
         *
         * @param command input command
         */
        Command(String command) {
            this.inputCommand = command;
        }

        /**
         * Returns the command if given a correct command.
         *
         * @param command Input command by user.
         * @return Enum value corresponding to the input command.
         * @throws IllegalArgumentException if invalid command is given.
         */
        public static Command getCommand(String command) throws IllegalArgumentException {
            for (Command c : Command.values()) {
                if (command.equals(c.inputCommand)) {
                    return c;
                }
            }
            throw new IllegalArgumentException("No such available command, " + "\"" + command + "\" please try again.");
        }
    }
}
