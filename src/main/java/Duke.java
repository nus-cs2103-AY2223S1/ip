import java.util.Scanner;

public class Duke {

    public Duke(String name) {
        speak(String.format("Hello! I'm %s\nWhat can I do for you?", name));
    }

    public static void main(String[] args) {
        Duke duke = new Duke("Duke");

        Scanner sc = new Scanner(System.in);
        while (true) {
            if (!duke.callback(sc.next())) {
                break;
            }
        }
    }

    private boolean callback(String input) {
        if (input.compareTo("bye") == 0) {
            goodbye();
            return false;
        }
        speak(input);
        return true;
    }

    private void speak(String text) {
        String line = "_".repeat(20) + '\n';
        System.out.println(line);
        System.out.println(text);
        System.out.println(line);
    }

    private void goodbye() {
        speak("Bye. Hope to see you again soon!");
    }
}
