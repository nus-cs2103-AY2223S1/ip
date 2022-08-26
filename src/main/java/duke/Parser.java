package duke;

import java.time.LocalDate;

public class Parser {

    /**
     * This method checks if a string typed can be a number or not
     * @param number the string that is entered that could be a number
     * @return a boolean value indicating whether the string entered can be parsed as an integer
     */
    public static boolean isNumeric(String number) {
        if (number == null) {
            return false;
        }
        try {
            // try to parse the integer as a number
            int num = Integer.parseInt(number);
        } catch (NumberFormatException numberFormatException) {
            return false;
        }
        return true;
    }

    /**
     * This method joins the individual string elements in an array from a start position
     * @param stringArray the array of strings
     * @param start the starting position
     * @return the string representing the elements joined from the starting position to the end
     */
    public static String joinString(String[] stringArray, int start) {
        StringBuilder outputString = new StringBuilder();
        for (int i = start; i < stringArray.length; i++) {
            String toAdd = stringArray[i] + " ";
            outputString.append(toAdd);
        }

        return outputString.toString();
    }

    /**
     * Creates a local date object
     * @param stringDate the date entered by the user
     * @return a local date object representing the date
     */
    public static LocalDate createLocalDate(String stringDate) {
        String[] currDateWords = stringDate.split("/");
        String year = currDateWords[2];
        String month = currDateWords[1].length() < 2 ? "0" + currDateWords[1] : currDateWords[1];
        String day = currDateWords[0].length() < 2 ? "0" + currDateWords[0] : currDateWords[0];
        String currDateString = year + '-' + month + '-' + day;
        return LocalDate.parse(currDateString);
    }

    /**
     * Check if the command is to mark a task
     * @param wordsArray the array of words entered by the user
     * @return a boolean value stating whether to mark a task or not
     */
    public static boolean isMarkTask(String[] wordsArray) {
        return wordsArray.length > 1 &&
                wordsArray[0].equals("mark") && isNumeric(wordsArray[1]);
    }

    /**
     * Check if the command is to unmark a task
     * @param wordsArray the array of words entered by the user
     * @return a boolean value stating whether to unmark a task or not
     */
    public static boolean isUnmarkTask(String[] wordsArray) {
        return wordsArray.length > 1 &&
                wordsArray[0].equals("unmark") && isNumeric(wordsArray[1]);
    }

    /**
     * Check if the command is to delete a task
     * @param wordsArray the array of words entered by the user
     * @return a boolean value stating whether to delete a task or not
     */
    public static boolean isDeleteTask(String[] wordsArray) {
        return wordsArray.length > 1 && wordsArray[0].equals("delete")
                && isNumeric(wordsArray[1]);
    }

    /**
     * Check if the command is to add a to-do task
     * @param wordsArray the array of words entered by the user
     * @return a boolean value stating whether to add a to-do task or not
     */
    public static boolean isAddTodoTask(String[] wordsArray) {
        return wordsArray[0].equals("todo");
    }

    /**
     * Check if the command is to add a deadline task
     * @param wordsArray the array of words entered by the user
     * @return a boolean value stating whether to add a deadline task or not
     */
    public static boolean isAddDeadlineTask(String[] wordsArray) {
        return wordsArray[0].equals("deadline");
    }

    /**
     * Check if the command is to add an event task
     * @param wordsArray the array of words entered by the user
     * @return a boolean value stating whether to add an event task or not
     */
    public static boolean isAddEventTask(String[] wordsArray) {
        return wordsArray[0].equals("event");
    }
}
