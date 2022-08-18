import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final String LINE = "    ____________________________________________________________";
    private static final String INDENTATION = "     ";
    private static ArrayList<Task> taskList = new ArrayList<>();

    private static boolean isDone = false;

    public static void main(String[] args) {
        greet();
        Scanner sc = new Scanner(System.in);

        while (!isDone) {
            try {
                String command = sc.nextLine();
                if (command.equals("bye")) {
                    bye();
                } else if (command.equals("list")) {
                    displayList();
                } else if (command.startsWith("mark")) {
                    String[] splitCommand = command.split("\\s+",2);
                    if (!splitCommand[0].equals("mark")) {
                        throw new DukeInvalidCommandException(splitCommand[0]);
                    }
                    if (splitCommand.length < 2) {
                        throw new DukeEmptyDescriptionException("mark");
                    }
                    try {
                        String markItem = splitCommand[1];
                        int itemNumber = Integer.parseInt(markItem);
                        mark(itemNumber);
                    } catch (NumberFormatException nfe) {
                        throw new DukeNumberFormatException();
                    }
                } else if (command.startsWith("unmark")) {
                    String[] splitCommand = command.split("\\s+",2);
                    if (!splitCommand[0].equals("unmark")) {
                        throw new DukeInvalidCommandException(splitCommand[0]);
                    }
                    if (splitCommand.length < 2) {
                        throw new DukeEmptyDescriptionException("unmark");
                    }
                    try {
                        String unmarkItem = splitCommand[1];
                        int itemNumber = Integer.parseInt(unmarkItem);
                        unmark(itemNumber);
                    } catch (NumberFormatException nfe) {
                        throw new DukeNumberFormatException();
                    }
                } else if (command.startsWith("todo")) {
                    String[] splitCommand = command.split("\\s+",2);
                    if (!splitCommand[0].equals("todo")) {
                        throw new DukeInvalidCommandException(splitCommand[0]);
                    }
                    if (splitCommand.length < 2) {
                        throw new DukeEmptyDescriptionException("todo");
                    }
                    String desc = splitCommand[1];
                    addTodo(desc);
                } else if (command.startsWith("deadline")) {
                    String[] splitCommand = command.split("\\s+",2);
                    if (!splitCommand[0].equals("deadline")) {
                        throw new DukeInvalidCommandException(splitCommand[0]);
                    }
                    if (splitCommand.length < 2) {
                        throw new DukeEmptyDescriptionException("deadline");
                    }
                    String desc = splitCommand[1];
                    addDeadline(desc);
                } else if (command.startsWith("event")) {
                    String[] splitCommand = command.split("\\s+",2);
                    if (!splitCommand[0].equals("event")) {
                        throw new DukeInvalidCommandException(splitCommand[0]);
                    }
                    if (splitCommand.length < 2) {
                        throw new DukeEmptyDescriptionException("event");
                    }
                    String desc = splitCommand[1];
                    addEvent(desc);
                } else {
                    throw new DukeInvalidCommandException(command);
                }
            } catch (DukeException de) {
                displayLine();
                System.out.println(INDENTATION + de.getMessage());
                displayLine();
            }
        }
    }

    private static void exitProgram() {
        isDone = true;
    }

    private static void displayLine() {
        System.out.println(LINE);
    }


    private static void printMessage(String message) {
        displayLine();
        System.out.println(message);
        displayLine();
    }

    private static void greet() {
        String greetingMessage = INDENTATION + "Hello! I'm Duke\n" +
                INDENTATION + "What can I do for you?\n";
        printMessage(greetingMessage);
    }

    private static void bye() {
        String byeMessage = INDENTATION + "Bye. Hope to see you again soon!\n";
        printMessage(byeMessage);
        exitProgram();
    }

    private static void addList(Task input) {
        taskList.add(input);
    }

    private static void displayList() {
        displayLine();
        String listMessage = INDENTATION + "Here are the tasks in your list:";
        System.out.println(listMessage);
        int len = taskList.size();
        for (int i = 0; i < len; i++) {
            int orderList = i + 1;
            String message = INDENTATION + orderList + ". " + taskList.get(i).toString();
            System.out.println(message);
        }
        displayLine();
    }

    private static void mark(int itemNumber) throws DukeException {
        int listLen = taskList.size();
        if (itemNumber < 1 || itemNumber > listLen) {
            throw new DukeOutOfBoundsException(1, listLen);
        }
        int index = itemNumber - 1;
        Task markedTask = taskList.get(index);
        markedTask.markAsDone();
        displayLine();
        String markMessage = INDENTATION + "Nice! I've marked this task as done:";
        System.out.println(markMessage);
        System.out.println(INDENTATION + markedTask.toString());
        displayLine();
    }

    private static void unmark(int itemNumber) throws DukeException {
        int listLen = taskList.size();
        if (itemNumber < 1 || itemNumber > listLen) {
            throw new DukeOutOfBoundsException(1, listLen);
        }
        int index = itemNumber - 1;
        Task unmarkedTask = taskList.get(index);
        unmarkedTask.unmarkAsDone();
        displayLine();
        String unmarkMessage = INDENTATION + "OK, I've marked this task as not done yet:";
        System.out.println(unmarkMessage);
        System.out.println(INDENTATION + unmarkedTask.toString());
        displayLine();
    }

    private static void displayAddTask(Task taskAdded) {
        displayLine();
        System.out.println(INDENTATION + "Got it. I've added this task:");
        System.out.println(INDENTATION + taskAdded.toString());
        System.out.println(INDENTATION + "Now you have " + taskList.size() + " tasks in the list.");
        displayLine();
    }

    private static void addTodo(String description) {
        ToDo todo = new ToDo(description);
        addList(todo);
        displayAddTask(todo);
    }

    private static void addDeadline(String description) throws DukeException {
        String[] splitDescription = description.split(" /by ", 2);
        if (splitDescription[0].equals(description)) {
            throw new DukeMissingSpecifierException("deadline", " /by ");
        }
        String instruction = splitDescription[0];
        String by = splitDescription[1];
        Deadline deadline = new Deadline(instruction, by);
        addList(deadline);
        displayAddTask(deadline);
    }

    private static void addEvent(String description) throws DukeException {
        String[] splitDescription = description.split(" /at ", 2);
        if (splitDescription[0].equals(description)) {
            throw new DukeMissingSpecifierException("event", " /at ");
        }
        String instruction = splitDescription[0];
        String at = splitDescription[1];
        Event event = new Event(instruction, at);
        addList(event);
        displayAddTask(event);
    }
}
