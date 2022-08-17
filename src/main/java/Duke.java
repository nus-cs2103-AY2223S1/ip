import java.util.Scanner;

public class Duke {
    private static String[] store = new String[100];
    private static int count = 0;
    public static void main(String[] args) {

        System.out.println("Hello! What are we gonna do today?");
        while (true) {
            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println("\tBye! Hope that I helped!");
                break;
            } else if (input.equals("list")) {
                int number = 1;
                for (String task : store) {
                    if (task == null) continue;
                    System.out.println("\t" + number + ". " + task);
                    number++;
                }
                continue;
            }
            store[++count] = input;
            System.out.println("\tadded: " + input);
        }
    }
}
