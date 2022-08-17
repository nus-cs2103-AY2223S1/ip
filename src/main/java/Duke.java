import java.util.ArrayList;
import java.util.Scanner;
public class Duke {
    private final String topWindow = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n \t";
    private final String bottomWindow = "\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
    private final static String greeting = "Hi, I'm Bob! Nice to meet you!\n\tWhats up?";
    private final static String farewell = "Okay then, see ya later :)";
    private ArrayList<Task> tasks;
    public Duke() {
        messageFormatter(greeting);
        tasks = new ArrayList<>();
    }

    private void messageFormatter(String input) {
        System.out.println(topWindow + input + bottomWindow);
    }

    private void displayList() {
        int index = 1;
        for (Task item : tasks) {
            System.out.println("\n \t" + index + ". " + item.toString());
            index++;
        }
    }

    private void command() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        while(!input.equals("bye")) {
            if (input.equals("list")) {
                System.out.println(topWindow);
                displayList();
                System.out.println(bottomWindow);
            } else {
                tasks.add(new Task(input));
                messageFormatter("added: " + input);
            }
            input = scanner.nextLine();
        }
        messageFormatter(farewell);
    }
    
    public static void main(String[] args) {
        Duke newDuke = new Duke();
        newDuke.command();

    }
}
