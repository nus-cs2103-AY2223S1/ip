import java.util.Locale;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    public static void echo(String echoCommand) {
        System.out.println("--------------------------------------------------");
        System.out.println(echoCommand);
        System.out.println("--------------------------------------------------");
    }

    public static void main(String[] args) {
        echo("Hello! I'm Duke\nWhat can I do for you?");
        ArrayList<Task> tasklist = new ArrayList<Task>();

        String command = "";
        while (true) {
            Scanner userInput = new Scanner(System.in);// Create a Scanner object
            command = userInput.nextLine(); // Read user input
            if ("bye".equals(command)) {
                echo("Bye. Hope to see you again soon!");
                break;
            } else if ("list".equals(command)) {
                String taskRecords = "";
                for (int i = 0; i < tasklist.size(); i++) {
                    String taskRecord="";
                    if (i == tasklist.size() - 1) {
                        taskRecord = String.format("%d.%s", i + 1, tasklist.get(i));
                    } else {
                        taskRecord = String.format("%d.%s\n", i + 1, tasklist.get(i));
                    }
                    taskRecords += taskRecord;
                }
                echo(taskRecords);
            } else if (command.contains("unmark")) { // to detect unmark command
                String number = command.replaceAll("[^\\d.]", "");
                int n = Integer.parseInt(number);
                Task unmarkedTask = tasklist.get(n-1);
                unmarkedTask.markAsUndone();
                String taskStatus = String.format("OK, I've marked this task as not done yet:\n%s", unmarkedTask);
                echo(taskStatus);
            } else if (command.contains("mark")){ // to detect mark command
                String number = command.replaceAll("[^\\d.]", "");
                int n = Integer.parseInt(number);
                Task markedTask = tasklist.get(n-1);
                markedTask.markAsDone();
                String taskStatus = String.format("Nice! I've marked this task as done:\n%s", markedTask);
                echo(taskStatus);
            } else if (command.contains("todo")) {
                String todoTask = command.replace("todo ", "");
                if (todoTask.equals(command) || "".equals(todoTask)) {
                    String error = DukeException.taskErrorMessage(command);
                    echo(error);
                } else {
                    Task newTask = new Todo(todoTask);
                    tasklist.add(newTask);
                    String taskStatus = String.format("Got it. I've added this task:\n" +
                            "%s\n" +
                            "Now you have %d tasks in the list.", newTask, tasklist.size());
                    echo(taskStatus);
                }

            } else if (command.contains("deadline")) {
                String deadlineTask = command.replace("deadline ", "");
                if (deadlineTask.equals(command) || "".equals(deadlineTask)) {
                    String error = DukeException.taskErrorMessage(command);
                    echo(error);
                } else {
                    String[] parts = deadlineTask.split(" /");
                    Task newTask = new Deadline(parts[0], parts[1]);
                    tasklist.add(newTask);
                    String taskStatus = String.format("Got it. I've added this task:\n" +
                            "%s\n" +
                            "Now you have %d tasks in the list.", newTask, tasklist.size());
                    echo(taskStatus);
                }
            } else if (command.contains("event")) {
                String eventTask = command.replace("event ", "");
                if (eventTask.equals(command) || "".equals(eventTask)) {
                    String error = DukeException.taskErrorMessage(command);
                    echo(error);
                } else {
                    String[] parts = eventTask.split(" /");
                    Task newTask = new Event(parts[0], parts[1]);
                    tasklist.add(newTask);
                    String taskStatus = String.format("Got it. I've added this task:\n" +
                            "%s\n" +
                            "Now you have %d tasks in the list.", newTask, tasklist.size());
                    echo(taskStatus);
                }
            } else if (command.toLowerCase().contains("delete")) {
                String deleteTaskNumber = command.replace("delete ", "");
                if (deleteTaskNumber.equals(command) || "".equals(deleteTaskNumber)) {
                    String error = DukeException.taskErrorMessage(command);
                    echo(error);
                } else {
                    int n = Integer.parseInt(deleteTaskNumber);
                    Task deletedTask = tasklist.remove(n-1);
                    String taskStatus = String.format("Noted. I've removed this task:\n" +
                            "%s\n" +
                            "Now you have %d tasks in the list.", deletedTask, tasklist.size());
                    echo(taskStatus);
                }
            } else {
                echo(DukeException.taskErrorMessage());
            }
        }
    }
}
