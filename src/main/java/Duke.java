import java.util.Scanner;
public class Duke {
    private static Task[] storage = new Task[100];
    private static int i = 0;
    private static void mark(String userInput) {
        int intCollect = Integer.parseInt(userInput.substring(5));
        storage[intCollect - 1].mark();
    }

    private static void unmark(String userInput) {
        int intCollect = Integer.parseInt(userInput.substring(7));
        storage[intCollect - 1].unmark();
    }

    private static void printList(String userInput) {
        System.out.println("Here are the tasks in your list:");
        int j = 0;
        while (storage[j] != null) {
            System.out.println((j + 1) + "." + storage[j].toString());
            j++;
        }
    }

    private static void toDo(String userInput) throws DukeException {
        if (userInput.length() <= 5) {
            throw new DukeException();
        }
        storage[i] = new ToDo(userInput.substring(5));
        i++;
        System.out.println("Got it. I've added this task:\n"
                +storage[i-1].toString()
                + "\nNow you have " + i + " tasks in the list.");
    }

    private static void deadline(String userInput) throws DukeException {
        if (userInput.length() <= 9) {
            throw new DukeException();
        }
        String[] box = userInput.substring(9).split(" /by ");
        storage[i] = new Deadline(box[0], box[1]);
        i++;
        System.out.println("Got it. I've added this task:\n"
                +storage[i-1].toString()
                + "\nNow you have " + i + " tasks in the list.");
    }

    private static void event(String userInput) throws DukeException {
        if (userInput.length() <= 6) {
            throw new DukeException();
        }
        String[] box = userInput.substring(6).split(" /at ");
        storage[i] = new Event(box[0], box[1]);
        i++;
        System.out.println("Got it. I've added this task:\n"
                +storage[i-1].toString()
                + "\nNow you have " + i + " tasks in the list.");
    }
    private static void exceptionThrower() throws DukeException {
        throw new DukeException();
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
                printList(userInput);
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
