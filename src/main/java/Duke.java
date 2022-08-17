import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! What are we gonna do today?");
        while (true) {
            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println("\tBye! Hope that I helped!");
                break;
            }
            System.out.println("\t" + input);
        }
    }
}
