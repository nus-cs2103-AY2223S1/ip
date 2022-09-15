package duke;

import java.util.ArrayList;

/**
 * TaskList Class to represent a class that will hold all the Task methods
 * @author amresh A0235398R
 */
public class TaskList {
    protected ArrayList<Task> storeTasks;
    protected Storage storage;
    protected String printStatement;

    /**
     * Overload constructor to initialize TaskList Object
     *
     * @param storage Storage object passed into constructor
     */
    public TaskList(Storage storage) {
        this.storage = storage;
    }

    /**
     * Overload constructor to initialize TaskList Object
     *
     * @param storeTasks ArrayList<Task> passed into constructor
     * @param storage Storage object passed into constructor
     */
    public TaskList(ArrayList<Task> storeTasks, Storage storage) {
        assert storeTasks != null;
        this.storeTasks = storeTasks;
        this.storage = storage;
    }

    /**
     * Method to add task to list
     * @param userInput User's Input that will be added to storeList
     */
    public String addTask(String userInput) {
        Task t = new Task(userInput);
        storeTasks.add(t);
//        storage.appendToFile(t.description);
        storage.writeToFile(storeTasks);
        return "added: " + t.description;
    }

    /**
     * Method to remove task from list
     * @param taskIndex Index of task that is required to be removed
     */
    public String deleteTask(int taskIndex) {
        String printStatement = "";
        printStatement += "Noted. I've removed this task: \n";
        printStatement += "\t" + storeTasks.get(taskIndex).toString() + "\n";
//        storage.removeLineInText(storeTasks.get(taskIndex).toString());
        storeTasks.remove(taskIndex);
        storage.writeToFile(storeTasks);
        printStatement += "Now you have " + storeTasks.size() + " tasks in the list.";
        return printStatement;
    }

    /**
     * Method to list all the tasks in storeList
     * @return Print Statement String
     */
    public String list() {
        printStatement = "Here are the tasks in your list:\n";
        for (int i = 0; i < storeTasks.size(); i++) {
            printStatement += i + 1 + ". " + storeTasks.get(i).toString() + "\n";
        }
        return printStatement;
    }

    /**
     * To find tasks that match Search string
     * @param search String that needs to be searched
     * @return String containing searchResults formatted for printing
     */
    public String find(String search) {
        int count = 0;
        for(int i = 0; i < storeTasks.size(); i++) {
            if(storeTasks.get(i).description.contains(search)) {
                count++;
                if(count == 1) {
                    printStatement = "Here are the matching tasks in your list:\n";
                }
                printStatement += i + 1 + ". " + storeTasks.get(i).toString() + "\n";
            }
        }
        if(count == 0) {
            printStatement = "No tasks match the search term";
        }
        return printStatement;
    }
    /**
     * To mark tasks
     * @param taskIndex Index of tasks to be changed to unmark
     * @return PrintStatement String
     */
    public String mark(int taskIndex) {
        String oldText = storeTasks.get(taskIndex).toString();
        printStatement = storeTasks.get(taskIndex).markAsDone();
        String newText = storeTasks.get(taskIndex).toString();
//        storage.editTextInFile(newText, oldText);
        storage.writeToFile(storeTasks);
        return printStatement;
    }

    /**
     * To unmark tasks
     * @param taskIndex Index of tasks to be changed to unmark
     * @return PrintStatement String
     */
    public String unmark(int taskIndex) {
        String oldText = storeTasks.get(taskIndex).toString();
        printStatement = storeTasks.get(taskIndex).markAsNotDone();
        String newText = storeTasks.get(taskIndex).toString();
//        storage.editTextInFile(newText, oldText);
        storage.writeToFile(storeTasks);
        return printStatement;
    }

    /**
     * To allow user to create a Todo task
     * @param userInput Input given by user
     * @return printStatement
     * @throws DukeException Throws an Exception specific to the program
     */
    public String todo(String userInput) throws DukeException {
        Todo todo = new Todo(userInput);
        if (userInput.isEmpty()) {
            throw new DukeException("\t☹ OOPS!!! The description of a todo cannot be empty.");
        }
        if(duplicateCheck(todo)) {
            return "Duplicate item entered";
        }
        storeTasks.add(todo);
        printStatement = "Got it. I've added this task:\n";
        printStatement += "\t" + todo.toString() + "\n";
        printStatement += "Now you have " + storeTasks.size() + " tasks in the list.";
//        storage.appendToFile(todo.toString());
        storage.writeToFile(storeTasks);
        return printStatement;

    }

    /**
     * To allow user to create a Deadline Task
     * @param userInput Input given by user
     * @param by Due date for the deadline
     * @return printStatement
     */
    public String deadline(String userInput, String by) {
        Deadline deadline = new Deadline(userInput, by);
        dateProcessor(deadline);
        if(duplicateCheck(deadline)) {
            return "Duplicate item entered";
        }
        storeTasks.add(deadline);
        printStatement = "Got it. I've added this task:\n";
        printStatement += "\t" + deadline.toString() + "\n";
        printStatement += "Now you have " + storeTasks.size() + " tasks in the list.";
//        storage.appendToFile(deadline.toString());
        storage.writeToFile(storeTasks);
        return printStatement;
    }

    /**
     * To allow user to create an Event task
     * @param userInput Input given by user
     * @param duration Duration given to the user to complete within
     * @return printStatement
     */
    public String event(String userInput, String duration) {
        Event event = new Event(userInput, duration);
        if(duplicateCheck(event)) {
            return "Duplicate item entered";
        }
        storeTasks.add(event);
        printStatement = "Got it. I've added this task:\n";
        printStatement += "\t" + event.toString() + "\n";
        printStatement += "Now you have " + storeTasks.size() + " tasks in the list.";
//        storage.appendToFile(event.toString());
        storage.writeToFile(storeTasks);
        return printStatement;
    }

    /**
     * Method to check for errors for the input that is given
     * @param userInput Input given by the user
     * @throws DukeException Error thrown that is specific to Duke
     */
    public void userInputCheck(String userInput) throws DukeException {
        String firstCommand = userInput.split(" ", 2)[0];
        if (!firstCommand.equals("todo") && !firstCommand.equals("deadline") && !firstCommand.equals("event")) {
            throw new DukeException("\t ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        if (userInput.split(" ", 2).length == 1) {
            throw new DukeException("\t ☹ OOPS!!! The description of a " + firstCommand + " cannot be empty.");
        }
        if((firstCommand.equals("deadline") || firstCommand.equals("event")) && userInput.split("/", 2).length == 1) {
            throw new DukeException("\t ☹ OOPS!!! The time due or needed cannot be empty.");
        }
    }

    public boolean duplicateCheck(Task task) {
        System.out.println("check");
        for(int i = 0; i < storeTasks.size(); i++) {
            if(storeTasks.get(i).equals(task)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Method that activates convertToDate method
     * @param d Deadline object passed in as parameter
     */
    public static void dateProcessor(Deadline d) {
        d.convertToDate();
    }

}
