package duke.inputoutput;

import java.util.List;

public interface DukeIo {

    /**
     * Prints the content of the list to the screen numbered.
     * 
     * @param list List to be printed
     */
    <U> void printList(List<U> list);

    /**
     * Prints the content of an array to the screen as a list numbered.
     * 
     * @param list Array to be printed
     */
    <U> void printList(U[] list);


    /**
     * Prints the message of an exception
     * 
     * @param e Exception to print
     */
    void printError(Exception e);

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
     * @param txt
     * @param features
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
