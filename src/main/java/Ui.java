import utils.Parser;

import java.util.Scanner;

public class Ui {
    public static String Logo = ".__ .  ..  ..___" + "\n" +
            "|  \\|  ||_/ [__ " + "\n" +
            "|__/|__||  \\[___" + "\n";

    public static String Name = "Duke";
    public static String Line = "──────────────────────────────────────────";

    private boolean open = true;
    private Scanner in;

    public Ui() {
    }

    public boolean isOpen(){
        return this.open;
    }

    public void start() {
        this.open = true;
        System.out.println(Ui.Logo);
        this.printWithHorizontalRule("Hello! I'm " + Ui.Name + "\n" + "What can I do for you?");
    }

    private String readInput() {
        return in.nextLine();
    }

    public Command readCommand() {
        Parser.ParsedInputArguments args = Parser.getInputArguments(this.readInput());
        Command c = Command.getCommand(args);
        return c;
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
