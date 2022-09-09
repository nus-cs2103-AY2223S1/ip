package betago;

/**
 * Ui class that prints statements for the user.
 */
public class Ui {

    /**
     * Prints a greeting message for the user.
     */
    public static void greet() {
        System.out.println(
                "Greetings Human! I am BetaGo, your personal robot assistant!\nHow may I assist you today?\n");
    }

    /**
     * Prints a load file error message where the data.txt file consist of an invalid entry.
     */
    public static void printLoadFileError() {
        System.out.println("The data.txt file has an invalid entry. Delete the file and try again.\n");
    }

    /**
     * Prints a file not found error message where there is an error in detecting the data file.
     */
    public static void printFileNotFoundError() {
        System.out.println("There is an error in detecting the data file.\n");
    }

    /**
     * Prints an input/output error message where there is an error in creating new data file.
     */
    public static void printInputOutputError() {
        System.out.println("Unable to create new data file.\n");
    }

    /**
     * Prints a save error message where there was an error in saving the TaskList to the data file.
     */
    public static void printSaveError() {
        System.out.println("There is an error when saving list to data file.\n");
    }

}
