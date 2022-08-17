import java.util.Scanner;
import java.util.ArrayList;
/**
 * The Kirby class implements the main method of the chatbot
 * with all the relevant commands.
 * @author Sheryl-Lynn Tan (G11)
 */
public class Kirby {
    /**
     * Static variable to store the number of tasks in the bag.
     */
    public static int taskCount = 0;

    /**
     * This method is to increment the static variable taskCount.
     */
    public static void addTaskCount() {
        taskCount += 1;
    }

    /**
     * This method is to decrement the static variable taskCount.
     */
    public static void subtractTaskCount() {
        taskCount -= 1;
    }

    /**
     * This method is to print the static variable taskCount.
     */
    public static void printTaskCount() {
        if (taskCount > 1) {
            System.out.println("Now you have " + taskCount + " tasks in the bag!");
        } else {
            System.out.println("Now you have " + taskCount + " task in the bag!");
        }
    }

    /**
     * This method is to print the welcome message.
     */
    public static void welcome() {
        System.out.println("Hai I'm Kirby (੭｡╹▿╹｡)੭ your friendly chat assistant!! \n" + "What amazing plans do you have today?");
    }

    /**
     * This method is to print the goodbye message.
     */
    public static void goodbye() {
        System.out.println("I loved talking to you ･ω･\n" +
                "Hope to see you again!");
    }

    /**
     * This method is to print all the tasks in the list Tasks.
     * @param Tasks this is the arraylist storing all the tasks in the bag.
     */
    public static void showList(ArrayList<Task> Tasks) {
        System.out.println("Here is your bag of fabulous tasks:");
        for (int i = 0; i < Tasks.size(); i++) {
            Task currTask = Tasks.get(i);
            System.out.println(i + 1 + ": " + currTask.toString());
        }
    }

    /**
     * This method is to mark the task selected.
     * @param inputString the command entered by the user specifying 'mark' and the task number.
     * @param Tasks this is the arraylist storing all the tasks in the bag.
     * @exception KirbyMissingArgumentException if there is an invalid task number.
     */
    public static void mark(String inputString, ArrayList<Task> Tasks) throws KirbyMissingArgumentException {
        if (inputString.split(" ").length != 2) {
            throw new KirbyMissingArgumentException("mark");
        }
        int taskIndex = Integer.parseInt(inputString.split(" ")[1]);
        if (Tasks.size() == 0 || taskIndex < 1 || taskIndex > Tasks.size()) {
            throw new KirbyMissingArgumentException("mark");
        }
            Task currTask = Tasks.get(taskIndex - 1);
            currTask.setCompleted();
            System.out.println("Awesome :D I've marked " + currTask.toString() + " completed!");
    }

    /**
     * This method is to unmark the task selected.
     * @param inputString the command entered by the user specifying 'unmark' and the task number.
     * @param Tasks this is the arraylist storing all the tasks in the bag.
     * @exception KirbyMissingArgumentException if there is an invalid task number.
     */
    public static void unmark(String inputString, ArrayList<Task> Tasks) throws KirbyMissingArgumentException {
        if (inputString.split(" ").length != 2) {
            throw new KirbyMissingArgumentException("unmark");
        }
        int taskIndex = Integer.parseInt(inputString.split(" ")[1]);
        if (Tasks.size() == 0 || taskIndex < 1 || taskIndex > Tasks.size()) {
            throw new KirbyMissingArgumentException("unmark");
        }
        Task currTask = Tasks.get(taskIndex - 1);
        currTask.setIncomplete();
        System.out.println("Okay, I've marked " + currTask.toString() + " pending!");
    }

    /**
     * This method is to add a Todo task.
     * @param inputString the command entered by the user specifying 'mark' and the task number.
     * @param Tasks this is the arraylist storing all the tasks in the bag.
     * @exception KirbyMissingArgumentException if there is an invalid task number.
     */
    public static void toDo(String inputString, ArrayList<Task> Tasks) throws KirbyMissingArgumentException {
        if (inputString.length() <= 5) {
            throw new KirbyMissingArgumentException("todo");
        }
        String taskName = inputString.substring(inputString.indexOf(' ') + 1);
        Todo todo = new Todo(taskName);
        Tasks.add(todo);
        System.out.println("Added into your bag of fabulous tasks: " + todo.toString());
        addTaskCount();
        printTaskCount();
    }

    /**
     * This method is to add a Deadline task.
     * @param inputString the command entered by the user specifying 'mark' and the task number.
     * @param Tasks this is the arraylist storing all the tasks in the bag.
     * @exception KirbyMissingArgumentException if there is an invalid task number.
     */
    public static void deadline(String inputString, ArrayList<Task> Tasks) throws KirbyMissingArgumentException {
        if (!inputString.contains("/by") || inputString.length() - 1 < inputString.indexOf("/by") + 4) {
            throw new KirbyMissingArgumentException("deadline");
        }
        String taskName = inputString.substring(inputString.indexOf("deadline") + 9, inputString.indexOf("/by"));
        String by = inputString.substring(inputString.indexOf("/by") + 4);
        Deadline deadline = new Deadline(taskName, by);
        Tasks.add(deadline);
        System.out.println("Added into your bag of fabulous tasks: " + deadline.toString());
        addTaskCount();
        printTaskCount();
    }

