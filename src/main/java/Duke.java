import java.util.Scanner;
import main.java.Task;

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
            // cannot use ==
            if (output.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (output.equals("list")) {
                printList(taskList);
            } else if (output.contains("unmark")) {
                // solution below taken from https://stackoverflow.com/questions/33186650/input-string-and-int-in-the-same-line
                String[] val = output.split(" ");
                int idx = Integer.parseInt(val[1]);
                Task undone = taskList[idx];
                markAsUndone(undone);
            } else if (output.contains("mark")) {
                String[] val = output.split(" ");
                int idx = Integer.parseInt(val[1]);
                Task done = taskList[idx];
                markAsDone(done);
            } else {
                    Task newtask = new Task(output);
                    taskList[index] = newtask;
                    index++;
                    System.out.println("added: " + printOnAdd(newtask));

            }
        }
    }

    public static void printList(Task[] list) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i < index + 1; i++) {
            System.out.println(i + "." + printTask(list[i - 1]));
        }
    }
}
