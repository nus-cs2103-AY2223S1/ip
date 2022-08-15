import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Hello! I'm Piggy");
        System.out.println("What can I oink for you?");

        String input = sc.nextLine();
        while (!input.equals("bye")) {
            System.out.println(input);
            input = sc.nextLine();
        }

        System.out.println("Bye. Hope to oink you again soon!");
        sc.close();
    }
}
