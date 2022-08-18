import java.util.Scanner;

public class Echo {

    public Echo() {
    }
    public void echoInput(String input) {
        Scanner userInput = new Scanner(System.in);
        while (!input.equals("bye")) {
            System.out.println(input);
            input = userInput.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon");
    }
}
