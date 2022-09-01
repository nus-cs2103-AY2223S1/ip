import utils.InputParser;

import java.util.HashMap;

public class Ui {
    public static String Logo = ".__ .  ..  ..___" + "\n" +
            "|  \\|  ||_/ [__ " + "\n" +
            "|__/|__||  \\[___" + "\n";

    public static String Name = "Duke";
    public static String Line = "──────────────────────────────────────────";

    public Ui() {

    }

    public void start() {
        System.out.println(Ui.Logo);
        this.printWithHorizontalRule("Hello! I'm " + Ui.Name + "\n" + "What can I do for you?");
    }

    public HashMap<String, String> read(String s) {
        return InputParser.getInputArguments(s);
    }

    public void printWithHorizontalRule(String s) {
        printHorizontalRule();
        System.out.println(s);
        printHorizontalRule();
        System.out.println("");
    }

    private void printHorizontalRule() {
        System.out.println(Line);
    }
}
