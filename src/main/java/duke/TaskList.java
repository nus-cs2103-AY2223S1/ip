package duke;

import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 * The Tasklist class that stores the arraylist.
 */
public class TaskList {

    private static final Pattern CHECKSTRING = Pattern.compile("-?\\d+");

    private final ArrayList<Task> list;

    /**
     * Constructor of a TaskList.
     */
    public TaskList() {
        this.list = new ArrayList<>();
    }

    /**
     * To check if an integer is given after user input "mark", "unmark" or "delete".
     *
     * @param strNum The String after user input "mark", "unmark" or "delete".
     * @return True if it is an integer, false otherwise.
     */
    private static boolean isInteger(String strNum) {
        if (strNum == null) {
            return false;
        }
        return CHECKSTRING.matcher(strNum).matches();
    }

    /**
     * Adds task from the text document to the arraylist.
     *
     * @param task The task from the text document.
     */
    public void addStorageToList(Task task) {
        this.list.add(task);
    }

    /**
     * gets index from the arraylist.
     *
     * @param number The index
     * @return The task in that index.
     */
    public Task getTask(int number) {
        return this.list.get(number);
    }

    /**
     * Gets the length of the arraylist.
     *
     * @return The length of the arraylist.
     */
    public int size() {
        return this.list.size();
    }

    /**
     * Clears the arraylist.
     */
    public void clear() {
        this.list.clear();
    }

    /**
     * Views the entire list
     *
     * @return the String of the entire list.
     * @throws DukeException If the list is empty.
     */
    public String viewList() throws DukeException {
        if (this.list.size() == 0) {
            throw new DukeException("there's nothing!");
        } else {
            String listString = "";
            for (int i = 0; i < this.list.size(); i++) {
                listString += (i + 1) + "." + this.list.get(i).toString() + "\n";
            }
            return listString;
        }
    }

    /**
     * Marks or unmarks the task.
     *
     * @param updater The action to be done.
     * @param index The string response broken down into its keywords.
     * @throws DukeException If the keywords are missing or invalid.
     */
    public String updateTask(Parser.TaskUpdater updater, String index) throws DukeException {
        int number = stringToInteger(index);
        if (this.list.size() < number || number <= 0) {
            throw new DukeException("There's no such task to " + updater.toString().toLowerCase() + "!");
        } else {
            switch (updater) {
            case MARK:
                return markString(number);
            case UNMARK:
                return unMarkString(number);
            case DELETE:
                return deleteTask(number);
            default:
                assert false : "There should not be any other TaskUpdater.";
                return "this should never happen in updateTask."; // Placeholder return statement.
            }
        }
    }

    /**
     * Deletes task from the list.
     *
     * @param number The index to be removed.
     */
    public String deleteTask(int number) {
        Task task = this.list.get(number - 1);
        this.list.remove(number - 1);
        return "Noted. I've removed this task:\n" + task + "\n"
                + "Now you have " + this.list.size() + " tasks in the list.";
    }

    /**
     * Finds all the task that fits the keyword.
     *
     * @param keyword The word used for searching.
     * @return all tasks that have keyword in their description.
     */
    public String findTask(String keyword) {
        int count = 1;
        String findListString = "Here are the matching tasks in your list:\n";
        Pattern findExpression = Pattern.compile(".*\\b" + keyword + "\\b.*");
        for (int i = 0; i < this.list.size(); i++) {
            if (this.list.get(i).getDescription().matches(String.valueOf(findExpression))) {
                findListString += (count) + "." + this.list.get(i).toString() + "\n";
                count++;
            }
        }
        return findListString;
    }

    /**
     * Converts String into integer.
     *
     * @param word The string to be converted.
     * @return The integer converted.
     * @throws DukeException If string cannot be converted into integer.
     */
    public int stringToInteger(String word) throws DukeException {
        if (!isInteger(word)) {
            throw new DukeException("I don't know which to delete!");
        } else {
            return Integer.parseInt(word);
        }
    }
    /**
     * Marks task with an X.
     *
     * @param number Index in the list for marking.
     */
    public String markString(int number) {
        Task currentTask = this.list.get(number - 1);
        currentTask.mark();
        return "Nice! I've marked this task as done:\n" + currentTask;
    }

    /**
     * Unmarks task by removing the X.
     *
     * @param number Index in the list for unmarking.
     */
    public String unMarkString(int number) {
        Task currentTask = this.list.get(number - 1);
        currentTask.unMark();
        return "OK, I've marked this task as not done yet:\n" + currentTask;
    }

    /**
     * Adds the right type of task to the list.
     *
     * @param type     Type of task.
     * @param stringComponent    The string response broken down into its keywords.
     * @throws DukeException If the keywords are missing or invalid.
     */
    public String addTaskType(Parser.Type type, String stringComponent) throws DukeException {
        switch (type) {
        case DEADLINE:
            String[] deadlineComponents = stringComponent.split(" /by ", 2);
            assert verifyTask(deadlineComponents, " /by ", Parser.Type.DEADLINE)
                    : "It should never be false.";
            DeadlineTask deadline = new DeadlineTask(deadlineComponents[0], deadlineComponents[1]);
            this.list.add(deadline);
            return addTask(deadline);
        case TODO:
            TodoTask todo = new TodoTask(stringComponent);
            this.list.add(todo);
            return addTask(todo);
        case EVENT:
            String[] eventComponents = stringComponent.split(" /at ", 2);
            assert verifyTask(eventComponents, " /at ", Parser.Type.EVENT)
                    : "It should never be false.";
            EventTask event = new EventTask(eventComponents[0], eventComponents[1]);
            this.list.add(event);
            return addTask(event);
        default:
            assert false : "There should not be any other type.";
            return "this should never happen in addTaskType."; // Placeholder return statement.
        }
    }

    /**
     * Adds task into the arraylist.
     *
     * @param task The task added into the arraylist.
     */
    public String addTask(Task task) {
        return "Got it. I've added this task:\n" + task + "\n"
                + "Now you have " + this.list.size() + " tasks in the list.";
    }

    /**
     * Verifies if the task input is valid.
     *
     * @param components Details of the task.
     * @param regex The keyword that separates task description and task date and time.
     * @return True if task input in valid. False, if otherwise.
     * @throws DukeException If the task input in invalid.
     */
    public boolean verifyTask(String[] components, String regex, Parser.Type type) throws DukeException {
        if (components[0].trim().equals("")) {
            throw new DukeException("There's no " + type.toString().toLowerCase() + " task!");
        } else if (components.length == 1) {
            throw new DukeException("You didn't specify the deadline! Please use " + regex);
        } else {
            return true;
        }
    }

    /**
     * Add tags into the task
     *
     * @param string The tag instructions.
     * @return String of tags added.
     * @throws DukeException if no task index was indicated.
     */
    public String tagTask(String string) throws DukeException {
        String[] stringComponents = string.split(" ");
        int taskIndex = stringToInteger(stringComponents[0]) - 1;
        Task taskChosen = this.list.get(taskIndex);
        String printTags = "Tags added: ";
        for (int i = 1; i < stringComponents.length; i++) {
            taskChosen.addTag(stringComponents[i]);
            printTags += stringComponents[i] + " ";
        }
        return printTags + "\n" + taskChosen;
    }
}
