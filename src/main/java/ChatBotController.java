import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class is a logic controller for a chatbot
 */
public class ChatBotController {

    private final Scanner sc = new Scanner(System.in);
    private final String initialGreeting = "Hello, I'm Logits. What can I do for you?";
    private final String goodbyeGreeting = "Bye. Hope to see you soon!";
    private final String listString = "list";
    private final String commandList = "1. Add a ToDo\n" + "2. Add an Event\n" + "3. Add a Deadline\n" +
            "4. List all Tasks\n" + "5. Mark\n" + "6. Unmark\n" + "7. Exit";
    private final String splitLine = "*".repeat(80);
    private final ArrayList<Task> tasks = new ArrayList<>();

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
    public int userCommand() throws InvalidCommandException {
        String command = sc.nextLine();
        if (!checkCommand(command)) {
            throw new InvalidCommandException("ERROR: The command you input is invalid!");
        }
        return Integer.parseInt(command);
    }

    /**
     * Get user's choice for task
     * @return user's input task
     */
    public int userTask() {
        return Integer.parseInt(sc.nextLine());
    }

    /**
     * Check the legitimacy of user's input for command
     * @param s input command
     * @return boolean value
     */
    public boolean checkCommand(String s) {
        for (int i = 1; i <= 8; ++i) {
            if (s.strip().equals(Integer.toString(i))) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check the legitimacy of user's input of a task
     * @param s the task content
     * @return boolean value
     */
    public boolean checkContent(String s) {
        return !s.isBlank();
    }

    /**
     * Check the legitimacy of user's input of time
     * @param s time string
     * @return boolean value
     */
    public boolean checkTime(String s) {
        Pattern pattern1 = Pattern.compile("[0-9]+/[0-9]+/[0-9]+");
        Pattern pattern2 = Pattern.compile("[0-9]+:[0-9]+:[0-9]+");
        Matcher matcher1 = pattern1.matcher(s);
        Matcher matcher2 = pattern2.matcher(s);
        return matcher1.find() && matcher2.find();
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
        System.out.println(initialGreeting);
        System.out.println(commandList);
        System.out.println(splitLine);
    }

    /**
     * Say goodbye to the user.
     */
    public void sayBye() {
        System.out.println(splitLine);
        System.out.println(goodbyeGreeting);
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
            System.out.println("Here are all your tasks:");
            for(int i = 0; i < tasks.size(); ++i) {
                System.out.println( + (i + 1) + ". " + tasks.get(i));
            }
            System.out.println(splitLine);
        } else if (isMark) {
            System.out.println(splitLine);
            System.out.println("Successfully marked! You can see it in your task list as follows:");
            System.out.println(info);
            System.out.println(splitLine);
        } else if (isUnmark) {
            System.out.println(splitLine);
            System.out.println( "Successfully unmarked! You can see it in your task list as follows:");
            System.out.println(info);
            System.out.println(splitLine);
        } else {
            System.out.println(splitLine);
            System.out.println("Successfully added! You can see it in your task list as follows:");
            System.out.println(info);
            System.out.println(splitLine);
        }
    }

    public void showSplitLine() {
        System.out.println(splitLine);
    }

    /**
     * Display the command list.
     */
    public void showCommandList() {
        System.out.println("Anything else? I'm always here for you!");
        System.out.println(commandList);
        System.out.println(splitLine);
    }
}
