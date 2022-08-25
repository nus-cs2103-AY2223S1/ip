package duke;

public class UI {
    public static String breakLine = "____________________________________________________________\n";
    public static String greeting = "Hello, I'm LishBot v6.9!\n" + "How may I help you today?\n";
    public static String taskListOpening = "Finding your task list...\n" + "Found it! Here are what you have to do:\n";
    public static String noListFound = "Congrats! You have finished all your task!\n";
    public static String findingRelatedTask = "Let me find tasks that match your description...\n";
    public static String noRelatedTaskFound = "I cannot find any tasks that match that description :(\n";
    public static String goodBye = "Glad to be of help! See you later~\n";
    public static String markAsDone = "Great! You have finished a task. I will mark this as done\n";
    public static String markAsNotDone = "Okay. I will mark this task as not done yet\n";
    public static String removeTask = "Roger that! I will remove this task\n";
    public static String numberOfTaskLeft = "Now, the number of tasks you have is ";
    public static String addTask = "Got it! I will add that task now\n";
    public static String fileNotFound = "I cannot find the data file \n";
    public static String createFile = "But don't worry I will create one for you :)\n";

    /**
     * Prints LishBot's response
     * this function specifies the format of LishBot's response
     *
     * @param response
     */
    public static void printResponse(String response) {
        System.out.println(breakLine);
        System.out.println(response);
        System.out.println(breakLine);
    }

}
