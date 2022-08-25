package duke;

import java.util.ArrayList;
public class TaskList {
    protected ArrayList<Task> storeLists;
    protected Storage storage;

    public TaskList(Storage storage) {
        this.storage = storage;
    }
    public TaskList(ArrayList<Task> storeList, Storage storage) {
        this.storeLists = storeList;
        this.storage = storage;
    }

    public void addTask(String userInput) {
        Task t = new Task(userInput);
        storeLists.add(t);
        System.out.println("added: " + t.description);
        storage.appendToFile(t.description);
    }

    public void deleteTask(int taskIndex) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("\t" + storeLists.get(taskIndex).toString());
        storage.removeLineInText(storeLists.get(taskIndex).toString());
        storeLists.remove(taskIndex);
        System.out.println("Now you have " + storeLists.size() + " tasks in the list.");
    }

    public void list() {
        System.out.println("Here are the tasks in your list:");
        for (int i=0; i < storeLists.size(); i++) {
//            System.out.printf("%i. [%c] %s", i + 1, storeList.get(i).getStatusIcon(), storeList.get(i).description);
            System.out.println(i + 1 + ". " + storeLists.get(i).toString());
        }
    }

    public void mark(int taskIndex) {
        String oldText = storeLists.get(taskIndex).toString();
        storeLists.get(taskIndex).markAsDone();
        String newText = storeLists.get(taskIndex).toString();
        storage.editTextInFile(newText, oldText);
    }

    public void unmark(int taskIndex) {
        String oldText = storeLists.get(taskIndex).toString();
        storeLists.get(taskIndex).markAsNotDone();
        String newText = storeLists.get(taskIndex).toString();
        storage.editTextInFile(newText, oldText);
    }

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

    public void deadline(String userInput, String by) {
        Deadline deadline = new Deadline(userInput, by);
        dateProcessor(deadline);
        storeLists.add(deadline);
        System.out.println("Got it. I've added this task:");
        System.out.println("\t" + deadline.toString());
        System.out.println("Now you have " + storeLists.size() + " tasks in the list.");
        storage.appendToFile(deadline.toString());
    }

    public void event(String userInput, String duration) {
        Event event = new Event(userInput, duration);
        storeLists.add(event);
        System.out.println("Got it. I've added this task:");
        System.out.println("\t" + event.toString());
        System.out.println("Now you have " + storeLists.size() + " tasks in the list.");
        storage.appendToFile(event.toString());
    }

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

    public static void dateProcessor(Deadline d) {
        d.convertToDate();
    }

}