    /**
     * This method is to add an Event task.
     * @param inputString the command entered by the user specifying 'mark' and the task number.
     * @param Tasks this is the arraylist storing all the tasks in the bag.
     * @exception KirbyMissingArgumentException if there is an invalid task number.
     */
    public static void event(String inputString, ArrayList<Task> Tasks) throws KirbyMissingArgumentException {
        if (!inputString.contains("/at") || inputString.length() - 1 < inputString.indexOf("/at") + 4) {
            throw new KirbyMissingArgumentException("event");
        }
        String taskName = inputString.substring(inputString.indexOf("event") + 6, inputString.indexOf("/at"));
        String at = inputString.substring(inputString.indexOf("/at") + 4);
        Event event = new Event(taskName, at);
        Tasks.add(event);
        System.out.println("Added into your bag of fabulous tasks: " + event.toString());
        addTaskCount();
        printTaskCount();
    }

    /**
     * This method is to handle commands that are not defined.
     * @exception KirbyInvalidCommandException if there is an invalid command.
     */
    public static void notDefinedCommand() throws KirbyInvalidCommandException {
        throw new KirbyInvalidCommandException();
    }

    /**
     * This method is to delete a task.
     * @param inputString the command entered by the user specifying 'mark' and the task number.
     * @param Tasks this is the arraylist storing all the tasks in the bag.
     * @exception KirbyMissingArgumentException if there is an invalid task number.
     */
    public static void delete(String inputString, ArrayList<Task> Tasks) throws KirbyMissingArgumentException {
        if (inputString.split(" ").length != 2) {
            throw new KirbyMissingArgumentException("delete");
        }
        int taskIndex = Integer.parseInt(inputString.split(" ")[1]);
        if (Tasks.size() == 0 || taskIndex < 1 || taskIndex > Tasks.size()) {
            throw new KirbyMissingArgumentException("delete");
        }
        Task currTask = Tasks.get(taskIndex - 1);
        Tasks.remove(taskIndex - 1);
        subtractTaskCount();
        System.out.println("Removed from your bag of fabulous tasks: " + currTask.toString());
        printTaskCount();
    }

    /**
     * This is the main method to initialise the bot.
     */
    public static void main(String[] args) {
        // Arraylist of Task to store all the tasks added.
        ArrayList<Task> Tasks = new ArrayList<>();
        welcome();
        Scanner scanner = new Scanner(System.in);
        // Keep taking in input
        while (scanner.hasNextLine()) {
                String inputString = scanner.nextLine();
                try {
                    // Bye
                    if (inputString.equals("bye")) {
                        goodbye();
                        break;
                    }

                    // List
                    else if (inputString.equals("list")) {
                        showList(Tasks);
                    }

                    //Mark
                    else if (inputString.split(" ")[0].equals("mark")) {
                        try {
                            mark(inputString, Tasks);
                        } catch (KirbyMissingArgumentException e) {
                            System.out.println(e.getMessage());
                        }
                    }

                    // Unmark
                    else if (inputString.split(" ")[0].equals("unmark")) {
                        try {
                            unmark(inputString, Tasks);
                        } catch (KirbyMissingArgumentException e) {
                            System.out.println(e.getMessage());
                        }
                    }

                    // Add task
                    else if (inputString.split(" ")[0].equals("todo")) {
                        try {
                            toDo(inputString, Tasks);
                        } catch (KirbyMissingArgumentException e) {
                            System.out.println(e.getMessage());
                        }
                    }

                    // Deadline
                    else if (inputString.split(" ")[0].equals("deadline")) {
                        try {
                            deadline(inputString, Tasks);
                        } catch (KirbyMissingArgumentException e) {
                            System.out.println(e.getMessage());
                        }
                    }

                    // Event
                    else if (inputString.split(" ")[0].equals("event")) {
                        try {
                            event(inputString, Tasks);
                        } catch (KirbyMissingArgumentException e) {
                            System.out.println(e.getMessage());
                        }
                    }

                    // Delete
                    else if (inputString.split(" ")[0].equals("delete")) {
                        try {
                            delete(inputString, Tasks);
                        } catch (KirbyMissingArgumentException e) {
                            System.out.println(e.getMessage());
                        }
                    }

                    // Undefined command
                    else {
                        notDefinedCommand();
                    }
                } catch (KirbyInvalidCommandException e) {
                    System.out.println(e.getMessage());
                }
        }
    }
}