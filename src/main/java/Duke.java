import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Duke {

    // String array used to store tasks
    private static final List<String> tasks = new ArrayList<>();

    // Currently, the main function takes in user input and echoes it to the user
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello, I am Duke. \nWhat can I do for you? :)\n=======================");
        Scanner sc = new Scanner(System.in);
        while (true) {
            String input = sc.nextLine();
            if (input.toLowerCase().equals("bye")) {
                System.out.println("Bye! Hope to see you again soon!");
                break;
            } else if (input.toLowerCase().equals("list")) {
                int counter = 1;
                System.out.println("=======================");
                System.out.println("Here are the tasks that you have added to the list: ");
                for (String s: tasks) {
                    if (s != null) {
                        System.out.println(counter + ". " + s);
                        counter++;
                    }
                }
                System.out.println("=======================");
            } else {
                tasks.add(input);
                System.out.println("=======================");
                System.out.println("added: " + input);
                System.out.println("=======================");
            }


        }

        return;

    }
}
