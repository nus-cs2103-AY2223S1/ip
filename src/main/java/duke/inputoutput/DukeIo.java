package duke.inputoutput;

import java.util.List;

/**
 * Interface to denote the basic functionalities of the IO classes
 */
public interface DukeIo {

    /**
     * Prints the content of the list to the screen numbered.
     *
     * @param list List to be printed
     */
    <U> void printNumberedList(List<U> list);

    /**
     * Prints the content of an array to the screen as a list numbered.
     *
     * @param list Array to be printed
     */
    <U> void printNumberedList(U[] list);

    /**
     * Prints the content of an array to the screen as a unordered numbered.
     *
     * @param list Array to be printed
     */
    <U> void printList(U[] list);

    /**
     * Prints the content of an array to the screen as a unordered numbered.
     *
     * @param list Array to be printed
     */
    <U> void printList(List<U> list);



    /**
     * Prints the message of an exception
     *
     * @param e Exception to print
     */
    void printError(Exception e);

    /**
     * Prints the message in the format of an exception
     *
     * @param msg message to print
     */
    void printError(String msg);

    /**
     * Prints Text with selected features
     *
     * @param txt
     * @param features
     */
    void printTask(String txt, int features);

    /**
     * Prints Text with selected features
     *
     * @param txt Text to be printed
     * @param featuresEnum features to be applied
     */
    void printTask(String txt, DukeCliSettings featuresEnum);

    /**
     * Prints txt with some default formatting.
     *
     * @param txt
     */
    void printTask(String txt);

    /**
     * Returns a line entered by the user as <code>String</code>.
     *
     * @return String
     */
    String readLine();
}
