import java.util.Scanner;

public class Blob {
    // Divider to separate message instances by the chat-bot
    private static final String MESSAGE_DIVIDER = "=".repeat(100);
    // Header to signify start of a message by the chat-bot
    private static final String MESSAGE_HEADER = "\u001B[33m" + "Blob says: " + "\u001B[0m";

    private String[] taskList = new String[100];
    private int taskIndex = 0;

    /**
     * Prints a greeting message.
     */
    private void greet() {
        speak("Hello... me Blob...", "How can Blob help...?");
    }

    /**
     * Prints a parting message.
     */
    private void sayGoodbye() {
        this.speak("Thanks for talking to Blob...", "Blob see you soon...");
    }

    private void listTasks() {
        StringBuilder tasksStringBuilder = new StringBuilder();
        tasksStringBuilder.append("\n");
        for (int i = 0; i < taskIndex; i++) {
            tasksStringBuilder.append(String.format("\t\t%d. %s \n", i + 1, taskList[i]));
        }
        speak("Blob remember tasks...", tasksStringBuilder.toString());
    }

    private void addTask(String task) {
        taskList[taskIndex] = task;
        taskIndex++;
        speak("Blob will remember task...", String.format("\n\t\t%s \n", task));
    }

    /**
     * Prints a sequence of strings, each in an indented newline encapsulated in a message instance.
     * @param content The sequence of strings to be printed
     */
    private void speak(String ...content) {
        System.out.println("\n" + MESSAGE_DIVIDER);
        System.out.println(MESSAGE_HEADER);
        for (int i = 0; i < content.length; i++) {
            System.out.println("\t" + content[i]);
        }
        System.out.println(MESSAGE_DIVIDER + "\n");
    }

    /**
     * Start the interaction with an instance of Blob
     */
    public void start() {
        this.greet();
        Scanner sc = new Scanner(System.in);
        boolean isRunning = true;
        while (isRunning) {
            System.out.print(">> ");
            String input = sc.nextLine();
            switch (input) {
            case "bye":
                sayGoodbye();
                isRunning = false;
                break;
            case "list":
                listTasks();
                break;
            default:
                addTask(input);
            }
        }
    }
}
