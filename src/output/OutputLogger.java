package output;

import java.util.stream.Collectors;

/**
 * Class for static methods related to console output
 */
public class OutputLogger {
    private static final String NAME = "JARVIS";
    public static String indent(String text) {
        return text
            .lines()
            .map(x -> "\t" + x)
            .collect(Collectors.joining("\n"));
    }

    public static void output(String msg) {
        String line = "____________________________________________________________";
        String out = String.format("%s%n%s:\n%s%n%s%n", line, NAME, msg, line);
        System.out.println(indent(out));
    }

    public static void printIntroduction() {
        String logo = "\n" +
                "     _   _    ___ __   __ ___  ___ \n" +
                "  _ | | /_\\  | _ \\\\ \\ / /|_ _|/ __|\n" +
                " | || |/ _ \\ |   / \\ V /  | | \\__ \\\n" +
                "  \\__//_/ \\_\\|_|_\\  \\_/  |___||___/\n";
        System.out.print(logo);
        output(String.format("Hello,I'm %s!\nWhat can I do for you?", NAME));
    }

}
