import java.util.Scanner;
public class Duke {
    public static void makeLine() {
        for (int i = 0; i < 50; i++) {
            System.out.print("\u2015");
        }
        System.out.println();
    }

    private static void echo(String command) {
        System.out.println(command);
    }

    private static void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        makeLine();
        System.out.println("Hello from\n" + logo);
        makeLine();

        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();
        while (!command.equals("bye")) {
            makeLine();
            echo(command);
            makeLine();
            command = sc.nextLine();
        }
        bye();
    }
}
