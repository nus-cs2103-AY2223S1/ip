import java.util.ArrayList;
import java.util.Scanner;

/**
 * Duke is a Task Manager program that helps a user keep track of and manage their tasks.
 */
public class Duke {
    /** Stores the list of tasks added by the user. */
    private final ArrayList<Task> tasks;

    /**
     * Points to the index of the tasks array to insert the next task in.
     * Also acts as a counter for the number of tasks in the tasks array.
     */
    private int pointer;

    private final Storage storage = new Storage("data/list.txt");

    /**
     * Constructor for a Duke bot.
     */
    public Duke() {
        tasks = storage.load();
        pointer = tasks.size();
        greet();
    }

    private void speak(String message) {
        System.out.println("============================================================\n");
        System.out.println(message);
        System.out.println("============================================================");
    }

    private void greet() {
        speak(" Greetings! My name is Alfred ^_^\n How may I be of service today?\n");
    }

    private void goodbye() {
        speak(" Farewell!\n");
    }

    private void addTask(TaskType type, String task, String dateTime) {
        Task newTask = null;
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
        }
        tasks.add(newTask);
        storage.addTask(newTask);
        pointer++;
        speak(" Understood. I have added the following task:\n"
            + "   " + newTask
            + " You now have a total of " + pointer + " task(s).\n");
    }

    private void deleteTask(int taskNum) throws DukeException {
        if (taskNum <= pointer && taskNum > 0) {
            pointer--;
            speak(" Understood. I have removed the following task:\n"
                + "   " + tasks.get(taskNum - 1)
                + " You have a total of " + pointer + " task(s) left.\n");
            tasks.remove(taskNum - 1);
            storage.deleteTask(taskNum - 1);
        } else if (pointer == 0) {
            throw new DukeException("You have no tasks to delete.");
        } else {
            throw new DukeException("Please indicate a task no. between 1 to " + pointer + ".");
        }
    }

    private void updateTask(TaskAction func, int taskNum) throws DukeException {
        if (taskNum <= pointer && taskNum > 0) {
            switch (func) {
            case MARK:
                speak(tasks.get(taskNum - 1).setDone(true));
                break;
            case UNMARK:
                speak(tasks.get(taskNum - 1).setDone(false));
                break;
            default:
            }
            storage.updateTask(tasks.get(taskNum - 1), taskNum - 1);
        } else if (pointer == 0) {
            throw new DukeException("You have no tasks to mark or unmark.");
        } else {
            throw new DukeException("Please indicate a task no. between 1 to " + pointer + ".");
        }
    }

    private void listTasks() {
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

    private void emptyList() {
        tasks.clear();
        storage.emptyList();
        pointer = 0;
        speak(" Understood. I have emptied your list of tasks.");
    }

    private void parseCommand(String cmd) throws DukeException {
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
                updateTask(TaskAction.MARK, Integer.parseInt(secondTerm));
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
                updateTask(TaskAction.UNMARK, Integer.parseInt(secondTerm));
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
    public void run() {
        Scanner scanner = new Scanner(System.in);
        String cmd = "";
        while (!cmd.equals("bye")) {
            cmd = scanner.nextLine().trim();

            try {
                parseCommand(cmd);
            } catch (DukeException e) {
                speak(e.toString());
            }
        }
        scanner.close();
    }

    /**
     * Driver method for Duke.
     */
    public static void main(String[] args) {
        new Duke().run();
    }

    enum TaskType {
        TODO,
        DEADLINE,
        EVENT
    }

    enum TaskAction {
        MARK,
        UNMARK
    }
}
