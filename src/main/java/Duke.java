import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        // Initialise the chatbot
        Seaward seaward = new Seaward();
        System.out.println(seaward.getWelcome());

        // Need to find a way to accept input
        Scanner input = new Scanner(System.in);
        while (true) {
            String s = input.next();
            System.out.println(seaward.readInputString(s));
            if (s.equals("bye")) {
                break;
            }
        }
        input.close();
    }
}
