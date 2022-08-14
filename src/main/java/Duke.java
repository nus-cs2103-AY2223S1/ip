import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String straightLine = "  ----------------------------------";
        Scanner sc = new Scanner(System.in);

        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm KiwiQE :) \nWhat can I do for you? \n");

        String input = sc.nextLine();

        while (!input.equalsIgnoreCase("Bye")) {
            System.out.print(straightLine + "\n  " + input + "\n" + straightLine + "\n\n");

            input = sc.nextLine();
        }

        System.out.println(straightLine + "\n  さよなら, goodbye\n" + straightLine);


    }
}
