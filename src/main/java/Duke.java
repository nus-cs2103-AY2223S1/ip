import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    private final static String INTRODUCTION_MSG = "Hello I'm Duke\nWhat can I do for you?";
    private final static String CLOSING_MSG = "Bye. Hope to see you again soon!";
    private static ArrayList<String> listOfTasks = new ArrayList<>();
    public static void main(String[] args) {
        System.out.println(INTRODUCTION_MSG);
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                break;
            }
            else if (input.equals("list")) {
                DisplayList();
            } else {
                AddToList(input);
            }
        }
        System.out.println(CLOSING_MSG);
        sc.close();
    }

    private static void DisplayList() {
        for (int i = 0; i < listOfTasks.size(); i++) {
            System.out.println((i + 1) + ". " + listOfTasks.get(i));
        }
    }

    private static void AddToList(String input) {
        listOfTasks.add(input);
        System.out.println("added: " + input);
    }
}
