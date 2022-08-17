import java.util.LinkedList;
import java.util.Scanner;

public class Duke extends Chatbot {
    private static final String GREETING = "Hi friend! How may I help you?";
    private static final String FAREWELL = "See you soon, friend!";
    private static final String TRIGGER_FAREWELL = "bye";
    private static final String TRIGGER_LIST = "list";
    private LinkedList<String> keywords = new LinkedList<>();
    private LinkedList<Task> tasks = new LinkedList<>();

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.sayHello();

        // initialize keywords
        duke.keywords.add(Duke.TRIGGER_FAREWELL);
        duke.keywords.add(Duke.TRIGGER_LIST);

        while(true) {
            String action = duke.listen();

            if (duke.keywords.contains(action)) {
                switch (action) {
                    case Duke.TRIGGER_FAREWELL:
                        duke.sayGoodbye();
                        break;
                    case Duke.TRIGGER_LIST:
                        duke.listTasks(duke);
                        break;
                }

                if (action.equals(Duke.TRIGGER_FAREWELL)) {
                    break;
                }
            } else {
                duke.tasks.add(new Task(action));
                duke.echo(String.format("added: %s", action));
            }
        }
    }

    public void listTasks(Duke chatSession) {
        chatSession.tasks.forEach(
                task -> {
                    System.out.println(String.format("%d. %s", task.getId(), task.getDescription()));
                }
        );
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
