import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    private void run() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello! I am a Store And Response Bot, otherwise known as Starboard\n" +
                "What can I do for you today?");

        ArrayList<String> store = new ArrayList<>();

        while (true) {

            String next = sc.nextLine();

            if (next.equals("bye")) {
                System.out.println("Bye bye! Hope to see you soon!");
                break;
            } else if (next.equals("list")) {
                for (int i = 0; i < store.size(); i++) {
                    System.out.println(i + 1 + ". " + store.get(i));
                }
            } else {
                store.add(next);
                System.out.println("Added: " + next);
            }
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
