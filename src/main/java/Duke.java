import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Hello! I'm Duke\n" +
                "What can I do for you?");
        TaskList taskList = new TaskList();

        while (true) {
            String input = myObj.nextLine().trim();// Read user input
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (input.equals("list")) {
                taskList.printTasks();
            } else if (input.startsWith("mark")) {
                if (input.length() <= 5) {
                    System.out.println("You need to specify the index of the task to mark");
                    continue;
                }
                // -1 to match 0-based index
                taskList.markTaskAsDone(Integer.parseInt(input.substring(5)) - 1);
            } else if (input.startsWith("unmark")) {
                if (input.length() <= 7) {
                    System.out.println("You need to specify the index of the task to unmark");
                    continue;
                }
                taskList.markTaskAsUnDone(Integer.parseInt(input.substring(7)) - 1);
            } else if (input.startsWith("todo")) {
                if (input.length() <= 5) {
                    System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                    continue;
                }
                taskList.addTask(input.substring(5), Task.TaskType.ToDo);
            } else if (input.startsWith("event")) {
                if (input.length() <= 6) {
                    System.out.println("☹ OOPS!!! The description of a event cannot be empty.");
                    continue;
                } else if (! input.contains("/at")) {
                    System.out.println("☹ OOPS!!! The event should have a time.\n" +
                            "e.g. event {name} /at {time}");
                    continue;
                }
                taskList.addTask(input.substring(6), Task.TaskType.Event);
            } else if (input.startsWith("deadline")) {
                if (input.length() <= 9) {
                    System.out.println("☹ OOPS!!! The description of a deadline cannot be empty.");
                    continue;
                } else if (! input.contains("/by")) {
                    System.out.println("☹ OOPS!!! The deadline should have a due date.\n" +
                            "e.g. deadline {name} /by {time}");
                    continue;
                }
                taskList.addTask(input.substring(9), Task.TaskType.Deadline);
            } else if (input.startsWith("delete")) {
                if (input.length() <= 7) {
                    System.out.println("You should specify the index of task to delete");
                    continue;
                }
                taskList.deleteTask(Integer.parseInt(input.substring(7)) - 1);

            } else {
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }

        //Level 4
        //Level 5
        //Level 6
        //A-TextUiTesting
        //Test commit

    }
}
