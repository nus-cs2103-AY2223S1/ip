import java.util.ArrayList;

public class TaskList {
    protected ArrayList<Task> storeList;
    protected Storage storage;

    public TaskList(Storage storage) {
        this.storage = storage;
    }
    public TaskList(ArrayList<Task> storeList, Storage storage) {
        this.storeList = storeList;
        this.storage = storage;
    }

    public void addTask(String userInput) {
        Task t = new Task(userInput);
        storeList.add(t);
        System.out.println("added: " + t.description);
        storage.appendToFile(t.description);
    }

    public void deleteTask(int taskIndex) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("\t" + storeList.get(taskIndex).toString());
        storage.removeLineInText(storeList.get(taskIndex).toString());
        storeList.remove(taskIndex);
        System.out.println("Now you have " + storeList.size() + " tasks in the list.");
    }

    public void list() {
        System.out.println("Here are the tasks in your list:");
        for(int i=0; i < storeList.size(); i++){
//            System.out.printf("%i. [%c] %s", i + 1, storeList.get(i).getStatusIcon(), storeList.get(i).description);
            System.out.println(i + 1 + ". " + storeList.get(i).toString());
        }
    }

    public void mark(int taskIndex) {
        String oldText = storeList.get(taskIndex).toString();
        storeList.get(taskIndex).markAsDone();
        String newText = storeList.get(taskIndex).toString();
        storage.editTextInFile(newText, oldText);
    }

    public void unmark(int taskIndex) {
        String oldText = storeList.get(taskIndex).toString();
        storeList.get(taskIndex).markAsNotDone();
        String newText = storeList.get(taskIndex).toString();
        storage.editTextInFile(newText, oldText);
    }

    public void todo(String userInput) throws DukeException {
        Todo t = new Todo(userInput);
        if (userInput.isEmpty()) {
            throw new DukeException("\t☹ OOPS!!! The description of a todo cannot be empty.");
        }
        storeList.add(t);
        System.out.println("Got it. I've added this task:");
        System.out.println("\t" + t.toString());
        System.out.println("Now you have " + storeList.size() + " tasks in the list.");
        storage.appendToFile(t.toString());

    }

    public void deadline(String userInput, String by) {
        Deadline d = new Deadline(userInput, by);
        dateProcessor(d);
        storeList.add(d);
        System.out.println("Got it. I've added this task:");
        System.out.println("\t" + d.toString());
        System.out.println("Now you have " + storeList.size() + " tasks in the list.");
        storage.appendToFile(d.toString());
    }

    public void event(String userInput, String duration) {
        Event e = new Event(userInput, duration);
        storeList.add(e);
        System.out.println("Got it. I've added this task:");
        System.out.println("\t" + e.toString());
        System.out.println("Now you have " + storeList.size() + " tasks in the list.");
        storage.appendToFile(e.toString());
    }

    public void userInputCheck(String userInput) throws DukeException {
        String firstCommand = userInput.split(" ", 2)[0];
        if(!firstCommand.equals("todo") && !firstCommand.equals("deadline") && !firstCommand.equals("event")) {
            throw new DukeException("\t ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        if(userInput.split(" ", 2).length == 1) {
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
