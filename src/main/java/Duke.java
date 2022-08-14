import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static String receiveCommand() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        return input;
    }

    private static void bye() {
        System.out.println("\tGoodbye! See you soon!\n" +
                           "\tAu revoir! À tout à l'heure!");
    }

    private static void add(Task newTask, ArrayList<Task> userInputs) {
        userInputs.add(newTask);
        System.out.println("\tadded: " + newTask.description + "\n" +
                           "\tajouté: " + newTask.description + "\n");
    }

    private static void list(ArrayList<Task> userInputs) {
        for (int i = 0; i < userInputs.size(); i++) {
            System.out.println((i+1) + ".\t " + userInputs.get(i).toString());
        }
    }

    public static void main(String[] args) {
        System.out.println("Hello! I'm Jean\n" +
                           "How can I help you?\n" +
                           "Bonjour! Je m'appelle Jean\n" +
                           "Vous désirez?\n");

        ArrayList<Task> userInputs = new ArrayList<>();

        while(true) {
            String input = receiveCommand();
            if (input.equals("bye")) {
                bye();
                break;
            } else if (input.equals("list")) {
                list(userInputs);
            } else {
                add(new Task(input), userInputs);
            }
        }
    }
}
