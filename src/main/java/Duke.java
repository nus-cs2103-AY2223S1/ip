import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static final String GREETING =
            "##################################################\n" +
            "||                                              ||\n" +
            "||                Hello! I'm Ted                ||\n" +
            "||            What can I do for you?            ||\n" +
            "||                                              ||\n" +
            "##################################################";

    private static final String INPUT_PREFIX = "> ";

    public static void main(String[] args) {
        System.out.println(GREETING);

        ArrayList<String> inputs = new ArrayList<String>();
        Scanner scanner = new Scanner(System.in);
        System.out.print(INPUT_PREFIX);

        while (scanner.hasNextLine()) {
            String command = scanner.nextLine();

            if (command.equals("bye")) {
                System.out.println("Bye. Hope to see you again!");
                break;
            } else if (command.equals("list")) {
                for (int inputIndex = 0;inputIndex < inputs.size();inputIndex++) {
                    System.out.printf("%d. %s\n", inputIndex + 1, inputs.get(inputIndex));
                }
            } else {
                inputs.add(command);
                System.out.printf("Added: %s\n", command);
            }

            System.out.print(INPUT_PREFIX);
        }

        scanner.close();
    }
}
