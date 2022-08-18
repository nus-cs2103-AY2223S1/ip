import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static char triangle = '\u25B8';
    public static String start = triangle + " ";
    public static ArrayList<Task> toDoList = new ArrayList<>(100);

    public static void printList() {
        if (toDoList.size() == 0) {
            System.out.println(start + "your list is empty. start adding some tasks to do now!");
        } else {
            System.out.println(start + "these are the tasks in your list:");
            int x = 1;
            for (Task task : toDoList) {
                System.out.println("  " + x + ". " + task.toString());
                x++;
            }
        }
    }

    public static void addToList(Task t) {
        toDoList.add(t);
        System.out.println(start + "added:\n" + "     " + t);
        if (toDoList.size() == 1) {
            System.out.println("  You now have 1 task in the list. Type list to see it!");
        } else {
            System.out.println("  Now you have " + toDoList.size() + " tasks in the list. Type list to view them.");
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(start + "Hi, I'm Duke!\n  What would you like to do today?");

        Scanner sc = new Scanner(System.in);
        while (true) {

            String input = sc.nextLine();
            String[] arr = input.split(" ", 2);
            if (arr[0].equals("bye")) {
                System.out.println(start + "Bye! I hope to see you again soon!");
                break;
            }

            switch (arr[0]) {
                case "list":
                    printList();
                    break;
                case "mark":
                    try {
                        if (toDoList.size() == 0) {
                            System.out.println(
                                    start + "Hmm, you do not have any tasks in your list to be marked. Add some now!"
                            );
                            break;
                        }
                        Task doneTask = toDoList.get(Integer.parseInt(arr[1]) - 1);
                        doneTask.setDone();
                        System.out.println(start + "good job! this task has been marked as done:");
                        System.out.println("     " + doneTask);
                    } catch (NumberFormatException | IndexOutOfBoundsException e) {
                        System.out.println(start + "please enter a valid integer from 1 - " + toDoList.size());
                    }
                    break;
                case "unmark":
                    try {
                        if (toDoList.size() == 0) {
                            System.out.println(start + "Hmm, you do not have any tasks in your list. Add some now!");
                            break;
                        }
                        Task undoneTask = toDoList.get(Integer.parseInt(arr[1]) - 1);
                        undoneTask.setUndone();
                        System.out.println(start + "ok, i've marked this task as not done yet:");
                        System.out.println("     " + undoneTask);
                    } catch (NumberFormatException | IndexOutOfBoundsException e) {
                        System.out.println(start + "please enter a valid integer from 1 - " + toDoList.size());
                    }
                    break;
                case "todo":
                    try {
                        addToList(new ToDo(arr[1]));
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println(start + "Please tell me the name of the ToDo task.");
                    }
                    break;
                case "deadline":
                    try {
                        String[] dl = arr[1].split(" /by ", 2);
                        addToList(new Deadline(dl[0], dl[1]));
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println(
                                start + "For tasks with deadlines, please tell me the name of the task, followed " +
                                        "by '/by', and then the date/time it needs to be completed by."
                        );
                    }
                    break;
                case "event":
                    try {
                        String[] e = arr[1].split(" /", 2);
                        String[] timeInfo = e[1].split(" ", 2);
                        addToList(new Event(e[0], timeInfo[0], timeInfo[1]));
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println(
                                start + "For events, please tell me the name of the event, when it starts and when it ends."
                        );
                    }
                    break;
                default:
                    toDoList.add(new Task(input));
                    System.out.println(start + "added: " + input);
            }
        }
    }
}
