import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.concurrent.DelayQueue;

import static java.lang.Integer.parseInt;

public class Duke {

    //Duke Start
    protected static final String LOGO = " ____        _        \n"
                                       + "|  _ \\ _   _| | _____ \n"
                                       + "| | | | | | | |/ / _ \\\n"
                                       + "| |_| | |_| |   <  __/\n"
                                       + "|____/ \\__,_|_|\\_\\___|\n";
    protected static final String WELCOME_GREET = "Hello from\n" + LOGO
                + "\nI am you personal task tracking assistant!\nWhat can I do for you today?\n";
    protected static final LinkedList<Task> TASK_LIST = new LinkedList<>();

    public static void welcomeGreet() {
        System.out.println(WELCOME_GREET);
    }
    public static void dukeExit() {
       System.out.println("Bye. Hope to see you again soon!");
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

    // List Helpers
    public static void printList() {
        System.out.println("My List Of Tasks :D");
        for (int i = 0; i < TASK_LIST.size(); i++) {
            int index = i + 1;
            System.out.println(index + ". " + TASK_LIST.get(i).toString());
        }
        System.out.println("\n");
    }
    public static void markAsDone(int index) {
        if (index > TASK_LIST.size()) {
            throw new DukeException("Your task list currently does not have a task at this index.\n");
        }
        Task task = TASK_LIST.get(index-1);
        task.markAsDone();
    }
    public static void markAsNotDone(int index) {
        if (index > TASK_LIST.size()) {
            throw new DukeException("Your task list currently does not have a task at this index.\n");
        }
        Task task = TASK_LIST.get(index-1);
        task.markAsNotDone();
    }

    // Task Helpers
    public static void addTask(Task task) {
        TASK_LIST.add(task);
        System.out.println("Got it. I've added this task:\n "
                + task.toString()
                + "\nNow you have " + Task.getNumberOfTasks() + " tasks in the list.\n");
    }
    public static void addTodo(String[] userInput) {
        int len = userInput.length;
        String todoName = String.join(" ", Arrays.copyOfRange(userInput, 1, len));
        if (todoName.equals("")) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.\n");
        }
        addTask(new Todo(todoName));
    }
    public static void addDeadline(String[] userInput) {
        int byIndex = timeFinder(userInput);
        if (byIndex == -1) {
            throw new DukeException("☹ OOPS!!! A deadline task needs a deadline date/time. Use the /by command after the name of the task to set its deadline\n");
        }
        String deadlineName = String.join(" ", Arrays.copyOfRange(userInput, 1, byIndex));
        if (deadlineName.equals("")) {
            throw new DukeException("☹ OOPS!!! The description of a deadline task cannot be empty.\n");
        }
        String by = String.join(" ", Arrays.copyOfRange(userInput, byIndex + 1, userInput.length));
        if (by.equals("")) {
            throw new DukeException("☹ OOPS!!! The deadline of the task cannot be empty.\n");
        }
        addTask(new Deadline(deadlineName, by));
    }
    public static void addEvent(String[] userInput) {
        int atIndex = timeFinder(userInput);
        if (atIndex == -1) {
            throw new DukeException("☹ OOPS!!! An event task needs a at date/time. Use the /at command after the name of the task to set its date/time.\n");
        }
        String eventName = String.join(" ", Arrays.copyOfRange(userInput, 1, atIndex));
        if (eventName.equals("")) {
            throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.\n");
        }
        String at = String.join(" ", Arrays.copyOfRange(userInput, atIndex + 1, userInput.length));
        if (at.equals("")) {
            throw new DukeException("☹ OOPS!!! The date/time of the event cannot be empty.\n");
        }
        addTask(new Event(eventName, at));
    }

    /*

    public static void echo() {
        LinkedList<Task> storedList = new LinkedList<>();
        Scanner scanner = new Scanner(System.in);
        String userCommand = scanner.nextLine();

        while (!"bye".equals(userCommand)) {
            if ("list".equals(userCommand)) {
                printList(storedList);
            } else if (userCommand.startsWith("mark ")) {
                int indexNo = parseInt(userCommand.substring(5)) - 1;
                System.out.println(storedList.get(indexNo).markAsDone() + "\n");
            } else if (userCommand.startsWith("unmark ")) {
                int indexNo = parseInt(userCommand.substring(7)) - 1;
                System.out.println(storedList.get(indexNo).markAsNotDone() + "\n");
            } else if (userCommand.startsWith("todo ")) {
                storedList.add(new Todo(userCommand.substring(5)));
                System.out.println(storedList.getLast().addedString() + "\n");
            } else if (userCommand.startsWith("deadline ")) {
                int index = userCommand.indexOf("/by ");
                Integer by = index + 4;
                storedList.add(new Deadline(userCommand.substring(9, index-1), userCommand.substring(by)));
                System.out.println(storedList.getLast().addedString() + "\n");
            } else if (userCommand.startsWith("event ")) {
                int index = userCommand.indexOf("/at ");
                Integer at = index + 4;
                storedList.add(new Event(userCommand.substring(6,  index-1), userCommand.substring(at)));
                System.out.println(storedList.getLast().addedString() + "\n");
            } else {
                throw new DukeException(userCommand);
                //storedList.add(new Task(userCommand));
                //System.out.println(storedList.getLast().addedString() + "\n");
            }
            userCommand = scanner.nextLine();
        }
        dukeExit();
    }
     */

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        welcomeGreet();

        while (scanner.hasNext()) {
            String userInput = scanner.nextLine();
            String[] splitInput = userInput.split(" ");
            try {
                if (userInput.equals("bye")) {
                    dukeExit();
                    scanner.close();
                    break;
                } else if (userInput.equals("list")) {
                    printList();
                } else if (splitInput.length == 2 && splitInput[0].equals("mark") && isNumeric(splitInput[1])) {
                    markAsDone(parseInt(splitInput[1]));
                } else if (splitInput.length == 2 && splitInput[0].equals("unmark") && isNumeric(splitInput[1])) {
                    markAsNotDone(parseInt(splitInput[1]));
                } else if (splitInput[0].equals("mark") || splitInput[0].equals("unmark") && splitInput.length == 1) {
                    throw new DukeException("To check off tasks, indicate the index of task correctly using an integer!\n");
                } else if (splitInput[0].equals("todo")) {
                    addTodo(splitInput);
                } else if (splitInput[0].equals("deadline")) {
                    addDeadline(splitInput);
                } else if (splitInput[0].equals("event")) {
                    addEvent(splitInput);
                } else {
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n");
                }
            } catch (DukeException error) {
                System.out.println(error.getMessage());
            }
        }
    }
}
