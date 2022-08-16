import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private ArrayList<Task> taskList;
    private Scanner scanner;

    public Duke() {
        this.taskList = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        System.out.println("Hello I'm Duke\n What can I do for you?");


        while (scanner.hasNextLine()) {
            System.out.println();
            String ss = scanner.nextLine();

            String[] splitSS = ss.split(" ");

            switch (splitSS[0]) {
                case "bye":
                    terminate();
                    return;
                case "list":
                    list();
                    break;
                case "mark":
                    changeMarkStatus(ss, true);
                    break;
                case "unmark":
                    changeMarkStatus(ss, false);
                    break;
                case "todo":
                    todo(ss);
                    break;
                case "deadline":
                    deadline(ss);
                    break;
                case "event":
                    event(ss);
                    break;
                default:
//                    Task task = new Task(ss);
//                    add(task);
//                    System.out.println("added: " + ss);
                    System.out.println("     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
    }

    private void terminate() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    private void list() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); ++i) {
            System.out.printf(" %d. %s\n", i + 1, taskList.get(i));
        }
    }

    private void changeMarkStatus(String input, boolean mark) {
        int indexOfTask = Integer.parseInt(input.split((" "))[1]) - 1;

        if (mark) {
            this.taskList.get(indexOfTask).completed();
            System.out.println("Nice! I've marked this task as done:");
        } else {
            this.taskList.get(indexOfTask).uncompleted();
            System.out.println("OK, I've marked this task as not done yet:");
        }
        System.out.printf("\t%s\n", this.taskList.get(indexOfTask));
    }

    private void add(Task s) {
        taskList.add(s);
    }

    private void todo(String input) {

        String[] splitInput = input.split(" ");

        if (splitInput.length < 2) {
            System.out.println("     ☹ OOPS!!! The description of a todo cannot be empty.");
            return;
        }

        System.out.println("Got it. I've added this task");
        StringBuilder todo = new StringBuilder();

        for (int i = 1; i < splitInput.length; ++i) {
            todo.append(' ');
            todo.append(splitInput[i]);
        }

        ToDo todoTask = new ToDo(todo.toString());
        taskList.add(todoTask);
        System.out.printf("\t %s\n", todoTask);
        System.out.printf("Now you have %d tasks in the list.\n", this.taskList.size());
    }

    private void deadline(String input) {
        String[] splitInput = input.split(" ");
        System.out.println("Got it. I've added this task");
        StringBuilder deadline = new StringBuilder();

        for (int i = 1; i < splitInput.length; ++i) {
            if (splitInput[i].equals("/by")) break;
            deadline.append(" " + splitInput[i]);
        }

        String date = input.split("/by")[1].trim();
        Deadline deadlineTask = new Deadline(deadline.toString(), date);
        taskList.add(deadlineTask);
        System.out.printf("\t %s\n", deadlineTask);
        System.out.printf("Now you have %d tasks in the list.\n", this.taskList.size());
    }

    private void event(String input) {
        String[] splitInput = input.split(" ");
        System.out.println("Got it. I've added this task");
        StringBuilder event = new StringBuilder();

        for (int i = 1; i < splitInput.length; ++i) {
            if (splitInput[i].equals("/at")) break;
            event.append(" " + splitInput[i]);
        }

        Event eventTask = new Event(event.toString(), input.split("/at")[1].trim());
        taskList.add(eventTask);
        System.out.printf("\t %s\n", eventTask);
        System.out.printf("Now you have %d tasks in the list.\n", this.taskList.size());
    }

}
