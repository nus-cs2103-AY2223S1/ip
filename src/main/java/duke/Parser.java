package duke;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Deals with making sense of the user command.
 */
public class Parser {

    private static Ui ui;
    private static TaskList tasklist;
    private static Storage storage;

    public Parser(Ui ui, TaskList tasklist, Storage storage) {
        this.ui = ui;
        this.tasklist = tasklist;
        this.storage = storage;
    }

    /**
     * Parses user input to determine what Duke method to call.
     *
     * @param userInput Input entered by user.
     * @return String of message to be displayed to user.
     * @throws DukeException If user fails to specify what task they want to do.
     */
    public String parse(String userInput) throws DukeException {
        if (userInput.equals("list")) {
            return showList();
        } else if (userInput.startsWith("mark ")) {
            return markDone(userInput);
        } else if (userInput.startsWith("unmark ")) {
            return markUndone(userInput);
        } else if (userInput.startsWith("todo")) {
            return addToDo(userInput);
        } else if (userInput.startsWith("deadline")) {
            return addDeadline(userInput);
        } else if (userInput.startsWith("event")) {
            return addEvent(userInput);
        } else if (userInput.startsWith("delete ")) {
            return deleteTask(userInput);
        } else if (userInput.startsWith("find ")) {
            return findTask(userInput);
        } else if (userInput.equals("count todo")) {
            return countTasks("todo");
        } else if (userInput.equals("count deadline")) {
            return countTasks("deadline");
        } else if (userInput.equals("count event")) {
            return countTasks("event");
        } else if (userInput.equals("bye")) {
            return ui.printGoodbyeMessage();
        } else {
            return "I'm sorry, but I don't know what that means. Try typing something else!";
        }
    }

    /**
     * Displays list of tasks to user.
     *
     * @return String of list.
     */
    public static String showList(){
        return ui.printList(tasklist);
    }

    /**
     * Marks tasks as done.
     *
     * @param userInput String containing user input.
     * @return String of task that is marked.
     */
    public static String markDone(String userInput) throws DukeException {
        int indexInStr = 5;
        int taskNum = Integer.parseInt(String.valueOf(userInput.charAt(indexInStr)));
        if (taskNum <= tasklist.getSize()) {
            Task task = tasklist.mark(taskNum - 1);
            assert task.isDone : "task should be marked done";
            storage.save(tasklist);
            return ui.printDone(tasklist.mark(taskNum - 1));
        } else {
            throw new DukeException("An invalid number is inputted!");
        }
    }

    /**
     * Changes status of task back to not done.
     *
     * @param userInput The string containing the user input.
     * @return String of task that is unmarked.
     */
    public static String markUndone(String userInput) throws DukeException {
        int indexInStr = 7;
        int taskNum = Integer.parseInt(String.valueOf(userInput.charAt(indexInStr)));
        if (taskNum <= tasklist.getSize()) {
            Task task = tasklist.unmark(taskNum - 1);
            assert task.isDone : "task should be marked as not done";
            storage.save(tasklist);
            return ui.printUndone(tasklist.unmark(taskNum - 1));
        } else {
            throw new DukeException("An invalid number is inputted!");
        }
    }

    /**
     * Adds Todo tasks.
     *
     * @param str The string containing task to be added.
     * @return String of todo that is added to list.
     */
    public static String addToDo(String str) throws DukeException {
        int lengthOfWordTodo = 5;
        if (str.length() > lengthOfWordTodo) {
            String finalStr = "";
            ToDos newToDo = new ToDos(str.substring(lengthOfWordTodo));
            tasklist.increaseTodoCount();
            int size = tasklist.getSize();
            assert size > 0 : "size should be more than 0";
            finalStr += ui.printTodo(tasklist.addTask(newToDo)) + "\n" + ui.printTasksLeft(tasklist.getSize());
            storage.save(tasklist);
            return finalStr;
        } else {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
        }
    }

    /**
     * Adds Deadline tasks.
     *
     * @param str The string containing task to be added.
     * @return String of deadline that is added to list.
     */
    public static String addDeadline(String str) throws DukeException {
        if (str.length() > 10) {
            String desc = "";

            int k = 9;
            while (str.charAt(k) != '/') {
                desc += str.charAt(k);
                k++;
            }

            String date = str.substring(k + 4);
            Deadlines newDeadline = new Deadlines(desc, LocalDateTime.parse(date));
            tasklist.increaseDeadlineCount();
            String finalStr = "";
            int size = tasklist.getSize();
            assert size > 0 : "size should be more than 0";
            finalStr += ui.printTodo(tasklist.addTask(newDeadline)) + "\n" + ui.printTasksLeft(tasklist.getSize());
            storage.save(tasklist);
            return finalStr;
        } else {
            throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
        }
    }

    /**
     * Adds Event tasks.
     *
     * @param str The string containing task to be added.
     * @return String of event that is added to the list.
     */
    public static String addEvent(String str) throws DukeException {
        if (str.length() > 6) {
            String desc = "";

            int k = 6;
            while (str.charAt(k) != '/') {
                desc += str.charAt(k);
                k++;
            }

            String eventTime = str.substring(k + 4);
            Events newEvent = new Events(desc, LocalDateTime.parse(eventTime));
            tasklist.increaseEventCount();
            String finalStr = "";
            int size = tasklist.getSize();
            assert size > 0 : "size should be more than 0";
            finalStr += ui.printTodo(tasklist.addTask(newEvent)) + "\n" + ui.printTasksLeft(tasklist.getSize());
            storage.save(tasklist);
            return finalStr;
        } else {
            throw new DukeException("OOPS!!! The description of an event cannot be empty.");
        }
    }

    /**
     * Deletes task.
     *
     * @param str The string specifying which task to be deleted.
     * @return String of task to be deleted.
     */
    public static String deleteTask(String str) throws DukeException {
        int indexInStr = 7;
        int taskToDel = Integer.parseInt(String.valueOf(str.charAt(indexInStr)));

        if (taskToDel <= tasklist.getSize()) {
            String finalStr = "";
            Task taskToDelete = tasklist.getTask(taskToDel - 1);
            finalStr += ui.printDelete(taskToDelete);
            reduceTaskCount(taskToDelete);
            tasklist.deleteTask(taskToDel - 1);
            finalStr += ui.printTasksLeft(tasklist.getSize());
            storage.save(tasklist);
            return finalStr;
        } else {
            throw new DukeException("No such task exist. Try again!");
        }
    }

    /**
     * Reduces task count when task is deleted
     *
     * @param task task that is deleted
     */
    public static void reduceTaskCount(Task task) {
        if (task instanceof ToDos) {
            tasklist.decreaseTodoCount();
        } else if (task instanceof Deadlines) {
            tasklist.decreaseDeadlineCount();
        } else {
            tasklist.decreaseEventCount();
        }
    }

    /**
     * Finds task with matching keyword.
     *
     * @param str User input.
     * @return String of list of tasks that matches keyword.
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

    /**
     * Counts the number of todos.
     *
     * @param str User input.
     * @return String of number of specified type of tasks in list.
     */
    public static String countTasks(String str) {
        if (str.equals("todo")) {
            int todoNum = tasklist.getTodoCount();
            return ui.printTaskCount("todo", todoNum);
        } else if (str.equals("deadline")) {
            int deadlineNum = tasklist.getDeadlineCount();
            return ui.printTaskCount("deadline", deadlineNum);
        } else {
            int eventNum = tasklist.getEventCount();
            return ui.printTaskCount("event", eventNum);
        }
    }

}