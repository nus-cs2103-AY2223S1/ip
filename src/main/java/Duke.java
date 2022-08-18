import java.util.Scanner;
import java.io.*;
import java.util.*;

public class Duke {

    public static void main(String[] args) {
        //start dialog
        start();
        Scanner scanner = new Scanner(System.in);
        String command = scanner.next();

        ArrayList<Task> todoList = new ArrayList<Task>();

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
                for (int i = 0; i < todoList.size(); i++) {
                    System.out.println("    " + (i+1) + ". [" + todoList.get(i).getStatusIcon() + "] " + todoList.get(i).getDescription());
                }
                System.out.println("    ____________________________________________________________");
                command = scanner.next();
            }
            else if (command.equals("mark")) {
                command = scanner.next();
                System.out.println("    ____________________________________________________________");
                System.out.println("    Nice! I've marked this task as done:");
                System.out.println("    " + "[X] " + todoList.get(Integer.parseInt(command)-1).getDescription());
                System.out.println("    ____________________________________________________________");
                command = scanner.next();
            }
            else if (command.equals("unmark")) {
                command = scanner.next();
                System.out.println("    ____________________________________________________________");
                System.out.println("   OK, I've marked this task as not done yet:");
                System.out.println("    " + "[ ] " + todoList.get(Integer.parseInt(command)-1).getDescription());
                System.out.println("    ____________________________________________________________");
                command = scanner.next();

            }
            else {
                System.out.println("    ____________________________________________________________\n" +
                        "     added: " +
                        command +
                        "\n    ____________________________________________________________");
                Task t = new Task(command);
                todoList.add(t);
                command = scanner.next();
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