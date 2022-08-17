import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static List<Task> tasks = new ArrayList<>();

    public static void endSession(Scanner input) {
        System.out.println("Bye! See you next time!");
        input.close();
    }

    public static void showTasks() {
        int id = 1;
        for (Task task: tasks) {
            System.out.println(id + "." + task.toString());
            id += 1;
        }
    }

    public static void markTaskAsDone(int taskIndex) {
        Task currTask = tasks.get(taskIndex - 1); // label starting from 1
        currTask.markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + currTask.toString());
    }

    public static void markTaskAsNotDone(int taskIndex) {
        Task currTask = tasks.get(taskIndex - 1); // label starting from 1
        currTask.markAsNotDone();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + currTask.toString());
    }

    public static void addTodo(String[] inputs) {
        StringBuilder todoName = new StringBuilder();
        for (int i = 1; i < inputs.length; i++) {
            todoName.append(inputs[i] + " ");
        }
        Todo newTodo = new Todo(todoName.toString());
        tasks.add(newTodo);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + newTodo.toString());
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    public static void addDeadline(String[] inputs) {
        StringBuilder deadlineName = new StringBuilder();
        StringBuilder endDateTime = new StringBuilder();
        Boolean readDateTime = false;
        for (int i = 1; i < inputs.length - 1; i++) {
            if (inputs[i].equals("/by")) {
                readDateTime = true;
                continue;
            }
            if (!readDateTime) {
                deadlineName.append(inputs[i] + " ");
            } else {
                endDateTime.append(inputs[i] + " ");
            }
        }
        // To prevent space at the end of the string
        endDateTime.append(inputs[inputs.length - 1]);

        Deadline newDeadline = new Deadline(deadlineName.toString(), endDateTime.toString());
        tasks.add(newDeadline);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + newDeadline.toString());
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    public static void addEvent(String[] inputs) {
        StringBuilder eventName = new StringBuilder();
        StringBuilder periodDateTime = new StringBuilder();
        Boolean readDateTime = false;
        for (int i = 1; i < inputs.length - 1; i++) {
            if (inputs[i].equals("/at")) {
                readDateTime = true;
                continue;
            }
            if (!readDateTime) {
                eventName.append(inputs[i] + " ");
            } else {
                periodDateTime.append(inputs[i] + " ");
            }
        }
        // To prevent space at the end of the string
        periodDateTime.append(inputs[inputs.length - 1]);

        Event newEvent = new Event(eventName.toString(), periodDateTime.toString());
        tasks.add(newEvent);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + newEvent.toString());
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    public static void main(String[] args) {
        System.out.println("Hello there! My name's Duck");
        System.out.println("Please type in a command...");
        Scanner input = new Scanner(System.in);
        while (true) {
            String inputLine = input.nextLine();
            String[] inputArr = inputLine.split(" ");

            if (inputLine.equals("bye")) {
                endSession(input);
                return;
            } else if (inputLine.equals("list")) {
                if (tasks.isEmpty()) System.out.println("You have no tasks...");
                showTasks();
            } else if (inputArr[0].equals("mark")) {
                markTaskAsDone(Integer.parseInt(inputArr[1]));
            } else if (inputArr[0].equals("unmark")) {
                markTaskAsNotDone(Integer.parseInt(inputArr[1]));
            } else if (inputArr[0].equals("todo")) {
                addTodo(inputArr);
            } else if (inputArr[0].equals("deadline")) {
                addDeadline(inputArr);
            } else if (inputArr[0].equals("event")) {
                addEvent(inputArr);
            } else {
                System.out.println("I don't get what you are saying...");
            }
        }
    }
}
