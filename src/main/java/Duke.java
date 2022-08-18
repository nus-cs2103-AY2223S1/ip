import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static List<Task> items = new ArrayList<>();
    // private static int id;

    public static void main(String[] args) throws DukeException {
        String introduction = "Hello! I'm Duke\n" + "\tWhat can I do for you?";
        Duke.echo(introduction);

        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String s = sc.nextLine();
            String[] arr = s.split(" ");
            if (s.equals("bye")) {
                Duke.echo("Bye. Hope to see you again soon!");
                break;
            }
            try {
                Duke.decide(s, arr);
            } catch (DukeException e) {
                Duke.echo(e.toString());
            }
        }
    }

    private static void decide(String s, String[] arr) throws DukeException {
        if (s.equals("list")) {
            Duke.list();
        } else if (arr[0].equals("mark")) {
            int i;
            try {
                i = Integer.parseInt(arr[1]) - 1;
                if (i >= 0 && i < items.size()) {
                    Task t = items.get(i);
                    t.markAsDone();
                    Duke.echo("Nice! I've marked this task as done:\n" +
                            "\t  " + t);
                }
            } catch (NumberFormatException e) {
                Duke.echo("Please enter an integer id after \"mark\"");
            }
        } else if (arr[0].equals("unmark")) {
            int i;
            try {
                i = Integer.parseInt(arr[1]) - 1;
                if (i >= 0 && i < items.size()) {
                    Task t = items.get(i);
                    t.markAsUndone();
                    Duke.echo("OK! I've marked this task as not done yet:\n" +
                            "\t  " + t);
                }
            } catch (NumberFormatException e) {
                Duke.echo("Please enter an integer id after \"ummark\"");

            }
        } else if (arr[0].equals("todo")) {
            if (arr.length == 1) {
                throw new DukeException("Error. The description of a todo cannot be empty.");
            }
            String todo = s.substring(4).trim();
            Duke.add(todo, "todo", "");
        } else if (arr[0].equals("deadline")) {
            String[] deadlineBy = s.substring(8).trim().split("/by");
            if (deadlineBy.length <= 1) {
                throw new DukeException("Error. The description and due date of a deadline\n\tshould be separated" +
                        " by a \"/by\".");
            }
            String deadline = deadlineBy[0].trim();
            String by = deadlineBy[1].trim();
            Duke.add(deadline, "deadline", by);
        } else if (arr[0].equals("event")) {
            String[] eventAt = s.substring(5).trim().split("/at");
            if (eventAt.length <= 1) {
                throw new DukeException("Error. The description and time of an event\n\tshould be separated" +
                        " by a \"/at\".");
            }
            String event = eventAt[0].trim();
            String at = eventAt[1].trim();
            Duke.add(event, "event", at);
        }  else {
            throw new DukeException("Error. Sorry, but I don't know what that means.");
        }
    }

    private static void echo(String s) {
        System.out.println("\t_________________________________________________");
        System.out.println("\t" + s);
        System.out.println("\t_________________________________________________");
        System.out.println();
    }

    private static void add(String description, String type, String remarks) {
        String s = "Got it. I've added this task:\n\t";
        switch (type) {
            case "todo":
                Todo t = new Todo(description);
                Duke.items.add(t);
                s = s + "  " + t;
                break;
            case "deadline":
                Deadline d = new Deadline(description, remarks);
                Duke.items.add(d);
                s = s + "  " + d;
                break;
            case "event":
                Event e = new Event(description, remarks);
                Duke.items.add(e);
                s = s + "  " + e;
                break;
            default:
                break;
        }
        int size = items.size();
        s = s + "\n\tNow you have " + (size) + (size == 1 ? " task" : " tasks") + " in the list.";
        Duke.echo(s);
    }

    private static void list() {
        if (items.size() == 0) {
            Duke.echo("no items stored");
        } else {
            String s = "Here are the tasks in your list:\n";
            for (int i = 0; i < items.size(); i++) {
                Task t = items.get(i);
                s = s + "\t" + (i + 1) + "." + t + "\n";
            }
            Duke.echo(s.trim());
        }
    }
}
