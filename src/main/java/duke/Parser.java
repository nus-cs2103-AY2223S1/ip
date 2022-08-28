package duke;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Deals with making sense of the user command.
 */
public class Parser {

    private static Ui ui;
    private static TaskList tasklist;
    private Storage storage;

    public Parser(Ui ui, TaskList tasklist, Storage storage) {
        this.ui = ui;
        this.tasklist = tasklist;
        this.storage = storage;
    }

    /**
     * Parses user input to determine what Duke method to call.
     *
     * @param userInput input entered by user.
     * @throws DukeException If user fails to specify what task they want to do.
     */
    public void parse(String userInput) throws DukeException {
        if (userInput.equals("list")) {
            showList();
        } else if (userInput.length() > 4 && (userInput.substring(0, 4)).equals("mark")) {
            markDone(userInput);
            storage.save(tasklist);
        } else if (userInput.length() > 6 && (userInput.substring(0, 6)).equals("unmark")) {
            markUndone(userInput);
            storage.save(tasklist);
        } else if (userInput.length() > 4 && (userInput.substring(0, 4)).equals("todo")) {
            addToDo(userInput);
            storage.save(tasklist);
        } else if (userInput.equals("todo")) {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
        } else if (userInput.equals("deadline")) {
            throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
        } else if (userInput.equals("event")) {
            throw new DukeException("OOPS!!! The description of a event cannot be empty.");
        } else if (userInput.length() > 8 && (userInput.substring(0, 8)).equals("deadline")) {
            addDeadline(userInput);
            storage.save(tasklist);
        } else if (userInput.length() > 5 && (userInput.substring(0, 5)).equals("event")) {
            addEvent(userInput);
            storage.save(tasklist);
        } else if (userInput.length() > 7 && (userInput.substring(0, 6)).equals("delete")) {
            deleteTask(userInput);
            storage.save(tasklist);
        } else if (userInput.startsWith("find ")) {
            findTask(userInput);
        } else {
            System.out.println("I'm sorry, but I don't know what that means! Try typing something else!");
        }
    }

    /**
     * Displays list of tasks to user.
     */
    public static void showList(){
        ui.printList(tasklist.listOfTasks());
    }

    /**
     * Marks tasks as done.
     *
     * @param task The string containing which task to be marked.
     */
    public static void markDone(String task) {
        int taskToMark = 0;
        String strTaskToMark = "";

        for (int j = 5; j < task.length(); j++) {
            strTaskToMark = strTaskToMark + task.charAt(j);
        }
        taskToMark = Integer.parseInt(strTaskToMark);
        ui.printDone(tasklist.mark(taskToMark - 1));
    }

    /**
     * Changes status of task back to not done.
     *
     * @param task The string containing which task to be unmarked.
     */
    public static void markUndone(String task) {
        int taskToUnmark = 0;
        String strTaskToUnmark = "";

        for (int j = 7; j < task.length(); j++) {
            strTaskToUnmark = strTaskToUnmark + task.charAt(j);
        }

        taskToUnmark = Integer.parseInt(strTaskToUnmark);
        ui.printUndone(tasklist.unmark(taskToUnmark - 1));
    }

    /**
     * Adds Todo tasks.
     *
     * @param str The string containing task to be added.
     */
    public static void addToDo(String str) {
        ToDos newToDo = new ToDos(str.substring(5, str.length()));
        ui.printTodo(tasklist.addTask(newToDo));
        ui.printTasksLeft(tasklist.getSize());
    }

    /**
     * Adds Deadline tasks.
     *
     * @param str The string containing task to be added.
     */
    public static void addDeadline(String str) {
        int k = 9;
        String desc = "";

        while (str.charAt(k) != '/') {
            desc += str.charAt(k);
            k++;
        }

        String date = str.substring(k + 4, str.length());
        Deadlines newDeadline = new Deadlines(desc, LocalDateTime.parse(date));
        ui.printTodo(tasklist.addTask(newDeadline));
        ui.printTasksLeft(tasklist.getSize());
    }

    /**
     * Adds Event tasks.
     *
     * @param str The string containing task to be added.
     */
    public static void addEvent(String str) {
        int k = 6;
        String desc = "";

        while (str.charAt(k) != '/') {
            desc += str.charAt(k);
            k++;
        }

        String eventTime = str.substring(k + 4, str.length());
        Events newEvent = new Events(desc, LocalDateTime.parse(eventTime));
        ui.printTodo(tasklist.addTask(newEvent));
        ui.printTasksLeft(tasklist.getSize());
    }

    /**
     * Deletes task.
     *
     * @param str The string specifying which task to be deleted.
     */
    public static void deleteTask(String str) {
        int taskToDel = 0;
        String strTaskToDel = "";
        for (int j = 7; j < str.length(); j++) {
            strTaskToDel = strTaskToDel + str.charAt(j);
        }
        taskToDel = Integer.parseInt(strTaskToDel) - 1;

        ui.printDelete(tasklist.getTask(taskToDel));
        tasklist.deleteTask(taskToDel);
        ui.printTasksLeft(tasklist.getSize());
    }

    /**
     * Finds task with matching keyword
     *
     * @param str User input
     */
    public static void findTask(String str) {
        String keyword = str.substring(5);
        ArrayList<Task> matchedTasks = new ArrayList<>();

        int i = 0;
        while (i != tasklist.getSize()) {
            if (tasklist.getTask(i).description.contains(keyword)) {
                matchedTasks.add(tasklist.getTask(i));
            }
            i++;
        }

        ui.printFind(matchedTasks);
    }
}