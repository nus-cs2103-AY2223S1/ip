import java.util.ArrayList;
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
        ArrayList<String> listOfThings = new ArrayList<>(100);

        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm KiwiQE :) \nWhat can I do for you? \n");

        String input = sc.nextLine();

        while (!input.equalsIgnoreCase("Bye")) {

            if (input.equalsIgnoreCase("list")) {
                if (listOfThings.isEmpty()) {
                    System.out.println(straightLine + "\n" + "  Nothing to do currently ehe\n" + straightLine);
                    input = sc.nextLine();
                    continue;
                }
                // print out in order placed
                int index = 1;

                System.out.println(straightLine);

                for (String task : listOfThings) {
                    System.out.println("  " + index + ". " + task);
                    index++;
                }

                System.out.println(straightLine);

                input = sc.nextLine();
                continue;
            }

            listOfThings.add(input);

            System.out.print(straightLine + "\n  added: " + input + "\n" + straightLine + "\n\n");

            input = sc.nextLine();
        }

        System.out.println(straightLine + "\n  さよなら, goodbye\n" + straightLine);


    }
}
