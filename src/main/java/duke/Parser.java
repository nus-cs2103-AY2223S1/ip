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
    public String parse(String userInput) throws DukeException {
        if (userInput.equals("list")) {
            return showList();
        } else if (userInput.startsWith("mark ")) {
            int taskNum = Integer.parseInt(String.valueOf(userInput.charAt(5)));
            if (taskNum <= tasklist.getSize() && taskNum > 0) {
                return markDone(userInput);
                //storage.save(tasklist);
            } else {
                throw new DukeException("An invalid number is inputted!");
            }
        } else if (userInput.startsWith("unmark ")) {
            int taskNum = Integer.parseInt(String.valueOf(userInput.charAt(7)));
            if (taskNum <= tasklist.getSize() && taskNum > 0) {
                return markUndone(userInput);
                //storage.save(tasklist);
            } else {
                throw new DukeException("An invalid number is inputted!");
            }
        } else if (userInput.startsWith("todo")) {
            if (userInput.length() > 5) {
                return addToDo(userInput);
                //storage.save(tasklist);
            } else {
                throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
            }
        } else if (userInput.startsWith("deadline")) {
            if (userInput.length() > 10) {
                return addDeadline(userInput);
                //storage.save(tasklist);
            } else {
                throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
            }
        } else if (userInput.startsWith("event")) {
            if (userInput.length() > 6) {
                return addEvent(userInput);
                //storage.save(tasklist);
            } else {
                throw new DukeException("OOPS!!! The description of an event cannot be empty.");
            }
        } else if (userInput.length() > 7 && userInput.startsWith("delete")) {
            return deleteTask(userInput);
            //storage.save(tasklist);
        } else if (userInput.startsWith("find ")) {
            return findTask(userInput);
        } else if (userInput.equals("bye")) {
            storage.save(tasklist);
            return ui.printGoodbyeMessage();
        } else {
            //System.out.println("I'm sorry, but I don't know what that means! Try typing something else!");
            return "I'm sorry, but I don't know what that means! Try typing something else!";
        }
    }

    /**
     * Displays list of tasks to user.
     */
    public static String showList(){
        //ui.printList(tasklist.getListOfTasks());
        return ui.printList(tasklist);
    }

    /**
     * Marks tasks as done.
     *
     * @param task The string containing which task to be marked.
     */
    public static String markDone(String task) {
        int taskToMark = 0;
        String strTaskToMark = "";

        for (int j = 5; j < task.length(); j++) {
            strTaskToMark = strTaskToMark + task.charAt(j);
        }
        taskToMark = Integer.parseInt(strTaskToMark);
        return ui.printDone(tasklist.mark(taskToMark - 1));
    }

    /**
     * Changes status of task back to not done.
     *
     * @param task The string containing which task to be unmarked.
     */
    public static String markUndone(String task) {
        int taskToUnmark = 0;
        String strTaskToUnmark = "";

        for (int j = 7; j < task.length(); j++) {
            strTaskToUnmark = strTaskToUnmark + task.charAt(j);
        }

        taskToUnmark = Integer.parseInt(strTaskToUnmark);
        return ui.printUndone(tasklist.unmark(taskToUnmark - 1));
    }

    /**
     * Adds Todo tasks.
     *
     * @param str The string containing task to be added.
     */
    public static String addToDo(String str) {
        String finalStr = "";
        ToDos newToDo = new ToDos(str.substring(5));
        finalStr += ui.printTodo(tasklist.addTask(newToDo)) + "\n" + ui.printTasksLeft(tasklist.getSize());
        return finalStr;
    }

    /**
     * Adds Deadline tasks.
     *
     * @param str The string containing task to be added.
     */
    public static String addDeadline(String str) {
        String desc = "";

        int k = 9;
        while (str.charAt(k) != '/') {
            desc += str.charAt(k);
            k++;
        }

        String date = str.substring(k + 4);
        Deadlines newDeadline = new Deadlines(desc, LocalDateTime.parse(date));

        String finalStr = "";
        finalStr += ui.printTodo(tasklist.addTask(newDeadline)) + "\n" + ui.printTasksLeft(tasklist.getSize());
        return finalStr;
    }

    /**
     * Adds Event tasks.
     *
     * @param str The string containing task to be added.
     */
    public static String addEvent(String str) {
        String desc = "";

        int k = 6;
        while (str.charAt(k) != '/') {
            desc += str.charAt(k);
            k++;
        }

        String eventTime = str.substring(k + 4);
        Events newEvent = new Events(desc, LocalDateTime.parse(eventTime));

        String finalStr = "";
        finalStr += ui.printTodo(tasklist.addTask(newEvent)) + "\n"+ ui.printTasksLeft(tasklist.getSize());
        return finalStr;
    }

    /**
     * Deletes task.
     *
     * @param str The string specifying which task to be deleted.
     */
    public static String deleteTask(String str) {
        int taskToDel = 0;
        String strTaskToDel = "";
        for (int j = 7; j < str.length(); j++) {
            strTaskToDel = strTaskToDel + str.charAt(j);
        }
        taskToDel = Integer.parseInt(strTaskToDel) - 1;

        String finalStr = "";
        finalStr += ui.printDelete(tasklist.getTask(taskToDel));
        tasklist.deleteTask(taskToDel);
        finalStr += ui.printTasksLeft(tasklist.getSize());
        return finalStr;
    }

    /**
     * Finds task with matching keyword
     *
     * @param str User input
     */
    public static String findTask(String str) {
        String keyword = str.substring(5);
        ArrayList<Task> matchedTasks = new ArrayList<>();

        int i = 0;
        while (i != tasklist.getSize()) {
            if (tasklist.getTask(i).description.contains(keyword)) {
                matchedTasks.add(tasklist.getTask(i));
            }
            i++;
        }

        return ui.printFind(matchedTasks);
    }
}