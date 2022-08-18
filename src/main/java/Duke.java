import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    // Printables
    private static final String TAB = "    ";
    private static final String LINE = String
            .format("%s%s", TAB, "____________________________________________________________");
    private static final String WELCOMEMESSAGE = String
            .format("Hello! I'm Duke\n%s What can I do for you?", TAB);
    private static final String ENDMESSAGE = "Bye. Hope to see you again soon!";
    private static final String CREATEMESSAGE = "Got it. I've added this task:";
    private static final String MARKMESSAGE = "Nice! I've marked this task as done:";
    private static final String UNMARKMESSAGE = "OK, I've marked this task as not done yet:";
    private static final String LISTMESSAGE = "Here are the tasks in your list:";
    private static final String DELETEMESSAGE = "Noted. I've removed this task:";

    // Commands
    private static final String ENDCOMMAND = "bye";
    private static final String PRINTCOMMAND = "list";
    private static final String MARKCOMMAND = "mark";
    private static final String UNMARKCOMMAND = "unmark";
    private static final String TODOCOMMAND = "todo";
    private static final String DEADLINECOMMAND = "deadline";
    private static final String EVENTCOMMAND = "event";
    private static final String DELETECOMMAND = "delete";

    // Data structures
    private static final List<Task> list = new ArrayList<>();

    /**
     * Adds param str to a List.
     * @param str
     */
    private static void createTask(String str) {
        Task newTask = new Task(str);
        list.add(newTask);
        prettyPrint(String.format("added: %s", newTask.getDescription()));
    }

    private static void createTask(String str, TaskType type){
        try {

            Task newTask;
            switch(type) {
                case TODO:
                    if (str.strip().equals("")) {
                        throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                    }
                    newTask = new ToDo(str);
                    break;
                case DEADLINE:
                    if (str.strip().equals("")) {
                        throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                    }
                    String[] strList = str.split(" /by ");
                    newTask = new Deadline(strList[0], strList[1]);
                    break;
                case EVENT:
                    if (str.strip().equals("")) {
                        throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
                    }
                    strList = str.split(" /at ");
                    newTask = new Event(strList[0], strList[1]);
                    break;
                default:
                    newTask = new Task(str);
            }
            list.add(newTask);
            String singulStr = "Now you have 1 task in the list";
            if (list.size() == 1) {
                prettyPrint(String.format("%s\n%s   %s\n%s %s",
                        CREATEMESSAGE, TAB, newTask.toString(), TAB, singulStr));
            } else {
                prettyPrint(String.format("%s\n%s   %s\n%s Now you have %d tasks in the list.",
                        CREATEMESSAGE, TAB, newTask.toString(), TAB, list.size()));
            }
        } catch (DukeException e) {
            prettyPrint(e.toString());
        }
    }

    /**
     * Marks a task at index as done
     */
    private static void markTask(String index) {
        index = index.strip();
        int i = Integer.parseInt(index);
        i--;
        Task task = list.get(i);
        task.markAsDone();
        prettyPrint(String.format("%s\n%s   %s", MARKMESSAGE, TAB, task.toString()));
    }

    /**
     * Marks a task at index as not done
     */
    private static void unmarkTask(String index) {
        index = index.strip();
        int i = Integer.parseInt(index);
        i--;
        Task task = list.get(i);
        task.markAsUndone();
        prettyPrint(String.format("%s\n%s   %s", UNMARKMESSAGE, TAB, task.toString()));
    }

    private static void deleteTask(String index) {
        index = index.strip();
        int i = Integer.parseInt(index);
        i--;
        Task task = list.remove(i);
        String singulStr = "Now you have 1 task in the list";
        if (list.size() == 1) {
            prettyPrint(String.format("%s\n%s   %s\n%s %s",
                    DELETEMESSAGE, TAB, task.toString(), TAB, singulStr));
        } else {
            prettyPrint(String.format("%s\n%s   %s\n%s Now you have %d tasks in the list.",
                    DELETEMESSAGE, TAB, task.toString(), TAB, list.size()));
        }
    }

    /**
     * Creates a new Collection that has each entry numbered in ascending order
     */
    private static void printAll() {
        List<String> printables = new ArrayList<>();
        printables.add(LISTMESSAGE);
        for (int i = 0; i < list.size(); i++) {
            Task task = list.get(i);
            int index = i + 1;
            printables.add(String.format("%d.%s", index, task.toString()));
        }
        prettyPrint(printables);
    }

    /**
     * Printing empty list
     */
    private static void prettyPrint() {
        prettyPrint("");
    }

    /**
     * Printing when everything has already been formatted into a single entry
     * @param printable
     */
    private static void prettyPrint(String printable) {
        String out = String.format("%s\n%s %s\n%s", LINE, TAB, printable, LINE);
        System.out.println(out);
    }

    /**
     * Printing when items are still in a List.
     * @param printables
     */
    private static void prettyPrint(List<String> printables) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < printables.size(); i++) {
            String s = printables.get(i);
            if (i == 0) {
                sb.append(s);
            }
            else {
                sb.append(String.format("\n%s %s", TAB, s));
            }
        }
        String printable = sb.toString();
        prettyPrint(printable);
    }

    public static void main(String[] args) throws DukeException {
        Scanner sc = new Scanner(System.in);
        prettyPrint(WELCOMEMESSAGE);
        boolean canNext = true;
        while (canNext) {
            try {
                String inputCmd = sc.next();
                String inputRem = sc.nextLine();
                switch (inputCmd) {
                    case (ENDCOMMAND):
                        canNext = false;
                        break;
                    case (PRINTCOMMAND):
                        printAll();
                        break;
                    case (MARKCOMMAND):
                        markTask(inputRem);
                        break;
                    case (UNMARKCOMMAND):
                        unmarkTask(inputRem);
                        break;
                    case (TODOCOMMAND):
                        createTask(inputRem, TaskType.TODO);
                        break;
                    case (DEADLINECOMMAND):
                        createTask(inputRem, TaskType.DEADLINE);
                        break;
                    case (EVENTCOMMAND):
                        createTask(inputRem, TaskType.EVENT);
                        break;
                    case (DELETECOMMAND):
                        deleteTask(inputRem);
                        break;
                    default: throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                prettyPrint(e.toString());
            }

        }
        prettyPrint(ENDMESSAGE);
    }
}
