import java.util.HashMap;
import java.util.Scanner;

public class Duke {
    int num = 1; // Number of tasks that are created.
    HashMap<Integer, String> map = new HashMap<>();

    public static void main(String[] args) {
        String command;

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Scanner sc = new Scanner(System.in);
        command = sc.nextLine();
        Duke duke = new Duke();
        while (true) {
            if (command.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } // Say goodbye.
            duke.PrintCommand(command);
            command = sc.nextLine();
        }
    }

    /*
    * PrintCommand function to print out the current command.
    *
    * @param command.
    */
    public void PrintCommand(String command) {
        if ((command.charAt(0) == 'm' && command.charAt(1) == 'a') &&
                (command.charAt(2) == 'r' && command.charAt(3) == 'k')) {
            int number = Integer.parseInt(command.substring(5));
            String prev = map.get(number);
            Task task = new Task(prev.substring(5));
            task.SetDone();
            map.put(number, number + ".[" + task.getStatusIcon() + "]" + prev.substring(5));
            System.out.println("Nice! I've marked this task as done:\n" + "[" + task.getStatusIcon() + "]" + " " + prev.substring(5));
        } else if (((command.charAt(0) == 'u' && command.charAt(1) == 'n') &&
                (command.charAt(2) == 'm' && command.charAt(3) == 'a')) &&
                (command.charAt(4) == 'r' && command.charAt(5) == 'k')) {
            int number = Integer.parseInt(command.substring(7));
            String prev = map.get(number);
            Task task = new Task(prev.substring(5));
            task.SetNotDone();
            map.put(number, number + ".[" + task.getStatusIcon() + "]" + prev.substring(5));
            System.out.println("OK, I've marked this task as not done yet:\n" + "[" + task.getStatusIcon() + "]" + " " + prev.substring(5));
        } else {
            if (!command.equals("list") && !command.equals("bye")) {
                Task task = new Task(command);
                System.out.println("added: " + command);
                map.put(num, num + ".[" + task.getStatusIcon() + "]" + command);
                num++;
            } // Add tasks to the list and print out current task.
            else if (command.equals("list")) {
                String list = ""; // Initial list.
                for (int k = 1; k < num; k++) {
                    list += map.get(k) + "\n";
                }
                System.out.println(list);
            } // print out the list.
        }
    }

}
