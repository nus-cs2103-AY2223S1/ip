import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        run();
    }

    /**
     * main interface function
     */
    public static void run() {
        Scanner in = new Scanner(System.in);
        boolean lastCommandOrNot = false;
        String command;

        Interface.greet();
        while (!lastCommandOrNot) {
            command = in.nextLine();
            switch (command) {
                case "bye":
                    Interface.bye();
                    lastCommandOrNot = true;
                    break;
                default:
                    Interface.echo(command);
            }
        }
    }

}
