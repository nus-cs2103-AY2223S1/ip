package hazell.ui;

import java.util.Scanner;

import hazell.Hazell;

/**
 * A class that abstracts the interactions with user via the command line.
 */
public class Cli implements UiInterface {
    private Scanner scanner;

    private Hazell hazell;
    private static final String APP_LOGO = "  _    _               _ _ \n"
            + " | |  | |             | | |\n"
            + " | |__| | __ _ _______| | |\n"
            + " |  __  |/ _` |_  / _ \\ | |\n"
            + " | |  | | (_| |/ /  __/ | |\n"
            + " |_|  |_|\\__,_/___\\___|_|_|\n";


    /**
     * Create a new Ui instance.
     */
    public Cli() {
        scanner = new Scanner(System.in);
    }

    @Override
    public void attachBotInstance(Hazell hazell) {
        this.hazell = hazell;
    }

    /**
     * Checks if user has entered a new command.
     *
     * @return Whether user has entered a new command
     */
    @Override
    public boolean hasNextUserInput() {
        return scanner.hasNextLine();
    }

    /**
     * Get the next command from user.
     *
     * @return A parsed Command object
     */
    @Override
    public String getNextUserInput() {
        return scanner.nextLine().strip();
    }

    @Override
    public void displayUserInput(String input) {
        System.out.println(input);
    }


    @Override
    public void displayBotResponse(String response) {
        String DIVIDER = "\t____________________________________________________________";
        System.out.println(DIVIDER);
        for (String line : response.split("\n")) {
            System.out.println("\t" + line);
        }
        System.out.println(DIVIDER);
    }

    public void sendSplash() {
        System.out.println(APP_LOGO);
    }

    @Deprecated
    public void step() { }

    @Override
    public void start() {
        sendSplash();
    }

    @Override
    public void run() {
        while (true) {
            hazell.step();
        }

    }
}
