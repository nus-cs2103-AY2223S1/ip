import java.util.Scanner;
import java.util.stream.IntStream;

public class Duke {
    private final String[] history;
    private int id = 0;

    public Duke(String name) {
        this.history = new String[100];
        speak(String.format("Hello! I'm %s\nWhat can I do for you?", name));
    }

    public static void main(String[] args) {
        Duke duke = new Duke("Duke");

        Scanner sc = new Scanner(System.in);
        while (true) {
            if (!duke.callback(sc.nextLine())) {
                break;
            }
        }
    }

    private boolean callback(String input) {
        switch (input) {
            case "bye":
                goodbye();
                return false;
            case "list":
                listHistory();
                return true;
            default:
                history[id++] = input;
                speak(input);
                return true;
        }
    }

    private void speak(String text) {
        String line = "_".repeat(20) + '\n';
        System.out.println(line);
        System.out.println(text);
        System.out.println(line);
    }

    public void listHistory() {
        StringBuilder output = new StringBuilder();
        IntStream.range(0, this.id).forEach(i -> output.append(String.format("%d. %s%n", i + 1, history[i])));
        speak(output.toString());
    }

    private void goodbye() {
        speak("Bye. Hope to see you again soon!");
    }
}
