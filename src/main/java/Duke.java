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
            } else if (input.startsWith("deadline ")) {
                String[] deadline = input.replace("deadline ", "").split(" /by ");
                addDeadline(deadline[0], deadline[1]);
            } else if (input.startsWith("todo ")) {
                String todo = input.replace("todo ", "");
                addTodo(todo);
            } else if (input.startsWith("event ")) {
                String[] event = input.replace("event ", "").split(" /at ");
                addEvent(event[0], event[1]);
            } else {
                addTask(input);
            }
        }

        exitMessage();
    }

    public void markAsDone(int taskNum) {
        tasks.get(taskNum - 1).markAsDone();
        linePrint();
        System.out.println("\tNice! I've marked this task as done:\n\t" +
                tasks.get(taskNum - 1).toString());
        linePrint();
    }

    public void markAsUndone(int taskNum) {
        tasks.get(taskNum - 1).markAsUndone();
        linePrint();
        System.out.println("\tOK, I've marked this task as not done yet:\n\t" +
                tasks.get(taskNum - 1).toString());
        linePrint();
    }

    public void addDeadline(String description, String by) {
        Deadline newDeadline = new Deadline(description, by);
        tasks.add(newDeadline);
        linePrint();
        System.out.println("\tGot it. I've added this task:\n\t" +
                newDeadline.toString() +
                "\n\tNow you have " + tasks.size() + " tasks in the list.");
        linePrint();
    }

    public void addTodo(String description) {
        Todo newTodo = new Todo(description);
        tasks.add(newTodo);
        linePrint();
        System.out.println("\tGot it. I've added this task:\n\t" +
                newTodo.toString() +
                "\n\tNow you have " + tasks.size() + " tasks in the list.");
        linePrint();
    }

    public void addEvent(String description, String time) {
        Event newEvent = new Event(description, time);
        tasks.add(newEvent);
        linePrint();
        System.out.println("\tGot it. I've added this task:\n\t" +
                newEvent.toString() +
                "\n\tNow you have " + tasks.size() + " tasks in the list.");
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
            System.out.println("\t" + (i + 1) + ". " + tasks.get(i).toString());
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

    public void printMessage(String input) {
        linePrint();
        System.out.println('\t' + input);
        linePrint();
    }

    public void linePrint() {
        System.out.println("\t____________________________________________________________");
    }
}
