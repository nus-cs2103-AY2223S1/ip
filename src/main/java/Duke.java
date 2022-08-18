import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static ArrayList<Task> tasks = new ArrayList<>();

    private Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.runBot();
    }

    public void runBot() {
        greetingMessage();

        boolean exitBot = false;
        while (!exitBot) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                exitBot = true;
            } else if (input.equals("list")) {
                listTasks();
            } else if (input.startsWith("mark ")) {
                int taskNum = Integer.parseInt(input.replace("mark ", ""));
                markAsDone(taskNum);
            } else if (input.startsWith("unmark ")) {
                int taskNum = Integer.parseInt(input.replace("unmark ", ""));
                markAsUndone(taskNum);
            } else {
                addTask(input);
            }
        }

        exitMessage();
    }

    public void markAsDone(int taskNum) {
        tasks.get(taskNum - 1).markAsDone();
        linePrint();
        System.out.println("\tNice! I've marked this task as done:\n" +
                "\t[" + tasks.get(taskNum - 1).getStatusIcon() + "] " + tasks.get(taskNum - 1).description);
        linePrint();
    }

    public void markAsUndone(int taskNum) {
        tasks.get(taskNum - 1).markAsUndone();
        linePrint();
        System.out.println("\tOK, I've marked this task as not done yet:\n" +
                "\t[" + tasks.get(taskNum - 1).getStatusIcon() + "] " + tasks.get(taskNum - 1).description);
        linePrint();
    }

    public void addTask(String input) {
        tasks.add(new Task(input));
        printMessage("added: " + input);
    }

    public void listTasks() {
        linePrint();
        System.out.println("\tHere are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("\t" + (i + 1) + ". [" + tasks.get(i).getStatusIcon() + "] " + tasks.get(i).description);
        }
        linePrint();
    }

    public void greetingMessage() {
        String greeting = "Hello! I'm Duke\n\t" +
                "What can I do for you?";
        printMessage(greeting);
    }

    public void exitMessage() {
        String exit = "Bye. Hope to see you again soon!";
        printMessage(exit);
    }

    public void echoInput(String input) {
        printMessage(input);
    }

    public void printMessage(String input) {
        linePrint();
        System.out.println('\t' + input);
        linePrint();
    }

    public void linePrint() {
        System.out.println("\t____________________________________________________________");
    }
}
