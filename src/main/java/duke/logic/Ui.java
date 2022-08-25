package duke.logic;

import java.util.Scanner;

public class Ui {
    private Scanner scanner;
    private static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static String prompt = "> ";

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void start(Parser parser) {
        System.out.println(Ui.logo);
        System.out.println("Hello! I'm Duke.\nWhat can I do for you?");
        System.out.print(prompt);   //prompt

        while (scanner.hasNextLine()) {
            parser.parse(scanner.nextLine()).run();
            System.out.print(prompt);
        }
    }
}
