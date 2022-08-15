import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Duke {

    private final static ArrayList<String> list = new ArrayList<>();
    public static void main(String[] args) {
        customPrint("Hello! I'm Duke\nWhat can I do for you?");
        Scanner sc = new Scanner(System.in);
        String userInput;
        while (!(userInput = sc.nextLine()).equals("bye")) {
            handleCommand(userInput);
        }
        customPrint("Bye. Hope to see you again soon!");
    }

    private static void customPrint(String s) {
        System.out.println("-------------------");
        System.out.println(s);
        System.out.println("-------------------");
    }

    private static void handleCommand(String s) {
        String[] args = s.split(" ");
        if (args.length == 0) {
            return;
        }
        switch (args[0]) {
            case "list":
                StringBuilder stringBuilder = new StringBuilder();
                int n = list.size();
                for (int i = 0; i < n; i++) {
                    stringBuilder.append(String.format("%d. %s", i + 1, list.get(i)));
                    if (i != n - 1) {
                        stringBuilder.append("\n");
                    }
                }
                customPrint(stringBuilder.toString());
                break;
            default:
                addToList(s);
                customPrint("added: " + s);
        }
    }

    private static void addToList(String s) {
        list.add(s);
    }
}
