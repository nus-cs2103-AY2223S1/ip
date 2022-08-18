import java.util.Scanner;
import java.io.*;
import java.util.*;

public class Duke {

    public static void main(String[] args) {
        //start dialog
        start();
        Scanner scanner = new Scanner(System.in);
        String command = scanner.next();

        ArrayList<Task> taskList = new ArrayList<Task>();

        while (true) {
            //bye dialog
            if (command.equals("bye")) {
                bye();
                break;
            }
            //show the todoList
            else if (command.equals("list")) {
                System.out.println("    ____________________________________________________________");
                System.out.println("    Here are the tasks in your list:");
                for (int i = 0; i < taskList.size(); i++) {
                    System.out.println("    " + (i+1) + ". " + taskList.get(i).toString());
                }
                System.out.println("    ____________________________________________________________");
                command = scanner.next();
            }
            else if (command.equals("mark")) {
                command = scanner.next();
                System.out.println("    ____________________________________________________________");
                System.out.println("    Nice! I've marked this task as done:");
                int i = Integer.parseInt(command) - 1;
                Task t = taskList.get(i);
                t.markAsDone();
                System.out.println("    " + t.toString());
                System.out.println("    ____________________________________________________________");
                command = scanner.next();
            }
            else if (command.equals("unmark")) {
                command = scanner.next();
                System.out.println("    ____________________________________________________________");
                System.out.println("    OK, I've marked this task as not done yet:");
                int i = Integer.parseInt(command) - 1;
                Task t = taskList.get(i);
                t.markAsUnDone();
                System.out.println("    " + t.toString());
                System.out.println("    ____________________________________________________________");
                command = scanner.next();

            }
            else if (command.equals(delete)) {
                command = scanner.next();
                System.out.println("    ____________________________________________________________");
                System.out.println("    Noted. I've removed this task:");
                int i = Integer.parseInt(command) - 1;
                Task t = taskList.get(i);
                t.markAsUnDone();
                System.out.println("    " + t.toString());
                int num = taskList.size();
                System.out.println("    Now you have " + num + " tasks in the list.");
                System.out.println("    ____________________________________________________________");
                command = scanner.next();

            }
            else if (command.equals("todo")) {
                command = scanner.nextLine();
                System.out.println("    ____________________________________________________________");
                System.out.println("    Got it. I've added this task:");
                Todo t = new Todo(command);
                taskList.add(t);
                System.out.println("     " + t.toString());
                int num = taskList.size();
                System.out.println("    Now you have " + num + " tasks in the list.");
                System.out.println("    ____________________________________________________________");
                command = scanner.next();
            }
            else if (command.equals("deadline")) {
                command = scanner.nextLine();
                int i = command.indexOf('/');
                String des = command.substring(0, i);
                String by = command.substring(i+4);
                System.out.println("    ____________________________________________________________");
                System.out.println("    Got it. I've added this task:");
                Deadline d = new Deadline(des, by);
                taskList.add(d);
                System.out.println("     " + d.toString());
                int num = taskList.size();
                System.out.println("    Now you have " + num + " tasks in the list.");
                System.out.println("    ____________________________________________________________");
                command = scanner.next();
            }
            else if (command.equals("event")) {
                command = scanner.nextLine();
                int i = command.indexOf('/');
                String des = command.substring(0, i);
                String at = command.substring(i+4);
                System.out.println("    ____________________________________________________________");
                System.out.println("    Got it. I've added this task:");
                Event e = new Event(des, at);
                taskList.add(e);
                System.out.println("     " + e.toString());
                int num = taskList.size();
                System.out.println("    Now you have " + num + " tasks in the list.");
                System.out.println("    ____________________________________________________________");
                command = scanner.next();
            }
            else {
//                System.out.println("    ____________________________________________________________\n" +
//                        "     added: " +
//                        command +
//                        "\n    ____________________________________________________________");
//                Task t = new Task(command);
//                todoList.add(t);
//                command = scanner.next();
            }
        }



    }

    public static void start() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        //System.out.println("Hello from\n" + logo);
        System.out.println("    ____________________________________________________________\n" +
                "     Hola! I'm Ashy (//●⁰౪⁰●)//\n" +
                "     What can I do for you? my darling~\n" +
                "    ____________________________________________________________");
    }

    public static void bye() {
        System.out.println("    ____________________________________________________________\n" +
                "     Byeeee~ Hope to see you again soon (•͈⌔•͈⑅)\n" +
                "    ____________________________________________________________");
    }

}