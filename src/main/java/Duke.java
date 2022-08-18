
import java.util.Scanner;
public class Duke {
    private static String WELCOME_MESSAGE =  "Hello! I'm Duke\n" + "What can I do for you?";
    private static String GOODBYE_MESSAGE =  "Bye. Hope to see you again soon!";

    private String[] inputs = new String[100];
    private int numInputsStored = 0;

    private void store(String input) {
        this.inputs[numInputsStored++] = input;
        System.out.println(String.format("added: %s", input));
    }
    private void getInputs() {
        for (int i = 0; i < this.numInputsStored; i++) {
            System.out.println(String.format("%d. %s", i+1, this.inputs[i]));
        }
    }

    /**
    * Note: You are strongly encouraged to customize the chatbot name,
    * command/display formats, and even the personality of the chatbot
    * to make your chatbot unique.
    */
    public static void main(String[] args) {
        System.out.println(WELCOME_MESSAGE);

        Scanner scanner = new Scanner(System.in);
        Duke duke = new Duke();

        while (true) {

            String input = scanner.nextLine();

            if (input.equals("bye")) {
                System.out.println(GOODBYE_MESSAGE);
                break;
            }

            if (input.equals("list")) {
                duke.getInputs();
                continue;
            }

            duke.store(input);

        }

        scanner.close();
    }
}
