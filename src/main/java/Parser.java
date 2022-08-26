public class Parser {

    public static String parseUserInput(String userInput) {
        if (userInput.trim().contains(" ")) {
            return userInput.trim().substring(0, userInput.indexOf(" ")).trim();
        }
        return userInput;
    }

    public static String getTaskName(String userInput) {
        return userInput.substring(userInput.indexOf(" ") + 1).trim();
    }

    public static String getDate(String userInput) {
        return userInput.substring(9);
    }
}
