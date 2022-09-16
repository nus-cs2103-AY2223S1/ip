package duke.tasklist;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import duke.exceptions.DukeException;
import duke.storage.Storage;

/**
 * Task list that handles changes to the list of tasks.
 *
 */
public class TaskList {
    private static TaskList tasklist;
    private final ArrayList<Task> storage;
    private final DateTimeFormatter formatterForInput = DateTimeFormatter
            .ofPattern("yyyy-MM-dd HHmm");
    private final DateTimeFormatter formatterForSavedFile = DateTimeFormatter
            .ofPattern("yyyy-MM-dd HH:mm");
    private Storage outputFile = Storage.getInstance();

    /**
     * Constructor for the TaskList object.
     *
     * Initializes the ArrayList for storing task objects.
     */
    public TaskList() {
        this.storage = new ArrayList<>();
    }

    /**
     * Lists out all the tasks currently in the list
     */
    public void list() {
        if (this.storage.size() == 0) {
            System.out.println("List is currently empty");
        }
        System.out.println("Here are your tasks:");
        for (int i = 0; i < this.storage.size(); i++) {
            System.out.printf("%d.%s\n", i + 1,
                    this.storage.get(i).toString());
        }
    }

    /**
     * Handles tasks to be added from saved file.
     *
     * @param input String containing input from saved file.
     */
    public void addTasksFromSave(String input) {
        String[] curr = input.split("\\s*\\|\\s*");
        taskIdentifierForSavedFile(curr);
    }

    /**
     * Identifies tasks from saved file and creates accordingly.
     *
     * @param input Input from saved file.
     */
    public void taskIdentifierForSavedFile(String[] input) {
        String command = input[0];

        switch (command) {
        case "T": {
            addTodoFromSave(input);
            break;
        }
        case "D": {
            addDeadlineFromSave(input);
            break;
        }
        case "E": {
            addEventFromSave(input);
            break;
        }
        default:
        }
    }

    /**
     * Creates a todo task and adds it to storage, and marks as done if appropriate.
     * This is used for input from the saved file.
     *
     * @param input String array containing task description.
     */
    public void addTodoFromSave(String[] input) {
        Task currTask = new Todo(input[2]);
        savedInputTaskCompletionChecker(currTask, input[1]);
        this.storage.add(currTask);
    }

    /**
     * Creates a deadline task and adds it to storage, and marks as done if appropriate.
     * This is used for input from the saved file.
     *
     * @param input String array containing task description and due date.
     */
    public void addDeadlineFromSave(String[] input) {
        Task currTask = new Deadline(input[2],
                LocalDateTime.parse(input[3].trim(), formatterForSavedFile));
        savedInputTaskCompletionChecker(currTask, input[1]);
        this.storage.add(currTask);
    }

    /**
     * Creates a event task and adds it to storage, and marks as done if appropriate.
     * This is used for input from the saved file.
     *
     * @param input String array containing task description and event date.
     */
    public void addEventFromSave(String[] input) {
        Task currTask = new Event(input[2],
                LocalDateTime.parse(input[3].trim(), formatterForSavedFile));
        savedInputTaskCompletionChecker(currTask, input[1]);
        this.storage.add(currTask);
    }

    /**
     * Marks tasks as done according to their value in the saved file.
     *
     * @param t Task to be marked.
     * @param flag String that indicates whether task was marked as done in file.
     */
    public void savedInputTaskCompletionChecker(Task t, String flag) {
        if (flag.equals("1")) {
            t.markSavedTaskAsDone();
        }
    }

