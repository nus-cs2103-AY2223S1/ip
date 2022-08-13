import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Stream;

public class Duke {
    private MessagePrinter mp = new MessagePrinter();
    private String HELLO_MESSAGE = "Hello! I'm Duke \n"
            + "What can I do for you?";
    private String FAREWELL_MESSAGE = "Bye. Hope to see you again soon!";


    public void greet() {
        mp.printMessage(HELLO_MESSAGE);
    }

    public void echo(String msg) {
        mp.printMessage(msg);
    }

    public void exit() {
        mp.printMessage(FAREWELL_MESSAGE);
    }

    public static void main(String[] args) {
        Duke d = new Duke();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        d.echo("Hello from\n" + logo);
        d.greet();

        Scanner scanner = new Scanner(System.in);
        boolean isTerminated = false;
        while (!isTerminated) {
            String command = scanner.next();
            switch (command) {
                case "bye" :
                    isTerminated = true;
                    break;
                default :
                    d.echo(command);
            }
        }
        d.exit();
    }
}
