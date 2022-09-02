package duke;

import duke.command.Command;
import duke.parser.Parser;

import java.util.Scanner;

public class Ui {
    private final static String LOGO = ".__ .  ..  ..___" + "\n" +
            "|  \\|  ||_/ [__ " + "\n" +
            "|__/|__||  \\[___" + "\n";

    private final static String NAME = "duke.Duke";
    private final static String LINE = "──────────────────────────────────────────";

    private boolean isOpen = true;
    private Scanner in;

    public Ui() {
    }

    public boolean isOpen(){
        return this.isOpen;
    }

    public void start() {
        this.isOpen = true;
        System.out.println(Ui.LOGO);
        this.printWithHorizontalRule("Hello! I'm " + Ui.NAME + "\n" + "What can I do for you?");
        this.in = new Scanner(System.in);
    }

    private String readInput() {
        return in.nextLine();
    }

    public Command readCommand() {
        Parser.ParsedInputArguments args = Parser.getInputArguments(this.readInput());
        Command c = Command.getCommand(args);

        if (c.isExit()) {
            this.isOpen = false;
            this.in.close();
        }
        return c;
    }

    public void printWithHorizontalRule(String s) {
        printHorizontalRule();
        System.out.println(s);
        printHorizontalRule();
        System.out.println("");
    }

    private void printHorizontalRule() {
        System.out.println(LINE);
    }
}
