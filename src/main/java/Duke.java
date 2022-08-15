import java.util.Scanner;

public class Duke {
    private static String[] tasks = new String[100];
    private static int count = 0;

    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        while (true) {
            String input = reader.next();
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                return;
            } else if (input.equals("list")) {
                for(int i = 0; i < count; i++) {
                    System.out.println(i + 1 + ". " + tasks[i]);
                }
            } else {
                tasks[count] = input;
                count++;
                System.out.println("added: " + input);
            }
        }
    }
}
