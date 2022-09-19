package pixel.util;

/**
 * Exception to be thrown if the format of data in text file is invalid
 */
public class InvalidTextDataFormatException extends RuntimeException {

    public static final String BAD_TASK_FORMATTING = "Seems like one of the tasks in the text file contains "
        + "an incorrect number of sections \n"
        + "Sections are separated by a \" ;;; \"\n"
        + "There are three compulsory sections: \n"
        + "1. Type of task (T, D, E) \n"
        + "2. Status of task (Done, Not Done) \n"
        + "3. And description of task \n\n"
        + "Also optional sections include: \n"
        + "4. The command word (at, by) \n"
        + "5. And the due/ location of the task/ activity \n";
    private final String errorMessage;

    /**
     * Constructor for a new InvalidTextDataFormatException object
     *
     * @param errorMessage description of the exception
     */
    public InvalidTextDataFormatException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

    /**
     * toString method of the exception
     *
     * @return description of the exception without the package name
     */
    @Override
    public String toString() {
        return this.errorMessage;
    }
}
