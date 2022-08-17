import java.util.Scanner;

public class Duke {
    private static Task[] items = new Task[100];
    private static int id;

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "\t|  _ \\ _   _| | _____ \n"
                + "\t| | | | | | | |/ / _ \\\n"
                + "\t| |_| | |_| |   <  __/\n"
                + "\t|____/ \\__,_|_|\\_\\___|\n";
        String introduction = "\tHello! I'm Duke\n" + "\tWhat can I do for you?";
        Duke.echo(logo + introduction);

        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String s = sc.nextLine();
            String[] arr = s.split(" ");


            if (s.equals("bye")) {
                Duke.echo("Bye. Hope to see you again soon!");
                break;
            } else if (s.equals("list")) {
                Duke.list();
            } else if (arr[0].equals("mark")) {
                int i;
                try {
                    i = Integer.parseInt(arr[1]) - 1;
                    if (i >= 0 && i < id) {
                        Task t = items[i];
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
                    if (i >= 0 && i < id) {
                        Task t = items[i];
                        t.markAsUndone();
                        Duke.echo("OK! I've marked this task as not done yet:\n" +
                                "\t  " + t);
                    }
                } catch (NumberFormatException e) {
                    Duke.echo("Please enter an integer id after \"ummark\"");

                }
            } else if (arr[0].equals("todo")) {
                String todo = s.substring(4).trim();
                Duke.add(todo, "todo", "");
            } else if (arr[0].equals("deadline")) {
                String[] deadlineBy = s.substring(8).trim().split("/by");
                String deadline = deadlineBy[0].trim();
                String by = deadlineBy[1].trim();
                Duke.add(deadline, "deadline", by);
            } else if (arr[0].equals("event")) {
                String[] eventAt = s.substring(5).trim().split("/at");
                String event = eventAt[0].trim();
                String at = eventAt[1].trim();
                Duke.add(event, "event", at);
            }  else {
                Duke.add(s, "task", "");
            }
        }
    }

    private static void echo(String s) {
        System.out.println("\t_________________________________________________");
        System.out.println("\t" + s);
        System.out.println("\t_________________________________________________");
    }

    private static void add(String description, String type, String remarks) {
        String s = "Got it. I've added this task:\n\t";
        switch (type) {
            case "todo":
                Todo t = new Todo(description);
                Duke.items[id] = t;
                s = s + "  " + t;
                break;
            case "deadline":
                Deadline d = new Deadline(description, remarks);
                Duke.items[id] = d;
                s = s + "  " + d;
                break;
            case "event":
                Event e = new Event(description, remarks);
                Duke.items[id] = e;
                s = s + "  " + e;
                break;
            default:
                break;
        }
        s = s + "\n\tNow you have " + (++id) + (id == 1 ? " task" : " tasks") + " in the list.";
        Duke.echo(s);
    }

    private static void list() {
        if (id == 0) {
            Duke.echo("no items stored");
        } else {
            String s = "Here are the tasks in your list:\n";
            for (int i = 0; i < id; i++) {
                Task t = items[i];
                s = s + "\t" + (i + 1) + "." + t + "\n";
            }
            Duke.echo(s.trim());
        }
    }
}
