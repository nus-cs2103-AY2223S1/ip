import java.util.*;
public class Duke {

    public static void enumerateList(ArrayList<Task> lst) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < lst.size(); i++) {
            int curr = i + 1;
            System.out.println(curr + ". " + lst.get(i).toString());
        }
    }

    public static void markDone(ArrayList<Task> lst, String message) throws DukeException{
        try {
            Scanner sc = new Scanner(message);
            int pos = sc.nextInt() - 1;
            lst.get(pos).mark();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(lst.get(pos).toString());
        } catch (ArrayIndexOutOfBoundsException a) {
            throw new DukeException("OOPS! Please enter a valid number!");
        } catch (Exception e) {
            throw new DukeException("OOPS! Please enter a number that you want to mark!");
        }
    }

    public static void markUndone(ArrayList<Task> lst, String message) throws DukeException {
        try {
            Scanner sc = new Scanner(message);
            int pos = sc.nextInt() - 1;
            lst.get(pos).unmark();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(lst.get(pos).toString());
        } catch (ArrayIndexOutOfBoundsException a) {
            throw new DukeException("OOPS! Please enter a valid number!");
        } catch (Exception e) {
            throw new DukeException("OOPS! Please enter a number that you want to unmark!");
        }
    }

    public static void toDo(ArrayList<Task> lst, String message) throws DukeException {
        Scanner sc = new Scanner(message);
        if (sc.hasNext()) {
            String description = sc.nextLine();
            ToDo todo = new ToDo(description);
            lst.add(todo);
            System.out.println("Got it. I've added this task:");
            System.out.println("\t" + todo.toString());
            System.out.println("Now you have " + lst.size() + " tasks in the list.");
        } else {
            throw new DukeException("OOPS! The description of the todo should not be empty!");
        }
    }

    public static void makeDeadline(ArrayList<Task> lst, String message) throws DukeException {
        Scanner sc = new Scanner(message);
        try {
            String description = "";
            while (!sc.hasNext("/by")) {
                String next = sc.next();
                description += next + " ";
            }
            sc.next();
            String date = sc.nextLine();
            Deadline deadline = new Deadline(description, date);
            lst.add(deadline);
            System.out.println("Got it. I've added this task: ");
            System.out.println("\t" + deadline.toString());
            System.out.println("Now you have " + lst.size() + " tasks in the list.");
        } catch (Exception e) {
            throw new DukeException("OOPS!! Please enter a valid message!");
        }
    }

    public static void makeEvent(ArrayList<Task> lst, String message) throws DukeException {
        Scanner sc = new Scanner(message);
        try {
            String description = "";
            while (!sc.hasNext("/at")) {
                String next = sc.next();
                description += next + " ";
            }
            sc.next();
            String date = sc.nextLine();
            Event event = new Event(description, date);
            lst.add(event);
            System.out.println("Got it. I've added this task: ");
            System.out.println("\t" + event.toString());
            System.out.println("Now you have " + lst.size() + " tasks in the list.");
        } catch (Exception e) {
            throw new DukeException("OOPS! Please enter a valid message!");
        }
    }

    public static void delete(ArrayList<Task> lst, String message) throws DukeException {
        try {
            Scanner sc = new Scanner(message);
            int pos = sc.nextInt() - 1;
            Task task = lst.remove(pos);
            System.out.println("Noted. I've removed this task:");
            System.out.println("\t" + task.toString());
            System.out.println("Now you have " + lst.size() + " tasks in the list");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please enter a valid number!");
        } catch (Exception e) {
            throw new DukeException("Please enter a number you want to delete!");
        }
    }

    public static void main(String[] args) throws DukeException {
        ArrayList<Task> lst = new ArrayList<>();
        boolean isDone = false;
        System.out.println("Hello! I'm Justin, your personal helper.");
        System.out.println("What can I do for you?");
        Scanner sc = new Scanner(System.in);
        while (!isDone) {
            String first = sc.next();
            String message = sc.nextLine();
            switch (first) {
                case ("bye"): {
                    System.out.println("Bye. Hope to see you again soon!");
                    isDone = true;
                    break;
                }
                case ("list"): {
                    enumerateList(lst);
                    break;
                }
                case ("mark"): {
                    markDone(lst, message);
                    break;
                }
                case ("unmark"): {
                    markUndone(lst, message);
                    break;
                }
                case ("todo"): {
                    toDo(lst, message);
                    break;
                }
                case ("deadline"): {
                    makeDeadline(lst, message);
                    break;
                }
                case ("event"): {
                    makeEvent(lst, message);
                    break;
                }
                case ("delete"): {
                    delete(lst, message);
                    break;
                }
                default: {
                    throw new DukeException("OOPS! I'm sorry, but I don't know what that means...");
                }
            }
        }
    }
}
