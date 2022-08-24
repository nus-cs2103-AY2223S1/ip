import java.util.Scanner;

public class Parser {
    //example input to parse: deadline taskToDo /by 2022-02-03 18:00-19:00

    public static String getCommand(String userInput) {
        return userInput.split(" ")[0];
    }

    public static String getDescription(String userInput) {
        return userInput.split(" ", 2)[1].split(" /")[0];
    }

    public static String getDate(String userInput) {
        return userInput.split(" /")[1].split(" ")[1];
    }

    public static String getFrom(String userInput) {
        return userInput.split(" /")[1].split(" ")[2].split("-")[0];
    }

    public static String getTo(String userInput) {
        return userInput.split(" /")[1].split(" ")[2].split("-")[1];
    }

    public static int getIndex(String userInput) {
        return Integer.parseInt(getDescription(userInput)) - 1;
    }

    public static boolean isValidDescription(String userInput) {
        return userInput.split(" ").length > 1;
    }

    public static boolean isValidDateTime(String userInput) {
        return userInput.split("/")[1].split(" ").length > 1;
    }
}
