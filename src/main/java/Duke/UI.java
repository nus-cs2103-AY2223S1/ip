package Duke;

public class UI {
    public static String breakLine = "____________________________________________________________\n";
    public static String greeting = "Hello, I'm LishBot v6.9!\n" + "How may I help you today?\n";
    public static String taskListOpening = "Finding your task list...\n" + "Found it! Here are what you have to do:\n";
    public static String noListFound = "Congrats! You have finished all your task!\n";

    public static void printResponse(String response) {
        System.out.println(breakLine);
        System.out.println(response);
        System.out.println(breakLine);
    }

}
