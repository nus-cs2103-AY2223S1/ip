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
                                "\t  " + t.getStatusIcon()
                                + t.getDescription());
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
                                "\t  " + t.getStatusIcon()
                                + t.getDescription());
                    }
                } catch (NumberFormatException e) {
                    Duke.echo("Please enter an integer id after \"ummark\"");

                }
            } else {
                Duke.add(s);
            }
        }
    }

    private static void echo(String s) {
        System.out.println("\t_________________________________________________");
        System.out.println("\t" + s);
        System.out.println("\t_________________________________________________");
    }

    private static void add(String item) {
        Duke.items[id] = new Task(item);
        Duke.id++;
        Duke.echo("added: " + item);
    }

    private static void list() {
        if (id == 0) {
            Duke.echo("no items stored");
        } else {
            String s = "";
            for (int i = 0; i < id; i++) {
                Task t = items[i];
                s = s + "\t" + (i + 1) + ". " + t.getStatusIcon() + t.getDescription() + "\n";
            }
            Duke.echo(s.trim());
        }
    }
}
