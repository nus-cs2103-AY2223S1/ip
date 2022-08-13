import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Stream;

public class Duke {
    private MessagePrinter mp = new MessagePrinter();
    private String HELLO_MESSAGE = "Hello! I'm Duke \n"
            + "What can I do for you?";
    private String FAREWELL_MESSAGE = "Bye. Hope to see you again soon!";
    private ArrayList<String> tasks = new ArrayList<>();


    public void greet() {
        mp.printMessage(HELLO_MESSAGE);
    }

    public void echo(String msg) {
        mp.printMessage(msg);
    }

    public void add(String msg) {
        tasks.add(msg);
        mp.printMessage("added: " + msg);
    }

    public void list() {
        mp.printMessage(Stream.iterate(0, x -> x + 1)
                        .limit(tasks.size())
                        .map(x -> String.valueOf(x + 1) + ". " + tasks.get(x))
                        .reduce("", (x, y) -> x + y + "\n" ));
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
            String command = scanner.nextLine();
            switch (command) {
                case "bye" :
                    isTerminated = true;
                    break;
                case "list" :
                    d.list();
                    break;
                default :
                    d.add(command);
            }
        }
        d.exit();
    }
}
