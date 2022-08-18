import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final String EXITCOMMAND = "bye";
    private static final String LINE = "\n========================================================";
    private static final String WELCOMEMESSAGE = "Hello, my name is Duke!\nHow can I help you today?";
    private static final String EXITMESSAGE = LINE + "\nGoodbye! Looking forward to see you again soon!\n(●'◡'●)ノ"
            + LINE;

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo + LINE + "\n" + WELCOMEMESSAGE + LINE);

        // ArrayList to store user inputs
        List<String> listOfInputs = new ArrayList<>();

        // Echo user input
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.equalsIgnoreCase("bye")) { // Stops loop if command is 'bye'
            // Check if user input is 'list'
            if (input.equalsIgnoreCase("list")) { // List all inputs typed so far
                if (listOfInputs.size() == 0) { // List is empty
                    System.out.println(LINE + "\nYour list is empty! Why not add something to it first?" + LINE);
                    input = sc.nextLine();
                    continue;
                } else {
                    System.out.println(LINE);
                    for (int i = 0; i < listOfInputs.size(); i++) {
                        System.out.println((i + 1) + ". " + listOfInputs.get(i));
                    }
                    System.out.println(LINE);
                }
                input = sc.nextLine();
                continue;
            }

            listOfInputs.add(input);
            System.out.println(LINE + "\nYou have added: " + input + LINE);
            input = sc.nextLine();
        }
        System.out.println(EXITMESSAGE);
    }
}
