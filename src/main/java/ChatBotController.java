import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class is a logic controller for a chatbot
 */
public class ChatBotController {

    private final Scanner sc = new Scanner(System.in);
    private final String initialGreeting = "Hello, I'm Logits. What can I do for you?";
    private final String goodbyeGreeting = "Bye. Hope to see you soon!";
    private final String listString = "list";
    private final String commandList = "1. Add a ToDo\n" + " ".repeat(10) + "2. Add an Event\n" +
            " ".repeat(10) + "3. Add a Deadline\n" + " ".repeat(10) + "4. List all Tasks\n"
            + " ".repeat(10) + "5. Mark\n" + " ".repeat(10) + "6. Unmark\n" + " ".repeat(10)
            + "7. Exit";

    private final String splitLine = " ".repeat(5) + "*".repeat(50);
    private final ArrayList<Task> tasks = new ArrayList<>();
    /**
     * Check if the given string is a "bye".
     * @param s The string that requires checking
     * @return if s is any version of "bye", then true; Otherwise false.
     */
    public Boolean checkBye(String s) {
        return s.equalsIgnoreCase("bye");
    }

    /**
     * Check if the given string is a "list".
     * @param s The string that requires checking
     * @return if s is any version of "list", then true; Otherwise false.
     */
    public Boolean checkList(String s) {
        return s.equalsIgnoreCase("list");
    }

    /**
     * Check if the given input is a mark command.
     * @param s given input
     * @return if s is a mark command, then true; Otherwise false.
     */
    public Boolean checkMark(String s) {
        return s.split(" ")[0].equalsIgnoreCase("mark");
    }

    /**
     * Check if the given input is an unmark command.
     * @param s given input
     * @return if s is an unmark command, then true; Otherwise false.
     */
    public Boolean checkUnmark(String s) {
        return s.split(" ")[0].equalsIgnoreCase("unmark");
    }

    /**
     * Check if the user add an event
     * @param s user's input
     * @return true if it's an event; otherwise false.
     */
    public Boolean checkEvent(String s) {
        return s.contains("/at");
    }

    /**
     * Check if the user add a deadline
     * @param s user's input
     * @return true if it's a deadline; otherwise false.
     */
    public Boolean checkDeadline(String s) {
        return s.contains("/by");
    }

    /**
     * Check if the user add a todo
     * @param s user's input
     * @return true if it's a todo; otherwise false.
     */
    public Boolean checkToDo(String s) {
        return s.split(" ")[0].equalsIgnoreCase("todo");
    }

    /**
     * Retrieve input from the user
     * @return user's input string
     */
    public String inputFromUser() {
        return sc.nextLine().strip().replaceAll("(?m)^\\s*$[\n\r]+", "");
    }

    /**
     * Get user's choice for command
     * @return user's input command
     */
    public int userCommand() {
        return Integer.parseInt(sc.nextLine());
    }

    /**
     * Get user's choice for task
     * @return user's input task
     */
    public int userTask() {
        return Integer.parseInt(sc.nextLine());
    }

    /**
     * info to the user based on his input.
     * @param userInput user's input string.
     * @param isBye boolean value to show if the user asked for an exit command
     * @param isList boolean value to show if the user asked for a list command
     * @param isMark boolean value to show if the user asked for a mark command
     * @param isUnmark boolean value to show if the user asked for an unmark command
     * @return chatbot info
     */
    public String infoToUser(String userInput, boolean isBye, boolean isList, boolean isMark, boolean isUnmark) {
        if (isBye) {
            return goodbyeGreeting;
        } else if (isList){
            return listString;
        } else if (isMark || isUnmark){
            return userInput.split(" ")[1];
        } else {
            return userInput;
        }
    }

    /**
     * Add an event or a deadline
     * @param s user's input
     */
    public Task addToList(String s, String time, String type) {
        Task task = new Task(s);
        switch (type) {
            case "todo":
                task = new ToDo(s);
                tasks.add(task);
                break;
            case "event":
                task = new Event(s, time);
                tasks.add(task);
                break;
            case "deadline":
                task = new Deadline(s, time);
                tasks.add(task);
                break;
        }
        return task;
    }

    /**
     * Return the target task.
     * @param index task index
     * @return the target task.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * A bridging method for changing the status of a task
     */
    public void changeTaskStatus(int taskIndex, boolean status) {
        tasks.get(taskIndex).changeStatus(status);
    }

    /**
     * Say hi to the user.
     */
    public void startGreeting() {
        System.out.println(splitLine);
        System.out.println(" ".repeat(10) + initialGreeting);
        System.out.println(" ".repeat(10) + commandList);
        System.out.println(splitLine);
    }

    /**
     * Say goodbye to the user.
     */
    public void sayBye() {
        System.out.println(splitLine);
        System.out.println(" ".repeat(10) + goodbyeGreeting);
        System.out.println(splitLine);
    }

    /**
     * Display chatbot info with list command
     * @param info chatbot info
     * @param isList boolean value to show if the user asked for a list command
     * @param isMark boolean value to show if the user asked for a mark command
     * @param isUnmark boolean value to show if the user asked for an unmark command
     */
    public void display(String info, boolean isList, boolean isMark, boolean isUnmark) {
        if (isList) {
            System.out.println(splitLine);
            System.out.println(" ".repeat(10) + "Here are all your tasks:");
            for(int i = 0; i < tasks.size(); ++i) {
                System.out.println(" ".repeat(10) + (i + 1) + ". " + tasks.get(i));
            }
            System.out.println(splitLine);
        } else if (isMark) {
            System.out.println(splitLine);
            System.out.println(" ".repeat(10) + "Successfully marked! You can see it in your task " +
                    "list as follows:");
            System.out.println(" ".repeat(10) + info);
            System.out.println(splitLine);
        } else if (isUnmark) {
            System.out.println(splitLine);
            System.out.println(" ".repeat(10) + "Successfully unmarked! You can see it in your task " +
                    "list as follows:");
            System.out.println(" ".repeat(10) + info);
            System.out.println(splitLine);
        } else {
            System.out.println(splitLine);
            System.out.println(" ".repeat(10) + "Successfully added! You can see it in your task " +
                    "list as follows:");
            System.out.println(" ".repeat(10) + info);
            System.out.println(splitLine);
        }
    }

    /**
     * Display the command list.
     */
    public void showCommandList() {
        System.out.println(splitLine);
        System.out.println(" ".repeat(10) + "Anything else? I'm always here for you!");
        System.out.println(" ".repeat(10) + commandList);
        System.out.println(splitLine);
    }
}
