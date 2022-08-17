import java.util.*;

public class Duke {
    public static void main(String[] args) {
        List list = new List();
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();
        int taskNumber;
        Task task;

        while (!command.equals("bye")) {
            if (command.equals("list")) {
                for (int i = 1; i <= list.taskList.size(); i++) {
                    task = list.taskList.get(i - 1);
                    System.out.println(i + ". " + task.toString() + "\n");
                }

            } else if (command.split(" ")[0].equals("mark")) {
                taskNumber = Integer.parseInt(command.split(" ")[1]);
                task = list.taskList.get(taskNumber - 1);
                task.markTaskAsDone();
                String message = "Congrats on completing this task!";
                System.out.println(message + "\n" + task.toString() + "\n");

            } else if (command.split(" ")[0].equals("unmark")) {
                taskNumber = Integer.parseInt(command.split(" ")[1]);
                task = list.taskList.get(taskNumber - 1);
                task.markTaskAsNotDone();
                String message = "Got it. I've unmarked this task for you ;)";
                System.out.println(message + "\n" + task.toString() + "\n");

            } else if (command.split(" ", 2)[0].equals("todo")) {
                task = new ToDo(command);
                list.addTask(task);
                System.out.println("Got it! I've added this task: \n"
                        + task.toString() + "\n"
                        + "You have " + list.taskList.size() + " tasks in the list.\n");
            } else if (command.split(" ", 2)[0].equals("deadline")) {
                String deadline = command.split(" /by ")[0];
                String by = command.split(" /by ")[1];
                task = new Deadline(deadline, by);
                list.addTask(task);
                System.out.println("Got it! I've added this task: \n"
                        + task.toString() + "\n"
                        + "You have " + list.taskList.size() + " tasks in the list.\n");
            } else if (command.split(" ", 2)[0].equals("event")) {
                String event = command.split(" /at ")[0];
                String at = command.split(" /at ")[1];
                task = new Event(event, at);
                list.addTask(task);
                System.out.println("Got it! I've added this task: \n"
                        + task.toString() + "\n"
                        + "You have " + list.taskList.size() + " tasks in the list.\n");
            }
            else {
                task = new Task(command);
                list.addTask(task);
                System.out.println("Got it! I've added this task: \n"
                        + task.toString() + "\n"
                        + "You have " + list.taskList.size() + " tasks in the list.\n");
            }

            sc = new Scanner(System.in);
            command = sc.nextLine();
        }

        System.out.println("Hiks. I'm sad, but see you again!!");
    }
}
