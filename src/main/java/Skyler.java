import java.util.Scanner;
import java.util.ArrayList;

public class Skyler {
    public static void main(String[] args) {
        ArrayList<String> tasks = new ArrayList<>();

        // declare scanner object and initialise with
        // predefined standard input object
        Scanner sc = new Scanner(System.in);

        System.out.println("Good day, mate! I'm Skyler\nHow can I help you?\n");

        while(sc.hasNext()) {
            String action = sc.nextLine();

            if (action.equals("bye")) {
                System.out.println("Bye! See you again soon!");
                break;
            } else if (action.equals("list")) {
                for (int i = 0; i < tasks.size(); i++) {
                    String str = String.format("%d. %s", i + 1, tasks.get(i));
                    System.out.println(str);
                }
            } else {
                tasks.add(action);
                String str = String.format("added: %s", action);
                System.out.println(str);
            }
        }

        sc.close();
    }
}
