package ted.ui;

import java.util.Scanner;

/**
 * Represents the user interaction feature of the bot. A <code>Ui</code> object reads the user input
 * and responds to the user.
 */
public class Ui {
    private Scanner sc = new Scanner(System.in).useDelimiter("\\n");

    /**
     * Prints out a welcome message to the user.
     */
    public void welcomeUser() {
        String banner = "~~~~~~~~~~~\n"
                + " TED |._.|\n"
                + "~~~~~~~~~~~\n";
        System.out.println(banner);
        System.out.println("Hello! I'm Ted and I'm here to help you keep track of your tasks |._.|\n"
                + "How can I assist you today?\n");
    }

    /**
     * Prints bot responses in a standard format.
     *
     * @param filler bot response.
     */
    public void tedResponse(String filler) {
        System.out.println("~ |._.| ~\n" + filler + "~\n");
    }

    /**
     * Prints farewell message to user.
     */
    public void showExit() {
        System.out.println("Goodbye! Have a pleasant day |._.|");
        sc.close();
    }

    /**
     * Prints response to user command to delete task.
     *
     * @param task task user deletes.
     * @param size size of tasklist.
     */
    public void deleteResponse(String task, int size) {
        tedResponse("Done! Task deleted:\n" + task + "\nremaining task count: " + size + "\n");
    }

    /**
     * Prints response to user command to mark task.
     *
     * @param task task user marks.
     */
    public void markResponse(String task) {
        tedResponse("Great! Task done:\n" + task + "\n");
    }

    /**
     * Prints response to user command to unmark task.
     *
     * @param task task user unmarks.
     */
    public void unmarkResponse(String task) {
        tedResponse("Aw :( Task undone:\n" + task + "\n");
    }

    /**
     * Prints response to user command to add task.
     *
     * @param task task user adds.
     * @param size size of tasklist.
     */
    public void addResponse(String task, int size) {
        tedResponse("added to tasklist:\n" + task + "\ntask count: " + size + "\n");
    }

    /**
     * Reads user input and returns it in the form of a string.
     *
     * @return String representing the user command.
     */
    public String readCommand() {
        String command = "";
        if (sc.hasNext()) {
            command = sc.next();
        }
        return command;
    }
}
