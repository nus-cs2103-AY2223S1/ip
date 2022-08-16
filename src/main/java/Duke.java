import java.sql.Array;
import java.util.ArrayList;
import java.util.Scanner;
public class Duke {
    private static ArrayList<Task> storage = new ArrayList<>();
    private static int i = 0;
    private static void mark(String userInput) {
        int intCollect = Integer.parseInt(userInput.substring(5));
        storage.get(intCollect - 1).mark();
    }

    private static void unmark(String userInput) {
        int intCollect = Integer.parseInt(userInput.substring(7));
        storage.get(intCollect - 1).unmark();
    }

    private static void printList() {
        final int[] a = {1};
        System.out.println("Here are the tasks in your list:");
        storage.forEach(x -> {
            System.out.println(a[0] + "." +  storage.get(a[0] - 1).toString());
            a[0]++;
        });
    }

    private static void toDo(String userInput) throws DukeException {
        if (userInput.length() <= 5) {
            throw new DukeException();
        }
        storage.add(new ToDo(userInput.substring(5)));
        i++;
        System.out.println("Got it. I've added this task:\n"
                +storage.get(i-1).toString()
                + "\nNow you have " + i + " tasks in the list.");
    }

    private static void deadline(String userInput) throws DukeException {
        if (userInput.length() <= 9) {
            throw new DukeException();
        }
        String[] box = userInput.substring(9).split(" /by ");
        storage.add(new Deadline(box[0], box[1]));
        i++;
        System.out.println("Got it. I've added this task:\n"
                +storage.get(i-1).toString()
                + "\nNow you have " + i + " tasks in the list.");
    }

    private static void event(String userInput) throws DukeException {
        if (userInput.length() <= 6) {
            throw new DukeException();
        }
        String[] box = userInput.substring(6).split(" /at ");
        storage.add(new Event(box[0], box[1]));
        i++;
        System.out.println("Got it. I've added this task:\n"
                +storage.get(i-1).toString()
                + "\nNow you have " + i + " tasks in the list.");
    }
    private static void exceptionThrower() throws DukeException {
        throw new DukeException();
    }

    private static void delete(String userInput) {
        int intCollection = Integer.parseInt(userInput.substring(7));
        Task deleted = storage.remove(intCollection - 1);
        System.out.println("Noted. I've removed this task:\n" + deleted.toString()
                            + "\nNow you have " + storage.size() + " tasks in the list.");
    }

    public static void main(String[] args) {
        System.out.println("Hey Dude here\n" + "What can I do for you?");
        Scanner sc = new Scanner(System.in);
        String userInput = sc.nextLine();
        while (!userInput.equals("bye")) {
            if (userInput.startsWith("mark ")) {
                mark(userInput);
            } else if (userInput.startsWith("unmark ")) {
                unmark(userInput);
            } else if (userInput.equals("list")) {
                printList();
            } else if (userInput.equals("todo") || userInput.startsWith("todo" )) {
                try {
                    toDo(userInput);
                } catch (DukeException e) {
                    System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                }
            } else if (userInput.equals("deadline") || userInput.startsWith("deadline ")) {
                try {
                    deadline(userInput);
                } catch (DukeException e) {
                    System.out.println("☹ OOPS!!! The description of a deadline cannot be empty.");
                }
            } else if (userInput.equals("event") || userInput.startsWith("event ")) {
                try {
                    event(userInput);
                } catch (DukeException e) {
                    System.out.println("☹ OOPS!!! The description of a event cannot be empty.");
                }
            } else if (userInput.startsWith("delete ")) {
                delete(userInput);
            }
            else {
                try {
                    exceptionThrower();
                }
                catch (DukeException e){
                    System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            }
            userInput = sc.nextLine();
        }
        sc.close();
        System.out.println("Bye. Hope to see you again soon!");
    }
}
