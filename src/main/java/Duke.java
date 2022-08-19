import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Duke {
    public static void main(String[] args) {

        List<String> list = new ArrayList<>(); // to store list of inputs

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String separator = "     ==================================";
        String indent = "      ";

        String welcomeMsg = "Hello! I'm\n" + logo + "\nWhat can I do for you?\n";
        System.out.println(welcomeMsg);

        Scanner sc = new Scanner(System.in);
        String input = "";

        while (true) {
            input = sc.nextLine();

            if (input.equals("bye")) {
                System.out.println(separator);
                System.out.println(indent + "Bye. Hope to see you again soon!");
                System.out.println(separator);
                break;
            } else if (input.equals("list")) {
                System.out.println(separator);
                listPrinter(list);
                System.out.println(separator);
            } else {
                list.add(input);
                System.out.println(separator);
                System.out.println(indent + "added: " + input);
                System.out.println(separator);
            }
        }
    }

    static void listPrinter(List<String> list) {
        String out = "";
        int num = 1;
        for (String x : list) {
            out += "      ";
            out += Integer.toString(num) + ". " + x + "\n";
            num++;
        }
        System.out.println(out);
    }

}
