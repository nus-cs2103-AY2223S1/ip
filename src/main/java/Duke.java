import java.util.Scanner;

import main.java.Deadlines;
import main.java.Events;
import main.java.Task;
import main.java.ToDos;

import static main.java.Task.*;

public class Duke {

    private static Scanner sc = new Scanner(System.in);
    private static Task[] taskList = new Task[100];
    private static int index = 0;


    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        while (sc.hasNext()) {
            String output = sc.nextLine();
            String[] breakitdown = output.split(" ");
            String command = breakitdown[0];
            Task newTask;
            if (command.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (command.equals("list")) {
                printList(taskList);
            } else if (command.equals("unmark")) {
                int idx = Integer.parseInt(breakitdown[1]);
                Task undone = taskList[idx - 1];
                markAsUndone(undone);
            } else if (command.equals("mark")) {
                int idx = Integer.parseInt(breakitdown[1]);
                Task done = taskList[idx - 1];
                markAsDone(done);
            } else if (command.equals("todo")) {
                String taskName = output.substring(5);
                newTask = new ToDos(taskName);
                taskList[index] = newTask;
                index++;
                printOnAdd(newTask);
            } else if (command.equals("deadline")) {
                String desc = output.substring(9);
                String taskName = desc.split("/by ")[0];
                String by = desc.split("/by ")[1];
                newTask = new Deadlines(taskName, by);
                taskList[index] = newTask;
                index++;
                printOnAdd(newTask);
            } else if (command.equals("event")) {
                String desc = output.substring(6);
                String taskName = desc.split("/at ")[0];
                String location = desc.split("/at ")[1];
                newTask = new Events(taskName, location);
                taskList[index] = newTask;
                index++;
                printOnAdd(newTask);
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
