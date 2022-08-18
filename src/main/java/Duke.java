import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Duke {


    public static void main(String[] args) throws DukeException {
        System.out.println("               __\n" +
                "              / _)\n" +
                "     _.----._/ /\n" +
                "    /         /\n" +
                " __/ (  | (  |\n" +
                "/__.-'|_|--|_|\n");

        String line = "Dino:\n";
        ArrayList<Task> ls = new ArrayList<>(100);

        System.out.println(line
                + "\tHello! I'm Dino\n"
                + "\tWhat can I do for you?\n");

        Scanner sc = new Scanner(System.in);
        String curr = "";
        int index;
        Task myTask;

        while (!Objects.equals(curr, "bye")) {
            curr = sc.nextLine();
            String[] str = curr.split("\\s", 2);
            System.out.print(line);
            switch(str[0]) {
                case "bye":
                    System.out.println("\tBye bye. Hope to see you again soon!");
                    break;
                case "list":
                    for (int i = 0; i < Task.lsSize(); i++) {
                        System.out.println("\t" + (i + 1) + ". " + ls.get(i).toString());
                    }
                    break;
                case "mark":
                    try {
                        index = Integer.parseInt(str[1]) - 1;
                    } catch (Exception e) {
                        throw new DukeException("Oops! You did not choose which task to mark!");
                    }

                    if (index > Task.lsSize() || index < 0) {
                        throw new DukeException("Oops! Invalid task!");
                    }
                    myTask = ls.get(index);
                    myTask.markAsDone();
                    System.out.println("\tHooray! You have completed this task:\n\t" + myTask);
                    break;
                case "unmark":
                    try {
                        index = Integer.parseInt(str[1]) - 1;
                    } catch (Exception e) {
                        throw new DukeException("Oops! You did not choose which task to mark!");
                    }

                    if (index > Task.lsSize() || index < 0) {
                        throw new DukeException("Oops! Invalid task!");
                    }
                    myTask = ls.get(index);
                    myTask.markAsUndone();
                    System.out.println("\tOh no! You have more things to complete:\n\t" + myTask);
                    break;
                case "deadline":
                    String[] dl;
                    try {
                        dl = str[1].split(" /by ");
                    } catch (Exception e) {
                        throw new DukeException("Oops! The description of a deadline cannot be empty!");
                    }

                    try {
                        myTask = new Deadline(dl[0], dl[1]);
                    } catch (Exception e) {
                        throw new DukeException("Oops! When is the deadline?");
                    }

                    ls.add(myTask);
                    System.out.println("\tadded: " + myTask);
                    System.out.println("\tYou have " + Task.lsSize() + " task" + (Task.lsSize() > 1 ? "s!" : "!"));
                    break;
                case "todo":
                    try {
                        myTask = new ToDo(str[1]);
                    } catch (Exception e) {
                        throw new DukeException("Oops! The description of a todo cannot be empty!");
                    }

                    ls.add(myTask);
                    System.out.println("\tadded: " + myTask);
                    System.out.println("\tYou have " + Task.lsSize() + " task" + (Task.lsSize() > 1 ? "s!" : "!"));
                    break;
                case "event":
                    String[] evnt;
                    try {
                        evnt = str[1].split(" /at ");
                    } catch (Exception e) {
                        throw new DukeException("Oops! The description of an event cannot be empty!");
                    }

                    try {
                        myTask = new Event(evnt[0], evnt[1]);
                    } catch (Exception e) {
                        throw new DukeException("Oops! When is the event?");
                    }

                    ls.add(myTask);
                    System.out.println("\tadded: " + myTask);
                    System.out.println("\tYou have " + Task.lsSize() + " task" + (Task.lsSize() > 1 ? "s!" : "!"));
                    break;
                case "delete":
                    try {
                        index = Integer.parseInt(str[1]) - 1;
                    } catch (Exception e) {
                        throw new DukeException("Oops! You did not choose which task to delete!");
                    }

                    if (index > Task.lsSize() || index < 0) {
                        throw new DukeException("Oops! Invalid task!");
                    }

                    ls.get(index).remove();
                    ls.remove(index);
                    break;
                default:
                    throw new DukeException("Oops! I don't know what that means.");
            }
            System.out.print("\n");
        }
    }
}
