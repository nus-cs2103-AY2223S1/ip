import task.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Blob {
    // Divider to separate message instances by the chat-bot
    private static final String MESSAGE_DIVIDER = "=".repeat(100);
    // Header to signify start of a message by the chat-bot
    private static final String MESSAGE_HEADER = "\u001B[33m" + "Blob says: " + "\u001B[0m";

    private final ArrayList<Task> taskList = new ArrayList<>();

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

    /**
     * Enumerates the list of added tasks.
     */
    private void listTasks() {
        StringBuilder tasksStringBuilder = new StringBuilder();
        tasksStringBuilder.append("\n");
        for (int i = 0; i < taskList.size(); i++) {
            tasksStringBuilder.append(String.format("\t\t%d. %s \n", i + 1, taskList.get(i).toString()));
        }
        speak("Blob remember tasks...", tasksStringBuilder.toString());
    }

    /**
     * Adds a task to the list of tasks.
     */
    private void addTask(String description) {
        Task task = new Task(description);
        taskList.add(task);
        speak("Blob will remember task...", String.format("\n\t\t%s \n", task));
    }

    /**
     * Marks task at the index to be done.
     *
     * @param index The index of the task to be marked done
     */
    private void markTaskAtIndexDone(int index) {
        try {
            Task task = taskList.get(index - 1);
            task.markAsDone();
            speak("Blob congratulates on task well done...", String.format("\n\t\t%s \n", task));
        } catch (IndexOutOfBoundsException ioobe) {
            speak("Blob cannot find task...", "Maybe task don't exist...?");
        }
    }

    /**
     * Marks task at the index to be undone.
     *
     * @param index The index of the task to be marked undone
     */
    private void markTaskAtIndexUndone(int index) {
        try {
            Task task = taskList.get(index - 1);
            task.markAsUndone();
            speak("Blob will mark as undone...", String.format("\n\t\t%s \n", task));
        } catch (IndexOutOfBoundsException ioobe) {
            speak("Blob cannot find task...", "Maybe task don't exist...?");
        }
    }

    /**
     * Prints a sequence of strings, each in an indented newline encapsulated in a message instance.
     *
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
            String[] deconstructedInput = input.split(" ", 2);
            String command = deconstructedInput[0];
            switch (command) {
            case "bye":
                sayGoodbye();
                isRunning = false;
                break;
            case "list":
                listTasks();
                break;
            case "mark":
                try {
                    int index = Integer.parseInt(deconstructedInput[1]);
                    markTaskAtIndexDone(index);
                } catch(NumberFormatException exception) {
                    speak("Blob cannot find task...", "Maybe task don't exist...?");
                }
                break;
            case "unmark":
                try {
                    int index = Integer.parseInt(deconstructedInput[1]);
                    markTaskAtIndexUndone(index);
                } catch(NumberFormatException exception) {
                    speak("Blob cannot find task...", "Maybe task don't exist...?");
                }
                break;
            default:
                addTask(input);
            }
        }
    }
}
