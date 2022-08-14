import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Scanner myScanner = new Scanner(System.in);

        System.out.println("Hello! I am Duke \n What do you want me to do? \n");

        String input;

        while (true) {
            input = myScanner.nextLine();
            if (input.equals("bye")) {
                System.out.println("See you later :)");
                System.exit(0);
            }
            System.out.println(input);
        }

    }
}
