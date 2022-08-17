import java.util.*;
public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello! I'm Jamie.\nWhat can I do for you?\n");
        String[] lst = new String[100];
        int count = 0;

        while(true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println("Bye! Hope to see you again soon!");
                break;
            }
            else if (input.equals("list")) {
                for (int i = 0; i < count; i++) {
                    System.out.println((i+1) + ". " + lst[i]);
                }
            }
            else {
                lst[count] = input;
                System.out.println("added: " + input);
                count++;
            }
        }
    }
}
