import java.util.Objects;
import java.util.Scanner;

public class Botson {
    static String[] list = new String[100];
    public static void main(String[] args) {
        welcome();
    }

    /**
     * Echos the input and stores it in the list
     * Exit when user types "bye"
     **/
    public static void welcome() {
        list = new String[100];
        int idx = 0;
        System.out.println("Hello! I'm Botson");
        Scanner input = new Scanner(System.in);
        System.out.println("What can I help you with?");
        System.out.println("--------------------------");
        while (true) {
            String action = input.nextLine();  // Read user input
            if (Objects.equals(action, "bye")) {
                System.out.println("Goodbye! Hope to see you again soon!");
                System.out.println("--------------------------");
                break;
            } else if (Objects.equals(action, "list")) {
                getList(idx);
                continue;
            }
            list[idx] = action;
            idx++;
            System.out.println("Added: " + action);
            System.out.println("--------------------------");
        }
    }

    private static void getList(int idx) {
        for (int i = 0; i < idx; i++) {
            System.out.println((i + 1) + ". " + list[i]);
        }
        System.out.println("--------------------------");
    }
}
