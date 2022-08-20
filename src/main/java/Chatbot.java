import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Chatbot {

    private final String name;
    private boolean isActive;

    private final Scanner scanner;

    private final TaskList taskList;
    private final Storage storage;

    public Chatbot(String name) {
        this.name = name;
        this.isActive = true;
        this.scanner = new Scanner(System.in);
        this.storage = new Storage("tasks.dukedata");
        this.taskList = StorageConverter.stringToTasks(this.storage);
    }

    private void printInstructions() {

        System.out.println("==========================");
        System.out.println("Hey, I am " + this.name + ". I can manage your tasks for you"); // Welcome text
        System.out.println("Type 'todo' to create a todo.");
        System.out.println("Type 'deadline' to create a task with deadline.");
        System.out.println("Type 'event' to create an event with a start and end time.");
        System.out.println("Type 'list' to list out all your tasks.");
        System.out.println("Type 'mark' to complete a task.");
        System.out.println("Type 'unmark' to un-complete a task.");
        System.out.println("Type 'delete' to remove a task.");
        System.out.println("Type 'bye' to finish the conversation.");
        System.out.println("==========================");

    }

    public void start() {

        printInstructions();

        while (this.isActive) {

            System.out.print("Type your instructions to " + this.name + ": ");
            String msgToChatbot = this.scanner.nextLine();
            System.out.println(this.name + " says: " + receiveMsg(msgToChatbot));

        }

    }

    private String receiveMsg(String msg) {

        switch (msg) {
        case "bye":
            this.isActive = false;
            return "Goodbye, see you soon!";
        case "todo":
            return makeTodo();
        case "deadline":
            return makeDeadline();
        case "event":
            return makeEvent();
        case "list":
            return getListOfTasks();
        case "mark":
            return markTask();
        case "unmark":
            return unmarkTask();
        case "delete":
            return removeTask();
        default:
            printInstructions();
            return "I don't know what you want me to do :( Please type exactly as per the instructions above.";
        }

    }

    private String makeTodo() {
        return TaskTodo.makeTodo(this.scanner, this.taskList);
    }

    private String makeDeadline() {
        return TaskDeadline.makeDeadline(this.scanner, this.taskList);
    }

    private String makeEvent() {
        return TaskEvent.makeEvent(this.scanner, this.taskList);
    }

    private String markTask() {

        System.out.print("What is the index of the task: ");
        int i = this.scanner.nextInt() - 1;
        this.scanner.nextLine();
        this.taskList.get(i).setDone(true);
        this.taskList.mutatedTask();
        return "Your task " + this.taskList.get(i) + " has been completed.";

    }

    private String unmarkTask() {

        System.out.print("What is the index of the task: ");
        int i = this.scanner.nextInt() - 1;
        this.scanner.nextLine();
        this.taskList.get(i).setDone(false);
        this.taskList.mutatedTask();
        return "Your task " + this.taskList.get(i) + " has been un-completed.";

    }

    private String removeTask() {

        System.out.print("What is the index of the task: ");
        int i = this.scanner.nextInt() - 1;
        this.scanner.nextLine();
        String taskStr = this.taskList.get(i).toString();
        this.taskList.remove(i);
        return "Your task " + taskStr + " has been deleted.";

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
