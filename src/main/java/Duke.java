import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        prettyPrint("Hello! I'm Duke\nWhat can I do for you?");
        while (in.hasNextLine()) {
            String command = in.nextLine();
            if (command.equalsIgnoreCase("bye")) {
                prettyPrint("Bye. Hope to see you again soon!");
                break;
            } else {
                prettyPrint(command);
            }
        }
    }

    private static void prettyPrint(String text) {
        String[] lines = text.split("\n");
        StringBuilder temp = new StringBuilder("    ____________________________________________________________\n");
        for (String line : lines) {
            temp.append("     ");
            temp.append(line);
            temp.append("\n");
        }
        temp.append("    ____________________________________________________________\n");
        System.out.println(temp.toString());
    }
}
