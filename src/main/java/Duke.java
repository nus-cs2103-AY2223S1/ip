import java.util.Scanner;

public class Duke {
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
            if (s.equals("bye")) {
                Duke.echo("Bye. Hope to see you again soon!");
                break;
            } else {
                Duke.echo(s);
            }
        }
    }

    private static void echo(String s) {
        System.out.println("\t_________________________________________________");
        System.out.println("\t" + s);
        System.out.println("\t_________________________________________________");
    }
}
