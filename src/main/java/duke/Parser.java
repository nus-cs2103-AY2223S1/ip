package duke;

public class Parser {

    public static String parseUserInput(String userInput) {
        if (userInput.trim().contains(" ")) {
            return userInput.trim().substring(0, userInput.indexOf(" ")).trim();
        }
        return userInput;
    }

    public static String getTaskName(String userInput) {
        /* String[] splitString = userInput.split(" ");
        return splitString[1]; */
        if (userInput.contains("/")) {
            return userInput.substring(userInput.indexOf(" ") + 1, userInput.indexOf("/") - 1).trim();
        }
        return userInput.substring(userInput.indexOf(" ") + 1).trim();
    }

    public static String getDate(String userInput) {
        return userInput.substring(9);
    }

    public static String parseUserDate(String userInput) {
        if (userInput.contains("/")) {
            System.out.println("Date: " + userInput.substring(userInput.indexOf("/") + 4));
            return userInput.substring(userInput.indexOf("/") + 4);
        }
        return "";
    }
}
