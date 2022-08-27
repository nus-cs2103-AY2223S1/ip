package hazell;

import java.util.Scanner;

public class Ui {
    Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public boolean hasNextCommand() {
        return this.scanner.hasNextLine();
    }

    public Command getNextCommand() {
        return Command.parse(this.scanner.nextLine().strip());
    }
    public void reply(String msg) {
        String DIVIDER = "\t____________________________________________________________";
        System.out.println(DIVIDER);
        for (String line : msg.split("\n")) {
            System.out.println("\t" + line);
        }
        System.out.println(DIVIDER);
    }
}
