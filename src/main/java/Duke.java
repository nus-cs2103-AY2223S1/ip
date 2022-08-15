import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.concurrent.DelayQueue;

import static java.lang.Integer.parseInt;

public class Duke {

    public enum Command {BYE, LIST, MARK, UNMARK, TODO, EVENT, DEADLINE, DELETE}

    private static final String LOGO = " ____        _        \n"
                                       + "|  _ \\ _   _| | _____ \n"
                                       + "| | | | | | | |/ / _ \\\n"
                                       + "| |_| | |_| |   <  __/\n"
                                       + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String WELCOME_GREET = "Hello there! I am\n" + LOGO
                + "\nyour personal task tracking assistant!\nWhat can I do for you today?\n";
    private static final String EXIT_GREET = "Bye. Hope to see you again soon!";
    private static final LinkedList<Task> TASK_LIST = new LinkedList<>();

    private static void welcomeGreet() {
        System.out.println(WELCOME_GREET);
    }
    public static void exitGreet() {
       System.out.println(EXIT_GREET);
    }

    //General Helpers
    public static boolean isNumeric(String str) {
        return str.chars().allMatch(Character::isDigit);
    }
    public static int timeFinder(String[] splitInput) {
        for (int j = 0; j < splitInput.length; j++) {
            if (splitInput[j].equals("/by") || splitInput[j].equals("/at")) {
                return j;
            }
        }
        return -1;
    }
    //

    // List Helpers
    public static void printList() {
        System.out.println("My List Of Tasks :D");
        for (int i = 0; i < TASK_LIST.size(); i++) {
            int index = i + 1;
            System.out.println(index + ". " + TASK_LIST.get(i).toString());
        }
        System.out.println("\n");
    }
    public static void taskTracker(int index, Command cmd) {
        if (index >= TASK_LIST.size() || index < 0) {
            throw new DukeException("Your task list currently does not have a task at this index.\n");
        }
        Task task = TASK_LIST.get(index);
        switch (cmd) {
            case MARK:
                task.done();
                break;
            case UNMARK:
                task.notDone();
                break;
            case DELETE:
                TASK_LIST.remove(index);
                System.out.println("Noted. I've removed this task:\n "
                                    + task.toString()
                                    + "\nNow you have " + TASK_LIST.size() + " tasks in the list.\n");
        }
    }
    public static void taskAdder(String[] userInput, Command taskType) {
        int len = userInput.length;
        Task task = null;

        switch (taskType) {
            case TODO:
                String todoName = String.join(" ", Arrays.copyOfRange(userInput, 1, len));
                if (todoName.equals("")) {
                    throw new DukeException("OOPS!!! The description of a todo cannot be empty.\n");
                }
                task = new Todo(todoName);
                break;
            case DEADLINE:
                int byIndex = timeFinder(userInput);
                if (byIndex == -1) {
                    throw new DukeException("OOPS!!! A deadline task needs a deadline date/time. Use the /by command after the name of the task to set its deadline\n");
                }
                String deadlineName = String.join(" ", Arrays.copyOfRange(userInput, 1, byIndex));
                if (deadlineName.equals("")) {
                    throw new DukeException("OOPS!!! The description of a deadline task cannot be empty.\n");
                }
                String by = String.join(" ", Arrays.copyOfRange(userInput, byIndex + 1, len));
                if (by.equals("")) {
                    throw new DukeException("OOPS!!! The deadline of the task cannot be empty.\n");
                }
                task = new Deadline(deadlineName, by);
                break;
            case EVENT:
                int atIndex = timeFinder(userInput);
                if (atIndex == -1) {
                    throw new DukeException("OOPS!!! An event task needs a at date/time. Use the /at command after the name of the task to set its date/time.\n");
                }
                String eventName = String.join(" ", Arrays.copyOfRange(userInput, 1, atIndex));
                if (eventName.equals("")) {
                    throw new DukeException("OOPS!!! The description of an event cannot be empty.\n");
                }
                String at = String.join(" ", Arrays.copyOfRange(userInput, atIndex + 1, len));
                if (at.equals("")) {
                    throw new DukeException("OOPS!!! The date/time of the event cannot be empty.\n");
                }
                task = new Event(eventName, at);
                break;
        }
        TASK_LIST.add(task);
        System.out.println("Got it. I've added this task:\n "
                + task.toString()
                + "\nNow you have " + TASK_LIST.size() + " tasks in the list.\n");
    }
    //

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        welcomeGreet();

        while (scanner.hasNext()) {
            String userInput = scanner.nextLine();
            String[] splitInput = userInput.split(" ");
            int len = splitInput.length;

            try {
                if (userInput.equals("bye")) {
                    exitGreet();
                    scanner.close();
                    break;
                } else if (userInput.equals("list")) {
                    printList();
                } else if (len == 2 && splitInput[0].equals("mark") && isNumeric(splitInput[1])) {
                    taskTracker(parseInt(splitInput[1]) - 1, Command.MARK);
                } else if (len == 2 && splitInput[0].equals("unmark") && isNumeric(splitInput[1])) {
                    taskTracker(parseInt(splitInput[1]) - 1, Command.UNMARK);
                } else if (splitInput[0].equals("mark") || splitInput[0].equals("unmark") && len == 1) {
                    throw new DukeException("To check off tasks, indicate the index of the task correctly using an integer!\n");
                } else if (len == 2 && splitInput[0].equals("delete") && isNumeric(splitInput[1])) {
                    taskTracker(parseInt(splitInput[1]) - 1, Command.DELETE);
                } else if (splitInput[0].equals("delete") && splitInput.length == 1) {
                    throw new DukeException("To delete tasks, indicate the index of the task correctly using an integer!\n");
                } else if (splitInput[0].equals("todo")) {
                    taskAdder(splitInput, Command.TODO);
                } else if (splitInput[0].equals("deadline")) {
                    taskAdder(splitInput, Command.DEADLINE);
                } else if (splitInput[0].equals("event")) {
                    taskAdder(splitInput, Command.EVENT);
                } else {
                    throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(\n");
                }
            } catch (DukeException error) {
                System.out.println(error.getMessage());
            }
        }
    }
}
