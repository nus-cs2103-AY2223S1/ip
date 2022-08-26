import java.io.IOException;
import java.nio.file.Path;
import java.util.*;
public class Duke {
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        // create scanner to receive user input
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello! I'm Kuke\n" +
                "What can I do for you?");
        String a = sc.nextLine();
        Task[] arr = new Task[100];

        // if input received is anything but "bye" system will output what the user
        // inputted
        while (!a.equals("bye")) {
            if (a.equals("list")) {
                //lists out all elements in task list
                System.out.println("Here are the tasks in your list:");
                for (int i = 1; i <= Task.getNumberTasks(); i++) {
                    System.out.println(i + "." + arr[i].output());
                }
                a = sc.nextLine();
            } else if (a.contains("unmark")) {
                // if unmark, update status
                char b = a.charAt(7);
                int c = Character.getNumericValue(b);
                arr[c].unMark();
                a = sc.nextLine();
            } else if (a.contains("mark")) {
                char b = a.charAt(5);
                int c = Character.getNumericValue(b);
                arr[c].mark();
                a = sc.nextLine();
            } else if (a.contains("delete")) {
                char b = a.charAt(7);
                int c = Character.getNumericValue(b);
                int numberTasksLeft = Task.getNumberTasks() - 1;
                Task deletedTask = arr[c];
                System.out.println("Noted. I've removed this task:");
                System.out.println(deletedTask.output());
                System.out.println("Now you have " + (numberTasksLeft) + " tasks in the list.");

                Task.numberTasks = Task.getNumberTasks() - 1;

                for (int i = (c - 1); i <= numberTasksLeft; i++) {
                    arr[i] = arr[i + 1];
                }

                a = sc.nextLine();
            } else if (a.contains("todo")) {
                try {
                    a.substring(5);
                } catch (Exception StringIndexOutOfBoundsException) {
                    DukeException.todoException();
                    a = sc.nextLine();
                }

                String description = a.substring(5);
                Todo newTask = new Todo(description);
                arr[Task.getNumberTasks()] = newTask;
                System.out.println("Got it. I've added this task:");
                System.out.println(newTask.output());
                System.out.println("Now you have " + Task.getNumberTasks() + " tasks in the list.");
                a = sc.nextLine();
            } else if (a.contains("deadline")) {
                String description = a.substring(9, a.lastIndexOf("/") - 1);
                String day = a.substring(a.lastIndexOf("/by") + 4);
                String dayDescription = " (by: " + day + ")";
                Deadline newTask = new Deadline(description, dayDescription);
                arr[Task.getNumberTasks()] = newTask;
                System.out.println("Got it. I've added this task:");
                System.out.println(newTask.output());
                System.out.println("Now you have " + Task.getNumberTasks() + " tasks in the list.");
                a = sc.nextLine();
            } else if (a.contains("event")) {
                String description = a.substring(6, a.lastIndexOf("/") - 1);
                String time = a.substring(a.lastIndexOf("/at") + 4);
                String timeDescription = " (at: " + time + ")";
                Event newTask = new Event(description, timeDescription);
                arr[Task.getNumberTasks()] = newTask;
                System.out.println("Got it. I've added this task:");
                System.out.println(newTask.output());
                System.out.println("Now you have " + Task.getNumberTasks() + " tasks in the list.");
                a = sc.nextLine();
            } else {
                // else
//                arr[pos] = a;
//                status[pos] = 0;
//                pos++;
//                System.out.println("added: " + a);
//                a = sc.nextLine();
                DukeException.taskException();
                a = sc.nextLine();
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }

//    public void fileWriter(String filePath) {
//
//        for (int i = 1; i <= pos; i++) {
//            if (status[i - 1] == 0) {
//                System.out.println(i + ".[" + taskType[i - 1] + "][ ] " + arr[i - 1]);
//            } else {
//                System.out.println(i + ".[" + taskType[i - 1] + "][X] " + arr[i - 1]);
//            }
//        }


}

