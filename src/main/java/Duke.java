import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static char triangle = '\u25B8';
    private static String start = triangle + " ";
    private static char sad = '\u2639';
    private static String sadFace = sad + " ";
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
            System.out.println("  you now have 1 task in the list. type list to see it!");
        } else {
            System.out.println("  now you have " + toDoList.size() + " tasks in the list. type list to view them.");
        }
    }

    public static void deleteFromList(int i) {
        Task t = toDoList.remove(i);
        System.out.println(start + "okay! i have deleted the following task from your list:");
        System.out.println("     " + t);
        if (toDoList.size() == 0) {
            System.out.println("  your list is now empty. time to add some more!");
        } else if (toDoList.size() == 1) {
            System.out.println("  you now have 1 task remaining in the list. type list to see it!");
        } else {
            System.out.println("  now you have " + toDoList.size() + " tasks in the list. type list to view them.");
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(start + "hi, i'm Duke!\n  what would you like to do today?");

        Scanner sc = new Scanner(System.in);
        while (true) {

            String input = sc.nextLine();
            String[] arr = input.split(" ", 2);
            if (arr[0].equals("bye")) {
                System.out.println(start + "bye! i hope to see you again soon! :)");
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
                                    start + "hmm, you do not have any tasks in your list to be marked. add some now!"
                            );
                            break;
                        }
                        Task doneTask = toDoList.get(Integer.parseInt(arr[1]) - 1);
                        doneTask.setDone();
                        System.out.println(start + "good job! this task has been marked as done:");
                        System.out.println("     " + doneTask);
                    } catch (NumberFormatException | IndexOutOfBoundsException e) {
                        System.out.println(sadFace + "please enter a valid integer from 1 - " + toDoList.size());
                    }
                    break;
                case "unmark":
                    try {
                        if (toDoList.size() == 0) {
                            System.out.println(start + "hmm, you do not have any tasks in your list. add some now!");
                            break;
                        }
                        Task undoneTask = toDoList.get(Integer.parseInt(arr[1]) - 1);
                        undoneTask.setUndone();
                        System.out.println(start + "ok, i've marked this task as not done yet:");
                        System.out.println("     " + undoneTask);
                    } catch (NumberFormatException | IndexOutOfBoundsException e) {
                        System.out.println(sadFace + "please enter a valid integer from 1 - " + toDoList.size());
                    }
                    break;
                case "todo":
                    try {
                        addToList(new ToDo(arr[1]));
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println(sadFace + "please tell me the name of the todo task.");
                    }
                    break;
                case "deadline":
                    try {
                        String[] dl = arr[1].split(" /by ", 2);
                        addToList(new Deadline(dl[0], dl[1]));
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println(
                                sadFace + "for tasks with deadlines, please tell me the name of the task, followed " +
                                        "by '/by',\n  and then the date/time it needs to be completed by."
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
                                sadFace + "for events, please tell me the name of the event, when it starts and when it ends."
                        );
                    }
                    break;
                case "delete":
                    try {
                        if (toDoList.size() == 0) {
                            System.out.println(
                                    start + "hmm, you do not have any tasks in your list to delete. add some now!"
                            );
                            break;
                        }
                        deleteFromList(Integer.parseInt(arr[1]) - 1);

                    } catch (NumberFormatException | IndexOutOfBoundsException e) {
                        System.out.println(sadFace + "please enter a valid integer from 1 - " + toDoList.size());
                    }
                    break;
                default:
                    System.out.println(sadFace + "sorry, i don't know what that means :(");
            }
        }
    }
}
