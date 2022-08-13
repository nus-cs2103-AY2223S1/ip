import java.util.List;
import java.util.Scanner;

public class Duke {
    private static String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Hello from\n" + LOGO);
        greetingMessage();
        listenForInputs();
    }

    private static void greetingMessage() {
        String[] messages = {"Hello! I'm Duke.", "What can I do for you?"};
        formatAndPrint(List.<String>of(messages));
    }

    private static void formatAndPrint(List<? super String> texts) {
        String divider = "    ____________________________________________________________";
        String padding = "     ";
        System.out.println(divider);
        texts.forEach((text) -> System.out.println(padding + text));
        System.out.println(divider);
    }

    private static void listenForInputs() {
        String input = scanner.nextLine();
        if (input.equals("bye")) {
            formatAndPrint(List.<String>of("Bye bye"));
            return;
        }
        formatAndPrint(List.<String>of(input));
        listenForInputs();
    }
}
