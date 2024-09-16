package duke;

import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * A console-based user interface handler.
 */
public class ConsoleUi extends Ui {
    private Scanner sc;
    private boolean isAlive;

    /**
     * Creates a new console-based UI handler.
     * @param ia The input acceptor where inputs are sent
     */
    public ConsoleUi(InputAcceptor ia) {
        super(ia);
        sc = new Scanner(System.in);
    }

    @Override
    public void runInputLoop() {
        isAlive = true;
        while (isAlive) {
            try {
                processInput(sc.nextLine());
            } catch (NoSuchElementException e) {
                isAlive = false;
            }
        }
    }

    @Override
    public void stopInputLoop() {
        isAlive = false;
    }

    /**
     * Displays a divider.
     */
    private void showDivider() {
        System.out.println("    __________________________________________________");
    }

    @Override
    public void respond(String response) {
        showDivider();
        System.out.println("    " + response.replace("\n", "\n    "));
        showDivider();
    }
}
