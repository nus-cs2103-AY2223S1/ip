import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm Duke\n" + "What can I do for you?\n");

        Scanner sc = new Scanner(System.in);

        List list = new List(100);

        while(true) {
            String input = sc.nextLine();
            String[] spacedArr = input.split(" ", 2);
            String command = spacedArr[0];
            
            if (command.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!\n");
                return;
            } else if (command.equals("list")) {
                System.out.println(list);
                continue;
            }
            
            try {
                if (!command.equals("mark") && !command.equals("unmark") && !command.equals("todo") && !command.equals("deadline") && !command.equals("event") && !command.equals("delete")) {
                    throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
                
                if (spacedArr.length == 1) {
                    throw new DukeException("OOPS!!! The description cannot be empty.");
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage() + "\n");
                continue;
            } 

            String suffix = spacedArr[1];
            
            if (command.equals("mark")) {
                int pos = Integer.parseInt(suffix) - 1;
                list.markTask(pos, true);
            } else if (command.equals("unmark")) {
                int pos = Integer.parseInt(suffix) - 1;
                list.markTask(pos, false);
            } else if (command.equals("todo")) {
                Task task = new ToDoTask(suffix);
                list.addTask(task);
            } else if (command.equals("deadline")) {
                String by = input.split(" /by ")[1];
                String description = suffix.split(" /by ")[0];
                Task task = new DeadlineTask(description, by);
                list.addTask(task);
            } else if (command.equals("event")) {
                String at = input.split(" /at ")[1];
                String description = suffix.split(" /at ")[0];
                Task task = new EventTask(description, at);
                list.addTask(task);
            } else if (command.equals("delete")) {
                int pos = Integer.parseInt(suffix) - 1;
                list.deleteTask(pos);
            }
        }
    }
}