    /**
     * Handles tasks to be added from user input.
     *
     * @param input String array containing user input.
     * @throws DukeException If input provided is not valid.
     */
    public void addTask(String[] input) throws DukeException {
        assert input.length == 2;
        if (input[1] == null) {
            throw new DukeException("Description of a " + input[0]
                    + " cannot be empty!");
        }

        try {
            taskIdentifierForUserInput(input);
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid date format!\n "
                    + "please enter date in yyyy-mm-dd hhMM format");
        }

    }

    /**
     * Identifies task according to user input and calls appropriate functions
     * to create the task.
     *
     * @param input User input from GUI.
     */
    public void taskIdentifierForUserInput(String[] input) throws DukeException {
        switch (input[0]) {
        case "todo":
            addTodo(input);
            break;
        case "deadline":
            addDeadline(input);
            break;
        case "event":
            addEvent(input);
            break;
        default:
            throw new DukeException("Invalid task");
        }
        printStorageSize();
        outputFile.writeToSavedFile();
    }

    /**
     * Creates a todo task and adds it to storage.
     *
     * @param input String array containing task description.
     */
    public void addTodo(String[] input) {
        Todo todo = new Todo(input[1].trim());
        storage.add(todo);
        System.out.printf("added %s\n", todo);
    }

    /**
     * Creates a deadline task and adds it to storage.
     *
     * @param input String array containing task description and due date.
     */
    public void addDeadline(String[] input) throws DukeException {
        if (!input[1].contains("/by")
                || input[1].indexOf("/by") == input[1].length() - 3) {
            throw new DukeException("No date inserted for deadline");
        }
        String[] deadlineInfo = input[1].split("/by", 2);
        Deadline deadline = new Deadline(deadlineInfo[0].trim(),
                LocalDateTime.parse(deadlineInfo[1].trim(),
                        formatterForInput));
        storage.add(deadline);
        System.out.printf("added %s\n", deadline);
    }

    /**
     * Creates a event task and adds it to storage.
     *
     * @param input String array containing task description and event date.
     */
    public void addEvent(String[] input) throws DukeException {
        if (!input[1].contains("/at")
                || input[1].indexOf("/at") == input[1].length() - 3) {
            throw new DukeException("No date inserted for event");
        }
        String[] eventInfo = input[1].split("/at", 2);
        Event event = new Event(eventInfo[0].trim(),
                LocalDateTime.parse(eventInfo[1].trim(),
                        formatterForInput));
        storage.add(event);
        System.out.printf("added %s\n", event);
    }


    /**
     *
     */
    public void printStorageSize() {
        System.out.printf("Now you have %d tasks in the list\n",
                storage.size());
    }

    /**
     * Deletes tasks according to user input.
     *
     * @param input String containing input for deletion.
     */
    public void delete(String input) throws DukeException {
        if (storage.size() == 0) {
            throw new DukeException("No tasks to delete!");
        }
        if (input == null) {
            throw new DukeException("No input provided for deletion");
        }
        String s = input.replaceAll("\\D+", "");
        if (!s.equals("")) {
            Task t = taskGetter(s, "deletion");
            storage.remove(t);
            System.out.println("Removed the following task:\n"
                    + t);
            outputFile.writeToSavedFile();
        } else {
            throw new DukeException("Invalid selection for deletion");
        }
    }

    /**
     * Unmarks tasks according to user input.
     *
     * @param input String containing input for unmarking.
     */
    public void unmark(String input) throws DukeException {
        if (this.storage.size() == 0) {
            throw new DukeException("No tasks to unmark!");
        }
        if (input == null) {
            throw new DukeException("No input provided for unmarking");
        }
        String s = input.replaceAll("\\D+", "");
        if (!s.equals("")) {
            Task t = taskGetter(s, "unmarking");
            t.markAsNotDone();
            outputFile.writeToSavedFile();
        } else {
            throw new DukeException("Invalid selection for unmarking");
        }
    }

    /**
     * Marks tasks according to user input.
     * @param input String containing input for marking.
     */
    public void mark(String input) throws DukeException {
        if (this.storage.size() == 0) {
            throw new DukeException("No tasks to mark!");
        }
        if (input == null) {
            throw new DukeException("No input provided for marking");
        }
        String s = input.replaceAll("\\D+", "");
        if (!s.equals("")) {
            Task t = taskGetter(s, "marking");
            t.markAsDone();
            outputFile.writeToSavedFile();
        } else {
            throw new DukeException("Invalid selection for marking");
        }
    }

    /**
     * Gets the task that is meant to be updated (unmarked, marked, deleted).
     * Only used for the above mentioned methods.
     *
     * @param index String containing index of item to be updated.
     * @param command String indicating command called.
     * @throws DukeException If selection is invalid.
     */
    public Task taskGetter(String index, String command) throws DukeException {
        int idx = Integer.parseInt(index) - 1;
        if (idx >= this.storage.size() || idx < 0) {
            throw new DukeException("Invalid selection for " + command);
        }
        return this.storage.get(idx);
    }

    /**
     * Returns TaskList instance if created, else create one.
     * Ensures TaskList will only ever have one created object.
     *
     * @return TaskList object.
     */
    public static TaskList getInstance() {
        if (tasklist == null) {
            tasklist = new TaskList();
        }
        return tasklist;
    }


    /**
     * Finds and prints tasks according to input keywords.
     *
     * @param filter Keywords to filter tasks, given by user.
     */
    public void findWithFilter(String filter) throws DukeException {
        if (filter == null) {
            throw new DukeException("No input provided for filter");
        }
        ArrayList<Task> filteredList = new ArrayList<>();

        for (Task x: this.storage) {
            if (x.toString().contains(filter)) {
                filteredList.add(x);
            }
        }

        if (filteredList.isEmpty()) {
            System.out.println("No matching tasks found");
            return;
        }
        System.out.println("Here are the tasks that match:");
        for (int i = 0; i < filteredList.size(); i++) {
            System.out.printf("%d.%s\n", i + 1,
                    filteredList.get(i).toString());
        }
    }

    /**
     * Getter for the list of tasks stored within the task list object.
     *
     */
    public ArrayList<Task> getTaskList() {
        ArrayList<Task> newList = new ArrayList<>();
        for (Task x : storage) {
            newList.add(x);
        }
        return newList;
    }
}
