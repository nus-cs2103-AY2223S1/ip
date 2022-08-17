import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String welcomeMsg = "Hi there! Baymax at your service.";
        System.out.println(welcomeMsg);
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while(!input.equals("bye")) {
            System.out.println(input);
            input = sc.nextLine();
        }
        System.out.println("Goodbye!");
    }
}
