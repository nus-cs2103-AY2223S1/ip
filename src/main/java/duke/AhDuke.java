package duke;

import java.util.Scanner;

import duke.task.TaskList;


/**
 * Class containing the main Duke application.
 * Initialises the application.
 */
public class AhDuke {
    private final Scanner sc;
    private final TaskList tl;
    private final Ui ui;

    /**
     * Constructor to initialise Duke.
     */
    public AhDuke() {
        this.sc = new Scanner(System.in);
        Storage st = new Storage("./data/dukedata.txt");
        this.tl = new TaskList(st.setFile());
        this.ui = new Ui();
    }

    /**
     * Main method that is run.
     * @param args arguments that is passed in main.
     */
    public static void main(String[] args) {
        AhDuke ahDuke = new AhDuke();
        ahDuke.run();
    }

    /**
     * Main run method for Duke.
     */
    public void run() {
        Parser parser = new Parser(ui, tl);
        System.out.println(Ui.messageWithLine(ui.printIntro()));
        while (sc.hasNext()) {
            String curr = sc.nextLine();
            String messageReceived = parser.parseInput(curr);
            if (messageReceived.equals("exit")) {
                sc.close();
                System.out.println(Ui.messageWithLine(ui.printGoodByeMessage()));
                System.exit(0);
                return;
            }
            System.out.println(Ui.messageWithLine(messageReceived));
        }
    }
}
