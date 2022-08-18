import java.lang.reflect.Array;
import java.util.*;

public class Duke {
    public static void main(String[] args) {

//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);

//        Task[] list = new Task[100];
        ArrayList<Task> list = new ArrayList<>();
        int i = 0;
        boolean loop = true;
        System.out.println("Hello! Duke here, how can I help you?");
        Scanner sc = new Scanner(System.in);

        while (loop) {
            if (sc.hasNext("bye")) {
                loop = false;
                break;
            }

            if (sc.hasNext("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int j = 0; j < list.size(); j++) {
                    System.out.println(j+1 + ". " + list.get(j).getTask());
                }
                sc.nextLine();
                continue;
            }

            if (sc.hasNext("mark")) {
                sc.next();
                if (sc.hasNextInt()) {
                    int input = sc.nextInt();
                    try {
                        list.get(input - 1).markAsDone();
                        System.out.println("Good job! Task " + input + " has been completed:");
                        System.out.println("  " + list.get(input - 1).getTask());
                    } catch (IndexOutOfBoundsException | NullPointerException e) {
                        System.out.println("You've given me an invalid task to mark!");
                    }
                }
                sc.nextLine();
                continue;
            }

            if (sc.hasNext("unmark")) {
                sc.next();
                if (sc.hasNextInt()) {
                    int input = sc.nextInt();
                    try {
                        list.get(input - 1).markAsUndone();
                        System.out.println("Got it! Task " + input + " has not yet been completed:");
                        System.out.println("  " + list.get(input - 1).getTask());
                    } catch (IndexOutOfBoundsException | NullPointerException e) {
                        System.out.println("You've given me an invalid task to unmark!");
                    }
                }
                sc.nextLine();
                continue;
            }

            if (sc.hasNext("delete")) {
                sc.next();
                if (sc.hasNextInt()) {
                    int input = sc.nextInt();
                    try {
                        Task removedTask = list.get(input - 1);
                        list.remove(input - 1);
                        i--;
                        System.out.println("Got it! Task " + input + " has been deleted from the list:");
                        System.out.println("  " + removedTask.getTask());
                        System.out.println("You have a total of " + i + " tasks in the list.");
                    } catch (IndexOutOfBoundsException | NullPointerException e) {
                        System.out.println("You've given me an invalid task to delete!");
                    }
                }
                sc.nextLine();
                continue;
            }

            String str = sc.next();

            if (str.contains("todo")) {
                String task = sc.nextLine();
                if (task.equals("")) {
                    throw new DukeException("The description of a task cannot be empty!");
                } else {
                    Todo todo = new Todo(task);
                    list.add(todo);
                    i++;
                    System.out.println("I've added this task to the list:");
                    System.out.println("  " + todo.getTask());
                    System.out.println("You have a total of " + i + " tasks in the list.");
                }
            } else if (str.contains("deadline")) {
                String task = sc.nextLine();
                if (task.equals("")) {
                    throw new DukeException("The description of a task cannot be empty!");
                } else if (!task.contains("/by")) {
                    throw new DukeException("Invalid input for a deadline!");
                } else {
                    Deadline deadline = new Deadline(task);
                    list.add(deadline);
                    i++;
                    System.out.println("I've added this task to the list:");
                    System.out.println("  " + deadline.getTask());
                    System.out.println("You have a total of " + i + " tasks in the list.");
                }
            } else if (str.contains("event")) {
                String task = sc.nextLine();
                if (task.equals("")) {
                    throw new DukeException("The description of a task cannot be empty!");
                } else if (!task.contains("/at")) {
                    throw new DukeException("Invalid input for an event!");
                } else {
                    Event event = new Event(task);
                    list.add(event);
                    i++;
                    System.out.println("I've added this task to the list:");
                    System.out.println("  " + event.getTask());
                    System.out.println("You have a total of " + i + " tasks in the list.");
                }
            } else {
                throw new DukeException("Invalid input!");
            }
        }

        System.out.println("Thanks for having me!\nHave a nice day ahead!");
    }
}
