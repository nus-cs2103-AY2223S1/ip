import java.util.*;

public class Duke {
    public static void main(String[] args) {
        TaskList itemList = new TaskList();
        Scanner sc= new Scanner(System.in);

        System.out.print("Hello! I'm Duke \nWhat can I do for you? \n");

        String command = sc.nextLine();
        boolean carryOn = true;

        while (carryOn){
            try {
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
                else if (words[0].equals("delete")) {
                    itemList.deleteTask(words[1]);
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
                    if (words.length > 1) {
                        Task toAdd = new ToDo(taskName[0].substring(5));
                        itemList.addTask(toAdd);
                        command = sc.nextLine();
                    } else {
                        throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
                    }
                }
                else if (words[0].equals("deadline")) {
                    if (words.length == 1) {
                        throw new DukeException("OOPS!!! The description of a Deadline cannot be empty.");
                    } else if (taskName.length == 1 || taskName[1].length() < 3) {
                        throw new DukeException("OOPS!!! The time and date of the Deadline cannot be empty.");
                    } else {
                        Task toAdd = new Deadline(taskName[0].substring(9), taskName[1].substring(3));
                        itemList.addTask(toAdd);
                        command = sc.nextLine();
                    }
                }
                else if (words[0].equals("event")) {
                    if (words.length == 1) {
                        throw new DukeException("OOPS!!! The description of a Event cannot be empty.");
                    } else if (taskName.length == 1 || taskName[1].length() < 3) {
                        throw new DukeException("OOPS!!! The time and date of the Event cannot be empty.");
                    } else {
                        Task toAdd = new Event(taskName[0].substring(6), taskName[1].substring(3));
                        itemList.addTask(toAdd);
                        command = sc.nextLine();
                    }
                }
                // unrecognised commands
                else {
                    throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
                command = sc.nextLine();
            }
        }


    }
}
