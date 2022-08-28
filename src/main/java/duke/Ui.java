package duke;

import java.util.Scanner;

public class Ui {
    private final String GREETING = "Hello";
    private final String BYE = "Goodbye";
    private Parser parser;

    public Ui() {
        this.parser = new Parser();
    }

    public void doGreeting() {
        System.out.println(this.GREETING);
    }
    public void doBye() {
        System.out.println(this.BYE);
    }
    public void giveInput(TaskList tasks) {
        String input;
        Scanner sc = new Scanner(System.in);
        while (!this.parser.isBye()) {
            input = sc.nextLine();
            try {
                this.parser.parse(input, tasks);
            } catch (DukeException e) {
                System.out.println(e);
            }
        }
    }
}
