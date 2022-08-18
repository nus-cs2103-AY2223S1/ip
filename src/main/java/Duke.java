import java.util.Scanner;

public class Duke {

    public static void echo(String msg) {
        String lineBlock = "     -----------------------------------------";
        System.out.println(lineBlock);
        System.out.println("     " + msg);
        System.out.println(lineBlock);
    }

    public static void main(String[] args) {
        String logo = "____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n\n";
        String welcomeMessage = "> What can I do for you today? : )\n";
        String toPrint = String.format("> Hello from\n %s%s", logo, welcomeMessage);
        System.out.println(toPrint);
        //System.out.println("Hello from\n" + logo + "\n" + welcomeMessage);

        while (true) {
            Scanner sc = new Scanner(System.in);

            if (sc.hasNext()) {
                String nextString = sc.next();

                if (nextString.equals("bye")) {
                    echo("bye");
                    break;
                }

                else {
                    echo(nextString);
                }
            }
        }
    }
}
