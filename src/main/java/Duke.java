import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm still evolving!\nWhat can I do for you, my highness?");
        Scanner in = new Scanner(System.in);

        ArrayList<Task> taskList = new ArrayList<Task>();

        while (true) {
            try {
                System.out.println("Your highness:");
                String input = in.nextLine();
                String[] elem = input.split(" ");
                if (input.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                } else if (input.equals("list")) {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 1; i <= taskList.size(); i++) {
                        Task task = taskList.get(i - 1);
                        System.out.println(i + ". " + task.toString());
                    }
                } else if (elem[0].equals("mark")) {
                    int taskNo = Integer.parseInt(elem[1]);
                    Task currTask = taskList.get(taskNo - 1);
                    currTask.setDone();
                    System.out.println("Nice! I've marked this task as done:\n" + "[" + currTask.getStatusIcon() + "] " + currTask.description);
                } else if (elem[0].equals("unmark")) {
                    int taskNo = Integer.parseInt(elem[1]);
                    Task currTask = taskList.get(taskNo - 1);
                    currTask.setUndone();
                    System.out.println("OK, I've marked this task as not done yet:\n" + "[" + currTask.getStatusIcon() + "] " + currTask.description);
                } else if (elem[0].equals("delete")) {
                    int taskNo = Integer.parseInt(elem[1]);
                    Task currTask = taskList.get(taskNo - 1);
                    taskList.remove(taskNo - 1);
                    System.out.println("Noted. I've removed this task:\n" + currTask.toString() +
                            "\nNow you have " + taskList.size() + " tasks left in the list.");
                } else {
                    if (input.equals("todo") || input.equals("deadline") || input.equals("event")) {
                        throw new DukeException("☹ OOPS!!! The description of a " + input + " cannot be empty.");
                    }
                    String[] task = input.split(" ", 2);
                    if (task[0].equals("todo")) {
                        taskList.add(new Todo(task[1]));
                    } else if (task[0].equals("deadline")) {
                        String taskDescription = task[1].split("/", 2)[0];
                        String taskDeadline = task[1].split("/", 2)[1].split(" ", 2)[1];
                        taskList.add(new Deadline(taskDescription, taskDeadline));
                    } else if (task[0].equals("event")) {
                        String taskDescription = task[1].split("/", 2)[0];
                        String taskAt = task[1].split("/", 2)[1].split(" ", 2)[1];
                        taskList.add(new Event(taskDescription, taskAt));
                    } else {
                        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
                    System.out.println("Got it. I've added this task:\n" + taskList.get(taskList.size()-1).toString() +
                            "\nNow you have " + taskList.size() + " tasks in the list.");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
