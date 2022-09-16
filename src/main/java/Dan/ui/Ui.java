package dan.ui;

/**
 * Manages the display of the user interface.
 */
public class Ui {

    /**
     * Adds indent of 4 spaces to the lines to be printed
     *
     * @param obj The object to be printed
     * @return A string of lines that are indented
     */
    public static String printIndent(Object obj) {
        return ("    " + obj.toString().replace("\n", "\n    "));
    }

    /**
     * Prints a horizontal line
     *
     * @return a horizontal line
     */
    public static String printLine() {
        return printIndent("____________________________________________________________");
    }

    /**
     * Prints the welcome message.
     *
     * @return a string of lines containing the welcome message
     */
    public static String greet() {
        StringBuilder result = new StringBuilder();
        result.append(printIndent("Hello from\n"));
        String logo = "\n"
                + "_ .-') _     ('-.         .-') _  \n"
                + "( (  OO) )   ( OO ).-.    ( OO ) ) \n"
                + " \\     .'_   / . --. /,--./ ,--,'  \n"
                + " ,`'--..._)  | \\-.  \\ |   \\ |  |\\  \n"
                + " |  |  \\  '.-'-'  |  ||    \\|  | ) \n"
                + " |  |   ' | \\| |_.'  ||  .     |/  \n"
                + " |  |   / :  |  .-.  ||  |\\    |   \n"
                + " |  '--'  /  |  | |  ||  | \\   |   \n"
                + " `-------'   `--' `--'`--'  `--'   \n";

        result.append(printIndent(logo + "Ouuuuuuuuuhhhhhh Spo0ky"));
        result.append(printIndent("What can I do for you?"));
        return result.toString();
    }

    /**
     * Prints the goodbye message
     *
     * @return A string of lines containing the goodbye message
     */
    public static String bye() {
        return printIndent("Boo! Bye bye... :(");
    }
}
