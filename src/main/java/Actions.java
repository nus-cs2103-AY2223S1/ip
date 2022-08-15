import java.util.Scanner;

public class Actions {
    public static void greetEcho() {
        String input = "";
        System.out.println("Hello! I'm Duke, what's up today?");
        while (input != "bye") {
            Scanner sc = new Scanner(System.in);
            input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println("See ya!");
                return;
            }
            else {
                System.out.println(input);
            }
        }
    }
}
