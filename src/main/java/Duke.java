import java.lang.reflect.Array;
import java.util.Scanner;
import java.util.ArrayList;

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

        ArrayList<String> list = new ArrayList<String>();
        Scanner dukeSc = new Scanner(System.in);

        while (true) {
            String input = dukeSc.nextLine();

            System.out.println(startLine);
            switch (input) {
                case "bye":
                    System.out.println(farewell);
                    System.out.println(endLine);
                    return;
                case "list":
                    for (int i = 0; i < list.size(); i++) {
                        System.out.println(i+1 + ". " + list.get(i));
                    }
                    System.out.println(endLine);
                    break;
                default:
                    list.add(input);
                    System.out.println("added: " + input);
                    System.out.println(endLine);
            }
        }
    }
}
