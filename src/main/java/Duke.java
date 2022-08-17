import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String startLine = "---->".repeat(10);
        String endLine = "<----".repeat(10);
        String greeting = "Hello! Imma Duke!\n What can I do for you?";
        String farewell = "Bye. Duke misses you.";

        System.out.println(startLine);
        System.out.println(greeting);
        System.out.println(endLine);

        Scanner dukeSc = new Scanner(System.in);
        String input = dukeSc.nextLine();

        while(!input.equalsIgnoreCase("bye")) {
            System.out.println(startLine);
            System.out.println(input);
            System.out.println(endLine);

            input = dukeSc.nextLine();
        }

        System.out.println(startLine);
        System.out.println(farewell);
        System.out.println(endLine);
    }
}
