import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        String input = "";
        ArrayList<String> list = new ArrayList<>();
        ArrayList<Boolean> listDone = new ArrayList<>();
        while (true) {
            input = scanner.nextLine();
            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                for (int i = 0; i < list.size(); i++) {
                    if (listDone.get(i)) {
                        System.out.println((i + 1) + ". [X] " + list.get(i));

                    } else {
                        System.out.println((i + 1) + ". [ ] " + list.get(i));
                    }
                }
            } else if (input.startsWith("mark")) {
                int target = Integer.valueOf(input.split(" ")[1]) - 1;
                listDone.set(target, true);
                System.out.println("Nice! I've marked this task as done:\n  [X] " + list.get(target));
            } else if (input.startsWith("unmark")) {
                int target = Integer.valueOf(input.split(" ")[1]) - 1;
                System.out.println("OK, I've marked this task as not done yet:\n  [ ] " + list.get(target));

                listDone.set(target, false);
            } else {
                list.add(input);
                listDone.add(false);
                System.out.println("added: " + input);
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
