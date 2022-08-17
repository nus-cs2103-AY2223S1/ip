import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I've upgraded to become a listing bot now!\nWhat can I do for you, my highness?");
        Scanner in = new Scanner(System.in);

        String[] tasks = new String[100];
        int count = 0;

        while (true) {
            System.out.println("Your highness:");
            String input = in.nextLine();
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (input.equals("list")) {
                for (int i=1; i<=count; i++) {
                    System.out.println(i + ". " + tasks[i-1]);
                }
            } else {
                tasks[count] = input;
                count++;
                System.out.println("added: " + input);
            }
        }
    }
}
