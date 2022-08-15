import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    static ArrayList<String> listOfThings = new ArrayList<String>();

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        // Special Commands
        String BYE = "bye";
        String LIST = "list";

        Scanner sc = new Scanner(System.in);

        System.out.println("Hello! I'm Duke\nWhat can I do for you?");

        while(true) {
            String userInput = sc.nextLine();
            if (userInput.equals(BYE)) {
                break;
            } else if (userInput.equals(LIST)) {
                ListOut();
            } else {
                AddToList(userInput);
            }
        }

        Bye();
    }

    public static void AddToList(String str) {
        listOfThings.add(str);
        System.out.println("--------------------------------");
        System.out.println("added: " + str);
        System.out.println("--------------------------------");
    }

    public static void ListOut() {
        int size = listOfThings.size();
        System.out.println("--------------------------------");
        for (int i = 0; i < size; i++) {
            System.out.println((i + 1) + ". " + listOfThings.get(i));
        }
        System.out.println("--------------------------------");
    }

    public static void Bye() {
        System.out.println("--------------------------------");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("--------------------------------");
    }
}
