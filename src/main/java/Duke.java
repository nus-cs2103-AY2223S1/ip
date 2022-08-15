import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<String> list = new ArrayList<>();

        System.out.println("Hello! I'm Piggy");
        System.out.println("What can I oink for you?");

        String input;
        while (true) {
            input = sc.nextLine();
            if (input.equals("list")) {
                for (int i = 0; i < list.size(); i++) {
                    System.out.printf("%d. %s\n", i + 1, list.get(i));
                }
            } else if (input.equals("bye")) {
                break;
            } else {
                list.add(input);
                System.out.println("added: " + input);
            }
        }

        System.out.println("Bye. Hope to oink you again soon!");
        sc.close();
    }
}
