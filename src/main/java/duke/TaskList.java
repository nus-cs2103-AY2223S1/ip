package duke;

import java.util.ArrayList;

/**
 * TaskList Class to represent a class that will hold all the Task methods
 * @author amresh A0235398R
 */
public class TaskList {
    protected ArrayList<Task> storeLists;
    protected Storage storage;

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
     * @param storeList ArrayList<Task> passed into constructor
     * @param storage Storage object passed into constructor
     */
    public TaskList(ArrayList<Task> storeList, Storage storage) {
        this.storeLists = storeList;
        this.storage = storage;
    }

    /**
     * Method to add task to list
     * @param userInput User's Input that will be added to storeList
     */
    public void addTask(String userInput) {
        Task t = new Task(userInput);
        storeLists.add(t);
        System.out.println("added: " + t.description);
        storage.appendToFile(t.description);
    }

    /**
     * Method to remove task from list
     * @param taskIndex Index of task that is required to be removed
     */
    public void deleteTask(int taskIndex) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("\t" + storeLists.get(taskIndex).toString());
        storage.removeLineInText(storeLists.get(taskIndex).toString());
        storeLists.remove(taskIndex);
        System.out.println("Now you have " + storeLists.size() + " tasks in the list.");
    }

    /**
     * Method to list all the tasks in storeList
     */
    public void list() {
        System.out.println("Here are the tasks in your list:");
        for (int i=0; i < storeLists.size(); i++) {
//            System.out.printf("%i. [%c] %s", i + 1, storeList.get(i).getStatusIcon(), storeList.get(i).description);
            System.out.println(i + 1 + ". " + storeLists.get(i).toString());
        }
    }

    /**
     * To mark tasks as marked
     * @param search String that needs to be searched
     */
    public void find(String search) {
        int count = 0;
        for(int i = 0; i < storeLists.size(); i++) {
            if(storeLists.get(i).description.contains(search)) {
                count++;
                if(count == 1) {
                    System.out.println("Here are the matching tasks in your list:");
                }
                System.out.println(i + 1 + ". " + storeLists.get(i).toString());
            }
        }
        if(count == 0) {
            System.out.println("No tasks match the search term");
        }
    }

    public void mark(int taskIndex) {
        String oldText = storeLists.get(taskIndex).toString();
        storeLists.get(taskIndex).markAsDone();
        String newText = storeLists.get(taskIndex).toString();
        storage.editTextInFile(newText, oldText);
    }

    /**
     * To unmark tasks
     * @param taskIndex Index of tasks to be changed to unmark
     */
    public void unmark(int taskIndex) {
        String oldText = storeLists.get(taskIndex).toString();
        storeLists.get(taskIndex).markAsNotDone();
        String newText = storeLists.get(taskIndex).toString();
        storage.editTextInFile(newText, oldText);
    }

    /**
     * To allow user to create a Todo task
     * @param userInput Input given by user
     * @throws DukeException Throws a Exception specific to the program
     */
    public void todo(String userInput) throws DukeException {
        Todo todo = new Todo(userInput);
        if (userInput.isEmpty()) {
            throw new DukeException("\t☹ OOPS!!! The description of a todo cannot be empty.");
        }
        storeLists.add(todo);
        System.out.println("Got it. I've added this task:");
        System.out.println("\t" + todo.toString());
        System.out.println("Now you have " + storeLists.size() + " tasks in the list.");
        storage.appendToFile(todo.toString());

    }

    /**
     * To allow user to create a Deadline Task
     * @param userInput Input given by user
     * @param by Due date for the deadline
     */
    public void deadline(String userInput, String by) {
        Deadline deadline = new Deadline(userInput, by);
        dateProcessor(deadline);
        storeLists.add(deadline);
        System.out.println("Got it. I've added this task:");
        System.out.println("\t" + deadline.toString());
        System.out.println("Now you have " + storeLists.size() + " tasks in the list.");
        storage.appendToFile(deadline.toString());
    }

    /**
     * To allow user to create an Event task
     * @param userInput Input given by user
     * @param duration Duration given to the user to complete within
     */
    public void event(String userInput, String duration) {
        Event event = new Event(userInput, duration);
        storeLists.add(event);
        System.out.println("Got it. I've added this task:");
        System.out.println("\t" + event.toString());
        System.out.println("Now you have " + storeLists.size() + " tasks in the list.");
        storage.appendToFile(event.toString());
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

    /**
     * Method that activates convertToDate method
     * @param d Deadline object passed in as parameter
     */
    public static void dateProcessor(Deadline d) {
        d.convertToDate();
    }

}
