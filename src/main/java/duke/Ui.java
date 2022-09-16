package duke;

/**
 * Deals with interactions with the user.
 */
public class Ui {

    /**
     * Prints out Duke's greeting to the user.
     */
    public String greet() {
        String text;
        String logo = " ____                 \n"
                + "|  _ \\ _ _ _ __ _____ \n"
                + "| | | |  _  | |/ / _ \\\n"
                + "| |_| | |_| |   /  __/\n"
                + "|____/ \\__,_|\\_/ \\___|\n";
        text = "Hello! I'm\n" + logo;
        text = text + "What can I do for you?\n";
        return text;
    }

}
