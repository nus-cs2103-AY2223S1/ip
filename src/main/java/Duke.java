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

        Scanner scanner = new Scanner(System.in);
        System.out.print(INPUT_PREFIX);

        while (scanner.hasNextLine()) {
            String command = scanner.nextLine();

            if (command.equals("bye")) {
                System.out.println("Bye. Hope to see you again!");
                break;
            } else {
                System.out.println(command);
            }

            System.out.print(INPUT_PREFIX);
        }

        scanner.close();
    }
}
