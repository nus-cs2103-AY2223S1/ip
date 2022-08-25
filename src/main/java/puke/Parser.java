package puke;

/**
 * Handles Parsing of input of the user
 */
public class Parser {

    /**
     * Parses input scans for the description of the task
     * @param s input
     * @param type type of task trying to be initiated
     * @return String representation of description of task
     * @throws DukeException
     */
    public static String getMessage(String s, String type) throws DukeException{
        String result = "";
        for (int i = 0 ; i < s.length(); i++) {
            if (i == 0) {
                continue;
            }
            if (s.charAt(i) == "/".charAt(0)) {
                break;
            }
            result += s.charAt(i);
        }

        if (result.length() == 0) {
            throw new DukeException("    ____________________________________________________________\n" +
                    "     OOPS!!! The Description of a " + type + " cannot be empty.\n" +
                    "    ____________________________________________________________");
        }
        return result;
    }

    /**
     * Gets the date as given by user
     * @param s String representation of user input
     * @return String representation of Date
     */
    public static String getDate(String s) {
        String result = "";
        int temp = 0;
        for (int i = 0 ; i < s.length(); i++) {
            if (s.charAt(i) == "/".charAt(0)) {
                temp = i;
            }
        }
        for (int r = temp + 4 ; r < s.length(); r++) {
            result += s.charAt(r);
        }
        return result;
    }

    public static String getFindTask(String s) {
        String temp = "";
        for (int i = 0; i < s.length(); i++ ) {
            if (i == 0) { continue;}
            temp += s.charAt(i);
        }
        return temp;
    }
} 
