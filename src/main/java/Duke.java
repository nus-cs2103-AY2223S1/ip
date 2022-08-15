import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] items = new String[100];
        String name = "Duke";
        int pointer = 0;
        System.out.println("Hello! I'm " + name + "\nWhat can I do for you?");
        while (true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (input.equals("list")) {
                for (int i = 1; i <= pointer; i++) {
                    System.out.println(i + ". " + items[i - 1]);
                }
            } else {
                items[pointer++] = input;
                System.out.println("added: " + input);
            }
        }
    }
}
