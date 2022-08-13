import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Chatbot {

    private final String name;
    private boolean isActive;

    private Scanner scanner;

    private List<Task> taskList;

    public Chatbot(String name) {
        this.name = name;
        this.isActive = true;
        this.scanner = new Scanner(System.in);
        this.taskList = new ArrayList<>();
    }

    public void start() {

        System.out.println("Hey, I am " + this.name + ". I can manage your tasks for you"); // Welcome text
        System.out.println("Type 'todo' to create a todo.");
        System.out.println("Type 'deadline' to create a task with deadline.");
        System.out.println("Type 'event' to create an event with a start and end time.");
        System.out.println("Type 'list' to list out all your tasks.");
        System.out.println("Type 'mark' to complete a task.");
        System.out.println("Type 'unmark' to un-complete a task.");
        System.out.println("Type 'bye' to finish the conversation.");

        while (this.isActive) {

            System.out.print("Type your instructions to " + this.name + ": ");
            String msgToChatbot = this.scanner.nextLine();
            System.out.println(this.name + " says: " + receiveMsg(msgToChatbot));

        }

    }

    private String receiveMsg(String msg) {

        if (msg.equals("bye")) {
            this.isActive = false;
            return "Goodbye, see you soon!";
        } else if (msg.equals("todo")) {
            return makeTodo();
        } else if (msg.equals("deadline")) {
            return makeDeadline();
        } else if (msg.equals("event")) {
            return makeEvent();
        } else if (msg.equals("list")) {
            return getListOfTasks();
        } else if (msg.equals("mark")) {
            return markTask();
        } else if (msg.equals("unmark")) {
            return unmarkTask();
        } else {
            return "I don't know what you want me to do :(";
        }

    }

    private String makeTodo() {

        System.out.print("What is the name of your todo: ");
        String name = this.scanner.nextLine();

        try {
            Task task = new Todo(name, false);
            this.taskList.add(task);
            return "I created a Todo " + task + " for you.";
        } catch (TaskNoNameException e) {
            return e.getMessage();
        }

    }

    private String makeDeadline() {

        System.out.print("What is the name of your deadline task: ");
        String name = this.scanner.nextLine();

        System.out.print("What is the deadline of your task: ");
        String deadline = this.scanner.nextLine();

        try {
            Task task = new Deadline(name, false, deadline);
            this.taskList.add(task);
            return "I created a Deadline " + task + " for you.";
        } catch (TaskNoNameException e) {
            return e.getMessage();
        }

    }

    private String makeEvent() {

        System.out.print("What is the name of your event: ");
        String name = this.scanner.nextLine();

        System.out.print("What is the start time of your event: ");
        String start = this.scanner.nextLine();

        System.out.print("What is the end time of your event: ");
        String end = this.scanner.nextLine();

        try {
            Task task = new Event(name, false, start, end);
            this.taskList.add(task);
            return "I created a Event " + task + " for you.";
        } catch (TaskNoNameException e) {
            return e.getMessage();
        }

    }

    private String markTask() {

        System.out.print("What is the index of the task: ");
        int i = this.scanner.nextInt() - 1;
        this.scanner.nextLine();
        this.taskList.get(i).setDone(true);
        return "Your task " + this.taskList.get(i) + " has been completed.";

    }

    private String unmarkTask() {

        System.out.print("What is the index of the task: ");
        int i = this.scanner.nextInt() - 1;
        this.scanner.nextLine();
        this.taskList.get(i).setDone(false);
        return "Your task " + this.taskList.get(i) + " has been un-completed.";

    }

    private String getListOfTasks() {

        StringBuilder sb = new StringBuilder();
        sb.append("Here are the list of tasks you have:\n");

        for (int i = 0; i < this.taskList.size(); i++) {
            sb.append(i + 1).append(". ").append(this.taskList.get(i)).append("\n");
        }

        return sb.toString();

    }

}
