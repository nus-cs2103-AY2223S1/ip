import java.util.Scanner;

public class Duke extends Chatbot {
    private static final String GREETING = "Hi friend! How may I help you?";
    private static final String FAREWELL = "See you soon, friend!";
    private static final String TRIGGER = "bye";

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.sayHello();

        while(true) {
            String action = duke.listen();

            if (action.equals(Duke.TRIGGER)) {
                duke.sayGoodbye();
                break;
            } else {
                duke.echo(action);
            }
        }
    }

    @Override
    public void echo(String message) {
        System.out.println(message);
    }

    @Override
    public void sayHello() {
        System.out.println(Duke.GREETING);
    }

    @Override
    public void sayGoodbye() {
        System.out.println(Duke.FAREWELL);
    }

    @Override
    public String listen() {
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        return userInput;
    }
}
