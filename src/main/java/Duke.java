import java.util.*;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Task[] taskList = new Task[100];
        int pointer = 0;
        System.out.println("Hello I'm Duke! What can I do for you?");
        while (true) {
            String input = sc.nextLine();
            if (input.equals("bye")) { //bye
                System.out.println("  ----\n  Goodbye!\n  ----");
                break;
            } else if (input.equals("list")) { //list
                String toDisplay = "  ----\n";
                int temp = 1;
                for (int i = 0; i < pointer; i++) {
                    toDisplay += "  " + temp + ": " + taskList[i].toString() + "\n";
                    temp++;
                }
                toDisplay += "  ----";
                System.out.println(toDisplay);
            } else if (input.startsWith("mark")) { //mark
                String digits = input.substring(5);
                int index = Integer.parseInt(digits) - 1;
                taskList[index].taskDone();
                System.out.println("  ----\n  I've marked this task as done!\n  " + taskList[index] + "\n  ----");
            } else if (input.startsWith("unmark")) { //unmark
                String digits = input.substring(7);
                int index = Integer.parseInt(digits) - 1;
                taskList[index].taskUndone();
                System.out.println("  ----\n  I've marked this task as not done..\n  " + taskList[index] + "\n  ----");
            } else if (input.startsWith("todo")) { //todo
                Todo task = new Todo(input);
                System.out.println("  ----\n  added: " + task.toString() + "\n  ----");
                taskList[pointer] = task;
                pointer++;
                System.out.println("  You currently have " + pointer + " tasks in the list.");
            } else if (input.startsWith("deadline")) { //deadline
                String[] segments = input.split("/");
                Deadline task = new Deadline(segments[0], segments[1].substring(3));
                System.out.println("  ----\n  added: " + task.toString() + "\n  ----");
                taskList[pointer] = task;
                pointer++;
                System.out.println("  You currently have " + pointer + " tasks in the list.");
            } else if (input.startsWith("event")) { //event
                String[] segments = input.split("/");
                Event task = new Event(segments[0], segments[1].substring(3));
                System.out.println("  ----\n  added: " + task.toString() + "\n  ----");
                taskList[pointer] = task;
                pointer++;
                System.out.println("  You currently have " + pointer + " tasks in the list.");
            }
        }
    }
}


