import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private boolean hasExited = false;
    private ArrayList<Task> taskList;

    public Duke() {
        this.taskList = new ArrayList<>();
    }

    public void chat() {
        greetUser();
        Scanner sc = new Scanner(System.in);
        while(!hasExited) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                hasExited = true;
            } else if (input.equals("list")) {
                printList();
            } else if (input.startsWith("mark ")) {
                int taskNum = Integer.parseInt(input.replace("mark ", ""));
                markTaskAsDone(taskNum);
            } else if (input.startsWith("unmark ")) {
                int taskNum = Integer.parseInt(input.replace("unmark ", ""));
                markTaskAsNotDone(taskNum);
            } else {
                echo(input);
                addToList(input);
            }
        }
        exitMessage();
    }

    public void markTaskAsDone(int index) {
        Task currTask = this.taskList.get(index - 1);
        currTask.markAsDone();

        generateLine();
        printFormatted("Nice! I've marked this task as done:");
        System.out.println("\t   " + currTask);
        generateLine();
    }

    public void markTaskAsNotDone(int index) {
        Task currTask = this.taskList.get(index - 1);
        currTask.markAsNotDone();

        generateLine();
        printFormatted("OK, I've marked this task as not done yet:");
        System.out.println("\t   " + currTask);
        generateLine();
    }

    public void addToList(String input) {
        Task t = new Task(input);
        this.taskList.add(t);
    }

    public void printList() {
        generateLine();
        printFormatted("Here are the tasks in your list:");
        for (int i = 0; i < this.taskList.size(); i++) {
            Task t =  this.taskList.get(i);
            String currLine = "\t " + (i + 1) + "." + t;
            System.out.println(currLine);
        }
        generateLine();
    }

    public void greetUser() {
        generateLine();
        printFormatted("Hello! I'm Zeus");
        printFormatted("What can I do for you?");
        generateLine();
    }

    public void exitMessage() {
        generateLine();
        printFormatted("Bye. Hope to see you again soon!");
        generateLine();
    }

    public void echo(String input) {
        generateLine();
        System.out.println("\t added: " + input);
        generateLine();

    }

    public void generateLine() {
        System.out.println("\t____________________________________________________________");
    }

    public void printFormatted(String message) {
        System.out.println("\t " + message);
    }

    public static void main(String[] args) {
        Duke zeus = new Duke();
        zeus.chat();
    }
}
