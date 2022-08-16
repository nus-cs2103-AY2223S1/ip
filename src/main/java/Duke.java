import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Fungusta\n" + "Peter's personal chatbot\n");
        Scanner sc = new Scanner((System.in));
        String userInput = sc.next();
        while (!userInput.equals("bye")) {
            System.out.println(userInput + "\n");
            userInput = sc.next();
        }
        System.out.println("Goodbye!");
    }

}
