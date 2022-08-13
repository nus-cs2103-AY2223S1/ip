import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final ArrayList<String> data = new ArrayList<>();

    public static String reply(String input) {
        if (input.equals("list")) {
            StringBuilder output = new StringBuilder();
            for (int i = 0; i < data.size(); i++) {
                output.append(i).append(". ").append(data.get(i)).append("\n");
            }
            return output.substring(0, output.length()-1);
        }
        data.add(input);
        return "added: " + input;
    }

    public static void main(String[] args) {
        System.out.println("Quack! I'm Duck\nWhat can I do for you?");
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            System.out.println("____________________________________________________________");
            System.out.println("Duck:");
            String input = sc.next();
            if (input.equals("bye")) {
                System.out.println("Quack! Hope to see you again soon!");
                return;
            } else System.out.println(reply(input));
            System.out.println("____________________________________________________________");
        }
    }
}
