import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        System.out.println("Hello I'm Duke\n" + "What can I do for you?");
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (input.equals("list")) {
                int length = list.size();
                for (int i = 0; i < length; i++) {
                    System.out.println((i + 1) + ". " + list.get(i));
                }
            } else {
                list.add(input);
                System.out.println("added: " + input);
            }
        }
    }

}
