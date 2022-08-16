import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    static final String CHATBOX_NAME = "Ado";
    static final String PARTITION = "<><><><><><><><><><><><><><><><>\n";
    static List<String> list = new ArrayList<>();

    public static void main(String[] args) {
        String input = "";
        Scanner sc = new Scanner(System.in);

        String startMessage = "Yo! I'm " + CHATBOX_NAME + "!\nWhat can I do for you? :)\n";
        printMessage(startMessage);

        while (!input.equalsIgnoreCase("bye")) {
            input = sc.nextLine();
            switch (input) {
                case "list":
                    printMessage(listToString());
                    break;
                case "bye":
                    printMessage("Gone so soon? :( Bye\n");
                    break;
                default:
                    list.add(input);
                    printMessage("added:" + input + "\n");
                    break;
            }
        }
    }

    static String listToString() {
        StringBuilder output = new StringBuilder();
        if (list.size() == 0) {
            return "List is empty!\n";
        }
        for (int i =0; i < list.size(); i++) {
            output.append(i + 1).append(". ").append(list.get(i)).append("\n");
        }
        return output.toString();
    }

     static void printMessage(String message) {
        String print = PARTITION + message + PARTITION;
        System.out.println(print);
    }

}
