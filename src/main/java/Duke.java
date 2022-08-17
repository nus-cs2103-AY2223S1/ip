import java.util.ArrayList;
import java.util.Scanner;

/**
 * Duke is a Task Manager program that helps a user keep track of and manager their tasks.
 */
public class Duke {
    /**
     * Takes in commands entered by the user.
     */
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Stores the list of tasks added by the user.
     */
    private static final ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Points to the index of the tasks array to insert the next task in.
     * Also acts as a counter for the number of tasks in the tasks array.
     */
    private static int pointer = 0;

    private static void speak(String message) {
        System.out.println("============================================================\n");
        System.out.println(message);
        System.out.println("============================================================");
    }

    private static void greet() {
        speak(" Greetings! My name is Alfred ^_^\n How may I be of service today?\n");
    }

    private static void goodbye() {
        speak(" Farewell!\n");
    }

    private static void addTask(TaskType type, String task, String dateTime) {
        Task newTask;
        switch (type) {
        case TODO:
            newTask = new Todo(task);
            break;
        case DEADLINE:
            newTask = new Deadline(task, dateTime);
            break;
        case EVENT:
            newTask = new Event(task, dateTime);
            break;
        default:
            newTask = new Task(task);
        }
        tasks.add(newTask);
        pointer++;
        speak(" Understood. I have added the following task:\n"
            + "   " + newTask
            + " You have a total of " + pointer + " task(s).\n");
    }

    private static void deleteTask(int taskNum) throws DukeException {
        if (taskNum <= pointer && taskNum > 0) {
            pointer--;
            speak(" Understood. I have removed the following task:\n"
                + "   " + tasks.get(taskNum - 1)
                + " You have a total of " + pointer + " task(s).\n");
            tasks.remove(taskNum - 1);
        } else {
            throw new DukeException("Please indicate a task no. between 1 to " + pointer + ".");
        }
    }

    private static void updateTask(TaskFunc func, int taskNum) throws DukeException {
        if (taskNum <= pointer && taskNum > 0) {
            switch (func) {
            case MARK:
                speak(tasks.get(taskNum - 1).mark());
                break;
            case UNMARK:
                speak(tasks.get(taskNum - 1).unmark());
                break;
            default:
            }
        } else {
            throw new DukeException("Please indicate a task no. between 1 to " + pointer + ".");
        }
    }

    private static void listTasks() {
        if (pointer == 0) {
            speak(" You have not added any tasks!\n");
        } else {
            StringBuilder result = new StringBuilder(" Here are your current tasks:\n");
            for (int i = 0; i < pointer; i++) {
                result.append(" ").append(i + 1).append(".").append(tasks.get(i).toString());
            }
            speak(result.toString());
        }
    }

    private static void emptyList() {
        tasks.clear();
        pointer = 0;
        speak(" Understood. I have emptied your list of tasks.");
    }

    private static void parseCommand(String cmd) throws DukeException {
        String[] firstParse = cmd.split(" ", 2);
        String firstTerm = firstParse[0];
        boolean hasSecondTerm = firstParse.length > 1;

        switch (firstTerm) {
        case "bye":
            goodbye();
            break;
        case "todo":
            if (hasSecondTerm) {
                addTask(TaskType.TODO, firstParse[1], "");
                break;
            } else {
                throw new DukeException("Please provide a description for the todo.");
            }
        case "deadline":
            if (hasSecondTerm) {
                String[] secondParse = firstParse[1].split("/by", 2);
                if (secondParse.length > 1) {
                    addTask(TaskType.DEADLINE, secondParse[0], secondParse[1]);
                    break;
                } else {
                    throw new DukeException("Please provide a date/time for the deadline.");
                }
            } else {
                throw new DukeException("Please provide a description for the deadline.");
            }
        case "event":
            if (hasSecondTerm) {
                String[] secondParse = firstParse[1].split("/at", 2);
                if (secondParse.length > 1) {
                    addTask(TaskType.EVENT, secondParse[0], secondParse[1]);
                    break;
                } else {
                    throw new DukeException("Please provide a date/time for the event.");
                }
            } else {
                throw new DukeException("Please provide a description for the event.");
            }
        case "delete":
            try {
                String secondTerm = hasSecondTerm
                    ? firstParse[1].split(" ", 2)[0]
                    : "0";
                deleteTask(Integer.parseInt(secondTerm));
                break;
            } catch (NumberFormatException e) {
                throw new DukeException("Please indicate the task no. in digits.");
            } catch (DukeException f) {
                speak(f.toString());
                break;
            }
        case "mark":
            try {
                String secondTerm = hasSecondTerm
                    ? firstParse[1].split(" ", 2)[0]
                    : "0";
                updateTask(TaskFunc.MARK, Integer.parseInt(secondTerm));
                break;
            } catch (NumberFormatException e) {
                throw new DukeException("Please indicate the task no. in digits.");
            } catch (DukeException f) {
                speak(f.toString());
                break;
            }
        case "unmark":
            try {
                String secondTerm = hasSecondTerm
                    ? firstParse[1].split(" ", 2)[0]
                    : "0";
                updateTask(TaskFunc.UNMARK, Integer.parseInt(secondTerm));
                break;
            } catch (NumberFormatException e) {
                throw new DukeException("Please indicate the task no. in digits.");
            } catch (DukeException f) {
                speak(f.toString());
                break;
            }
        case "list":
            listTasks();
            break;
        case "empty":
            emptyList();
            break;
        default:
            throw new DukeException("Please enter a supported command.");
        }
    }

    /**
     * Runs the Duke program until the user exits with the 'bye' command.
     */
    public static void main(String[] args) {
        greet();

        String cmd = "";
        while (!cmd.equals("bye")) {
            cmd = scanner.nextLine().trim();

            try {
                parseCommand(cmd);
            } catch (DukeException e) {
                speak(e.toString());
            }
        }
    }

    enum TaskType {
        TODO,
        DEADLINE,
        EVENT
    }

    enum TaskFunc {
        MARK,
        UNMARK
    }
}
