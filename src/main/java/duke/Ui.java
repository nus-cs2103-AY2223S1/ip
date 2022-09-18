package duke;

import java.io.IOException;
import java.util.Scanner;
public class Ui {
    private Scanner sc;
    private static final String INTRO = "Hi! I'm ELON MUSK\nWhat can I do for you?";
    private static final String OUTRO = "Bye, Don't miss me please.";

    Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Prints greeting message.
     */
    public static String greet() {
        return INTRO;
    }

    /**
     * Prints exit message.
     */
    public static String bye() {
      return OUTRO;
    }

    /**
     * Prints the task list.
     *
     * @param tasks prints the tasks in the taskList
     */
    public static String printList(TaskList tasks) {
       String str = "Here are the tasks in your list: \n";
        for (int i = 0, d = 1; i < tasks.size(); i++, d++) {
            str += (d + ". " + tasks.get(i) + "\n");
        }
        return str;
    }

    /**
     *
     * @param tasks add task into this taskList
     * @param input prints out the task added
     */
    public static String addedTask(TaskList tasks, Task input) throws IOException {
        tasks.add(input);
        Storage.writeToFile(tasks);
        String result = "Got it. I've added this task: \n";
        result += input.toString();
        String line = String.format("\nNow you have %d tasks in the list.\n", tasks.size());
        result += line;
        return result;
    }

    /**
     *  Prints tasks with specified keywords
     * @param tasks the resulting tasklist to be passed here
     */
    public static String finderPrinter(TaskList tasks) {
        String result = "Here are the matching tasks in your list: \n";
        for (int i = 0, d = 1; i < tasks.size(); i++, d++) {
            result += d + ". " + tasks.get(i) + "\n";
        }
        return result;
    }

}
