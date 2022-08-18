import java.util.Scanner;

import main.java.*;

import static main.java.Task.*;

public class Duke {

    private static Scanner sc = new Scanner(System.in);
    private static Task[] taskList = new Task[100];
    private static int index = 0;


    public static void main(String[] args) throws DukeException {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        while (sc.hasNext()) {
            String input = sc.nextLine();
            String[] breakitdown = input.split(" ");
            String command = breakitdown[0];
            Task newTask;
            try {
                if (command.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                } else if (command.equals("list")) {
                    if (index == 0) {
                        System.out.println("There are currently no tasks in the list.");
                    } else {
                        printList(taskList);
                    }
                } else if (command.equals("unmark")) {
                    int idx = Integer.parseInt(breakitdown[1]);
                    Task undone = taskList[idx - 1];
                    markAsUndone(undone);
                } else if (command.equals("mark")) {
                    int idx = Integer.parseInt(breakitdown[1]);
                    Task done = taskList[idx - 1];
                    markAsDone(done);
                } else if (command.equals("todo")) {
                    if (breakitdown.length == 1) {
                        throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
                    }
                    String taskName = input.substring(5);
                    newTask = new ToDos(taskName);
                    taskList[index] = newTask;
                    index++;
                    printOnAdd(newTask);
                } else if (command.equals("deadline")) {
                    if (breakitdown.length == 1) {
                        throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
                    }
                    String desc = input.substring(9);
                    if (!desc.contains("/by")) {
                        throw new DukeException("OOPS!!! /by keyword not found!");
                    }
                    String[] taskNameBy = desc.split("/by ");
                    if (taskNameBy.length == 1) {
                        throw new DukeException("OOPS!!! Date of deadline cannot be empty.");
                    }
                    String taskName = taskNameBy[0];
                    String by = taskNameBy[1];
                    newTask = new Deadlines(taskName, by);
                    taskList[index] = newTask;
                    index++;
                    printOnAdd(newTask);
                } else if (command.equals("event")) {
                    if (breakitdown.length == 1) {
                        throw new DukeException("OOPS!!! The description of an event cannot be empty.");
                    }
                    String desc = input.substring(6);
                    if (!desc.contains("/at")) {
                        throw new DukeException("OOPS!!! /at keyword not found!");
                    }
                    String[] taskNameLocation = desc.split("/at ");
                    if (taskNameLocation.length == 1) {
                        throw new DukeException("OOPS!!! Location of event cannot be empty.");
                    }
                    String taskName = taskNameLocation[0];
                    String location = taskNameLocation[1];
                    newTask = new Events(taskName, location);
                    taskList[index] = newTask;
                    index++;
                    printOnAdd(newTask);
                } else {
                    throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                System.out.println(e.toString());
            }
        }
    }

    public static void printList(Task[] list) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i < index + 1; i++) {
            System.out.println(i + "." + list[i - 1].toString());
        }
    }

    public static void printOnAdd(Task task) {
        System.out.println("Got it. I've added this task:");
        System.out.println(" " + task.toString());
        System.out.println("Now you have " + index + " task" + (index == 1 ? " " : "s ") + "in the list");
    }
}
