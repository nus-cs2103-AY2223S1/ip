import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        List<String> tasks = new ArrayList<>();
        prettyPrint("Hello! I'm Duke\nWhat can I do for you?");
        while (in.hasNextLine()) {
            String command = in.nextLine();
            if (command.equalsIgnoreCase("bye")) {
                prettyPrint("Bye. Hope to see you again soon!");
                break;
            } else if (command.equalsIgnoreCase("list")) {
                StringBuilder temp = new StringBuilder();
                for (int i = 0; i < tasks.size(); i++) {
                    temp.append(i+1);
                    temp.append(". ");
                    temp.append(tasks.get(i));
                    temp.append("\n");
                }
                prettyPrint(temp.toString());
            } else {
                tasks.add(command);
                prettyPrint("added: " + command);
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
        System.out.println(temp);
    }
}
