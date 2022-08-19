import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static ArrayList<Task> taskList = new ArrayList<>();

    private static String getOutput(String input) {
        if (input.equals("bye")) {
            return "Bye. Hope to see you again soon!";
        } else if (input.equals("list")) {
            return "Here are the tasks in your list:\n" + listTasks();
        } else if (input.length() > 3 && input.substring(0, 4).equals("mark")) {
            int index = Integer.parseInt(input.substring(5)) - 1;
            Task currTask = taskList.get(index);
            currTask.markAsDone();
            return "Nice! I've marked this task as done\n  " + currTask.toString();
        } else if (input.length() > 5 && input.substring(0, 6).equals("unmark")) {
            int index = Integer.parseInt(input.substring(7)) - 1;
            Task currTask = taskList.get(index);
            currTask.markAsNotDone();
            return "OK, I've marked this task as not done yet:\n  " + currTask.toString();
        } else if (input.length() > 3 && input.substring(0, 4).equals("todo")) {
            Task newTask = new Todo(input.substring(5));
            addTask(newTask);
            return "Got it. I've added this task:\n  " + newTask.toString() +
                    "\nNow you have " + taskList.size() + " tasks in the list";
        } else if (input.length() > 7 && input.substring(0, 8).equals("deadline")) {
            String taskInput = input.substring(9);
            String[] taskAndDate = taskInput.split("/by ");
            Task newTask = new Deadline(taskAndDate[0], taskAndDate[1]);
            addTask(newTask);
            return "Got it. I've added this task:\n  " + newTask.toString() +
                    "\nNow you have " + taskList.size() + " tasks in the list";
        } else if (input.length() > 5 && input.substring(0, 5).equals("event")) {
            String taskInput = input.substring(6);
            String[] taskAndDate = taskInput.split("/at ");
            Task newTask = new Event(taskAndDate[0], taskAndDate[1]);
            addTask(newTask);
            return "Got it. I've added this task:\n  " + newTask.toString() +
                    "\nNow you have " + taskList.size() + " tasks in the list";
        } else {
            return "invalid command";
        }
    }

    private static void addTask(Task task) {
        taskList.add(task);
    }

    private static String listTasks() {
        String tasks = "";
        for (int i = 0; i < taskList.size(); i++) {
            tasks += (i + 1) + ". " + taskList.get(i).toString() + "\n";
        }
        return tasks;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println(logo + "\nHello! I'm Duke\n" + "What can I do for you?");
        String command = scanner.nextLine();
        while (true) {
            String output = getOutput(command);
            System.out.println(output);
            if (command.equals("bye")) {
                break;
            } else {
                command = scanner.nextLine();
            }
        }
    }
}
