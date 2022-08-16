import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {

        System.out.println("Hello! I'm Duke\nWhat can I do for you?");

        Scanner sc = new Scanner(System.in);

        String input = "";
        int index = 0;
        String[] items = new String[100];
        while(!input.equals("bye")) {
            input = sc.nextLine();
            if (!input.equals("list") && !input.equals("bye")) {
                items[index++] = input;
                System.out.println("added: "+ input);
            }
            else if (input.equals("list")) {
                for (int i = 0; i < items.length; i++) {
                    if ((items[i]) == null) {
                        break;
                    }
                    System.out.println(i+1 + ". " + items[i]);
                }
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
