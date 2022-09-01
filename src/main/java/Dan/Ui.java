package dan;

/**
 * Manages the display of the user interface.
 */
public class Ui {

    /**
     * Adds indent of 4 spaces to the lines to be printed
     *
     * @param obj The object to be printed
     */
    public static void printIndent(Object obj) {
        System.out.println("    " + obj.toString().replace("\n", "\n    "));
    }

    /**
     * Prints a horizontal line
     */
    public static void printLine() {
        printIndent("____________________________________________________________");
    }

    /**
     * Adds an indent to the lines to be printed, and encapsulates it with lines, forming a block
     *
     * @param s String to be printed
     */
    public static void printBlock(String s) {
        printLine();
        printIndent(s);
        printLine();
    }

    /**
     * Prints the welcome message
     */
    public static void greet() {
        printLine();
        printIndent("Hello from\n");
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

        printIndent(logo + "Ouuuuuuuuuhhhhhh Spo0ky");
        printIndent("What can I do for you?");
        printLine();
    }

    /**
     * Prints the goodbye message
     */
    public static void sayonara() {
        printBlock("Boo! Bye bye... :(");
    }
}
