import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
public class Duke {
    private static ArrayList<Task> storage = new ArrayList<>();
    private static Formatting f = new Formatting();
    private static void mark(String userInput) throws DukeException{
        if (userInput.length() <= 5) {
            throw new DukeException("Mark which one??");
        }
        try {
            Integer.parseInt(userInput.substring(5));
        } catch (Exception e) {
            throw new DukeException("Don't try anything funny!");
        }
        int intCollect = Integer.parseInt(userInput.substring(5));
        try {
            storage.get(intCollect - 1).mark();
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Are you sure that you pressed the right number?");
        }
    }

    private static void unmark(String userInput) throws DukeException {
        if (userInput.length() <= 7) {
            throw new DukeException("Unmark which one??");
        }
        try {
            Integer.parseInt(userInput.substring(7));
        } catch (Exception e) {
            throw new DukeException("Don't try anything funny!");
        }
        int intCollect = Integer.parseInt(userInput.substring(7));
        try {
            storage.get(intCollect - 1).unmark();
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Are you sure that you pressed the right number?");
        }
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
            throw new DukeException("The description of a todo cannot be empty.");
        }

        storage.add(new ToDo(userInput.substring(5)));
        System.out.println("Got it. I've added this task:\n"
                +storage.get(storage.size() - 1).toString()
                + "\nNow you have " + storage.size() + " tasks in the list.");
    }

    private static void deadline(String userInput) throws DukeException {
        if (userInput.length() <= 9) {
            throw new DukeException("The description of a deadline cannot be empty.");
        }
        String[] box = userInput.substring(9).split(" /by ");
        try {
            storage.add(new Deadline(box[0], box[1]));
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Please follow the appropriate format for deadline!");
        }
        System.out.println("Got it. I've added this task:\n"
                +storage.get(storage.size() - 1).toString()
                + "\nNow you have " + storage.size() + " tasks in the list.");
    }

    private static void event(String userInput) throws DukeException {
        if (userInput.length() <= 6) {
            throw new DukeException("The description of a event cannot be empty.");
        }

        String[] box = userInput.substring(6).split(" /at ");
        try {
            storage.add(new Event(box[0], box[1]));
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Please follow the appropriate format for event!");
        }
        System.out.println("Got it. I've added this task:\n"
                +storage.get(storage.size() - 1).toString()
                + "\nNow you have " + storage.size() + " tasks in the list.");
    }
    private static void incomprehensible() throws DukeException {
        throw new DukeException("I'm sorry, but I don't know what that means :-(");
    }

    private static void delete(String userInput) throws DukeException {
        try {
            Integer.parseInt(userInput.substring(7));
        } catch(Exception e) {
            throw new DukeException("Don't try to type funny things!");
        }
        int intCollection = Integer.parseInt(userInput.substring(7));
        try {
            storage.get(intCollection - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("How to delete something not inside the list?");
        }
        Task deleted = storage.remove(intCollection - 1);
        System.out.println("Noted. I've removed this task:\n" + deleted.toString()
                            + "\nNow you have " + storage.size() + " tasks in the list.");
    }

    public static void main(String[] args) {
        System.out.println("Hey Dude here\n" + "What can I do for you?");
        storage = FileManipulation.read("/Users/chinhongming/Documents/cs2103t/tasks.txt");
        Scanner sc = new Scanner(System.in);
        String userInput = sc.nextLine();
        while (!userInput.equals("bye")) {
            if (userInput.equals("mark") || userInput.startsWith("mark ")) {
                try {
                    mark(userInput);
                    FileManipulation.overwrite("/Users/chinhongming/Documents/cs2103t/tasks.txt", f.apply(storage));
                } catch (DukeException e) {
                    System.out.println(e.toString());
                }
            } else if (userInput.equals("unmark") || userInput.startsWith("unmark ")) {
                try {
                    unmark(userInput);
                    FileManipulation.overwrite("/Users/chinhongming/Documents/cs2103t/tasks.txt", f.apply(storage));
                } catch (DukeException e) {
                    System.out.println(e.toString());
                }
            } else if (userInput.equals("list")) {
                printList();
            } else if (userInput.equals("todo") || userInput.startsWith("todo ")) {
                try {
                    toDo(userInput);
                    FileManipulation.overwrite("/Users/chinhongming/Documents/cs2103t/tasks.txt", f.apply(storage));
                } catch (DukeException e) {
                    System.out.println(e.toString());
                }
            } else if (userInput.equals("deadline") || userInput.startsWith("deadline ")) {
                try {
                    deadline(userInput);
                    FileManipulation.overwrite("/Users/chinhongming/Documents/cs2103t/tasks.txt", f.apply(storage));
                } catch (DukeException e) {
                    System.out.println(e.toString());
                }
            } else if (userInput.equals("event") || userInput.startsWith("event ")) {
                try {
                    event(userInput);
                    FileManipulation.overwrite("/Users/chinhongming/Documents/cs2103t/tasks.txt", f.apply(storage));
                } catch (DukeException e) {
                    System.out.println(e.toString());
                }
            } else if (userInput.startsWith("delete ")) {
                try {
                    delete(userInput);
                    FileManipulation.overwrite("/Users/chinhongming/Documents/cs2103t/tasks.txt", f.apply(storage));
                } catch (DukeException e) {
                    System.out.println(e.toString());
                }
            }
            else {
                try {
                    incomprehensible();
                }
                catch (DukeException e){
                    System.out.println(e.toString());
                }
            }
            userInput = sc.nextLine();
            FileManipulation.overwrite("/Users/chinhongming/Documents/cs2103t/tasks.txt", f.apply(storage));
        }
        sc.close();
        System.out.println("Bye. Hope to see you again soon!");
    }
}
