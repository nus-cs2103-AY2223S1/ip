import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static final String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private static final String GREETINGS = "Hello! I'm Duke\nWhat can I do for you?\n";

    private static void echo(String str) {
        System.out.println("added: " + str);
    }

    private static void printArrAsNumberedList(ArrayList<String> arr) {
        for (int i = 0; i < arr.size(); i++) {
            System.out.println(i+1 + ". " + arr.get(i));
        }
    }

    public static void main(String[] args) {
        System.out.println("Hello from\n" + logo);
        System.out.println(GREETINGS);

        ArrayList<String> strArr = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while (!input.equals("bye")) {
            if (input.equals("list")) {
                printArrAsNumberedList(strArr);
            } else {
                strArr.add(input);
                echo(input);
            }
            input = sc.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!\n");




    }

}
