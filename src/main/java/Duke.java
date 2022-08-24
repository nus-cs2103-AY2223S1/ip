import java.util.*;

public class Duke {
    public static void main(String[] args) {
        TaskList itemList = new TaskList();
        Scanner sc= new Scanner(System.in);
        int index;
        System.out.print("Hello! I'm Duke \nWhat can I do for you? \n");

        String command = sc.nextLine();
        boolean carryOn = true;

        while (carryOn){
            try {
                String[] words = command.split(" ", 2);
                String[] taskName = command.split("/");

                switch (words[0]) {
                    // Exit
                    case "bye":
                        carryOn = false;
                        System.out.print("Bye. Hope to see you again soon!");
                        break;
                    // List out items
                    case "list":
                        System.out.println(itemList);
                        command = sc.nextLine();
                        break;
                    case "delete":
                        itemList.deleteTask(words[1]);
                        command = sc.nextLine();
                        break;
                    // mark items
                    case "mark":
                        index = Integer.parseInt(words[1]) - 1;
                        itemList.markTask(index);
                        command = sc.nextLine();
                        break;
                    // unmark items
                    case "unmark":
                        index = Integer.parseInt(words[1]) - 1;
                        itemList.unmarkTask(index);
                        command = sc.nextLine();
                        break;
                    case "todo":
                        if (words.length > 1) {
                            Task toAdd = new ToDo(taskName[0].substring(5));
                            itemList.addTask(toAdd);
                            command = sc.nextLine();
                        } else {
                            throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
                        }
                        break;
                    case "deadline":
                        if (words.length == 1) {
                            throw new DukeException("OOPS!!! The description of a Deadline cannot be empty.");
                        } else if (taskName.length == 1 || taskName[1].length() < 3) {
                            throw new DukeException("OOPS!!! The time and date of the Deadline cannot be empty.");
                        } else {
                            //eg. by 2019-10-03 18:00
                            String[] dateTime = taskName[1].split(" ");
                            String date = dateTime[1];
                            String time = dateTime[2];

                            Task toAdd = new Deadline(taskName[0].substring(9), date, time);
                            itemList.addTask(toAdd);
                            command = sc.nextLine();
                        }
                        break;
                    case "event":
                        if (words.length == 1) {
                            throw new DukeException("OOPS!!! The description of a Event cannot be empty.");
                        } else if (taskName.length == 1 || taskName[1].length() < 3) {
                            throw new DukeException("OOPS!!! The time and date of the Event cannot be empty.");
                        } else {
                            String[] dateTime = taskName[1].split(" ");
                            //eg. at 2019-10-3 10:30-16:00
                            String date = dateTime[1];
                            String[] time = dateTime[2].split("-");

                            Task toAdd = new Event(taskName[0].substring(6), date, time[0], time[1]);
                            itemList.addTask(toAdd);
                            command = sc.nextLine();
                        }
                        break;
                    // unrecognised commands
                    default:
                        throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
                command = sc.nextLine();
            }
        }


    }
}
