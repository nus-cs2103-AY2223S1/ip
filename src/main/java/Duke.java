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
                taskList.markTaskAsDone(Integer.parseInt(input.substring(5)) - 1);
            } else if (input.startsWith("unmark")) {
                taskList.markTaskAsUnDone(Integer.parseInt(input.substring(7)) - 1);
            } else if (input.startsWith("todo")) {
                taskList.addTask(input.substring(5), Task.TaskType.ToDo);
            } else if (input.startsWith("event")) {
                taskList.addTask(input.substring(6), Task.TaskType.Event);
            } else if (input.startsWith("deadline")) {
                taskList.addTask(input.substring(9), Task.TaskType.Deadline);
            } else {
                System.out.println("Invalid input. Please enter again");
            }
        }

        //Level 4
        //Level 5
        //Level 6
        //A-TextUiTesting
        //Test commit

    }
}
