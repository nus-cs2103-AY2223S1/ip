import java.util.*;

public class Duke {
    public static void main(String[] args) {
        TaskList itemList = new TaskList();
        Scanner sc= new Scanner(System.in);

        System.out.print("Hello! I'm Duke \nWhat can I do for you? \n");

        String command = sc.nextLine();
        boolean carryOn = true;

        while (carryOn){
            String[] words = command.split(" ", 2);
            String[] taskName = command.split("/");

            // Exit
            if (command.equals("bye")) {
                carryOn = false;
                System.out.print("Bye. Hope to see you again soon!");
            }
            // List out items
            else if (command.equals("list")) {
                System.out.println(itemList);
                command = sc.nextLine();
            }
            // mark items
            else if (words[0].equals("mark")) {
                int index = Integer.parseInt(words[1]) - 1;
                itemList.markTask(index);
                command = sc.nextLine();
            }
            // unmark items
            else if (words[0].equals("unmark")) {
                int index = Integer.parseInt(words[1]) - 1;
                itemList.unmarkTask(index);
                command = sc.nextLine();
            }
            else if (words[0].equals("todo")) {
                Task toAdd = new ToDo(taskName[0].substring(5));
                itemList.addTask(toAdd);
                command = sc.nextLine();
            }
            else if (words[0].equals("deadline")) {
                Task toAdd = new Deadline(taskName[0].substring(9), taskName[1].substring(3));
                itemList.addTask(toAdd);
                command = sc.nextLine();
            }
            else if (words[0].equals("event")) {
                Task toAdd = new Event(taskName[0].substring(5), taskName[1].substring(3));
                itemList.addTask(toAdd);
                command = sc.nextLine();
            }
            // unrecognised commands
            else {
                System.out.print(command + "\n");
                command = sc.nextLine();
            }
        }


    }
}
