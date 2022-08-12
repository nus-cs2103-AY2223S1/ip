import java.util.Scanner;
import java.util.stream.IntStream;

public class Duke {
    private final Task[] tasks;
    private int id = 0;

    public Duke(String name) {
        this.tasks = new Task[100];
        speak(String.format("Hello! I'm %s\nWhat do you need to do?", name));
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
                if (input.matches("^mark \\d+$")) {
                    int task = getIdFromCommand(input);
                    if (task < id && task >= 0) {
                        tasks[task].setDone(true);
                        speak(String.format("Nice! I've marked this task as done:\n%s", tasks[task]));
                    } else {
                        speak("Invalid task number");
                    }
                } else if (input.matches("^unmark \\d+$")) {
                    int task = getIdFromCommand(input);
                    if (task < id && task >= 0) {
                        tasks[task].setDone(false);
                        speak(String.format("Ok, I've marked this task as not done yet:\n%s", tasks[task]));
                    } else {
                        speak("Invalid task number");
                    }
                } else {
                    tasks[id++] = new Task(input);
                    speak(String.format("Nice! I've added this task to your list:\n%s", input));
                }
                return true;
        }
    }

    private int getIdFromCommand(String input) {
        return Integer.parseInt(input.split(" ")[1]) - 1;
    }

    private void speak(String text) {
        String line = "_".repeat(20) + '\n';
        System.out.println(line);
        System.out.println(text);
        System.out.println(line);
    }

    public void listHistory() {
        StringBuilder output = new StringBuilder();
        IntStream.range(0, this.id).forEach(i -> output.append(String.format("%d. %s%n", i + 1, tasks[i])));
        speak(output.toString());
    }

    private void goodbye() {
        speak("Bye. Hope to see you again soon!");
    }
}
