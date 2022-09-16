package duke;

/**
 * The object that handles the UI aspects of the application
 */
public class Ui {
    /** Main logo of the app */
    private String logo = " ____        _        \n"
            + "|  _  \\ _   _     _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private String logo2 = "╭━━━╮╱╱╭╮\n"
            + "╰╮╭╮┃╱╱┃┃\n"
            + "╱┃┃┃┣╮╭┫┃╭┳━━╮\n"
            + "╱┃┃┃┃┃┃┃╰╯┫┃━┫\n"
            + "╭╯╰╯┃╰╯┃╭╮┫┃━┫\n"
            + "╰━━━┻━━┻╯╰┻━━╯";




    /**
     * Prints a text in the specified format
     * @param text
     */
    public void printText(String text) {
        assert text != null;
        System.out.println("________________________________________\n"
                + text
                + "\n________________________________________\n");
    }

    /**
     * Prints initial UI of the app
     */
    public String initialize() {
        String string = "";
        string += "Hello from\n" + logo2 + "\n";

        string += "Hello I'm Duke, What can I do for you";
        return string;
    }

    /**
     * Prints final UI of the app
     */
    public String exit() {
        return ("Bye. hope to see you again soon!");
    }

}
