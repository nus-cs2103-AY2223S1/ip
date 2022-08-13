import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Chatbot {

    private final String name;
    private boolean isActive;

    private List<Task> msgLog;

    public Chatbot(String name) {
        this.name = name;
        this.isActive = true;
        this.msgLog = new ArrayList<>();
    }

    public void start() {

        System.out.println("Hey, I am " + this.name + ". I can manage your tasks for you"); // Welcome text
        System.out.println("Type 'list' to list out all your tasks.");
        System.out.println("Type 'bye' to finish the conversation.");

        Scanner sc = new Scanner(System.in);

        while (this.isActive) {

            System.out.print("Type your task to " + this.name + ": ");
            String msgToChatbot = sc.nextLine();
            System.out.println(this.name + " says: " + receiveMsg(msgToChatbot));

        }

    }

    private String receiveMsg(String msg) {

        if (msg.equals("bye")) {
            this.isActive = false;
            return "Goodbye, see you soon!";
        } else if (msg.startsWith("mark")) {
            return handleMarkTaskCommand(msg);
        } else if (msg.startsWith("unmark")) {
            return handleUnmarkTaskCommand(msg);
        } else if (msg.equals("list")) {
            return getListOfTasks();
        } else {
            msgLog.add(new Task(msg, false));
            return "Added: [" + msg + "]";
        }

    }

    private String handleMarkTaskCommand(String msg) {
        String[] tokens = msg.split(" ");
        int indexOfTask = Integer.parseInt(tokens[1]) - 1;
        this.msgLog.get(indexOfTask).setDone(true);
        return "I have marked your task " + this.msgLog.get(indexOfTask) + " as done.";
    }

    private String handleUnmarkTaskCommand(String msg) {
        String[] tokens = msg.split(" ");
        int indexOfTask = Integer.parseInt(tokens[1]) - 1;
        this.msgLog.get(indexOfTask).setDone(false);
        return "I have marked your task " + this.msgLog.get(indexOfTask) + " as not done.";
    }

    private String getListOfTasks() {

        StringBuilder sb = new StringBuilder();
        sb.append("Here are the list of tasks you have:\n");

        for (int i = 0; i < this.msgLog.size(); i++) {
            sb.append(i + 1).append(". ").append(this.msgLog.get(i)).append("\n");
        }

        return sb.toString();

    }

}
