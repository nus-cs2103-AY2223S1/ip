import java.util.HashMap;
import java.util.Scanner;

public class Duke {
    int num = 0; // Number of tasks that are created.
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
        if (command.equals("list")) {
            String list = "";
            for (int k = 1; k <= num; k++) {
                list += map.get(k) + "\n";
            }
            System.out.println(list);
        }
        else {
            Task task = Task.of(command, map);
            if (task.AddToList()) {
                num++;
                map.put(num, num + "." + task.toString());
            }
            System.out.println(task.toString());
        }
    }

}
