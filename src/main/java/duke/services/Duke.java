package duke.services;

import java.io.IOException;

/**
 * Provides Duke's high-level functionality
 */
public class Duke {

    /** What Duke will tell the user after a command */
    private static String reply;

    /** Is Duke interacting with the user? */
    private static boolean isActive;

    /**
     * Duke performs its activation behaviour. Save data is loaded.
     *
     * @throws IOException From IO errors when loading save data
     */
    public static void activate() throws IOException {
        isActive = true;
        Storage.loadData();
        setReply(new String[] {
                "Hello! I'm Duke",
                "What can I do for you?",
        });
    }

    /**
     * Responds to the command and gives a reply
     *
     * @param command User's inputted command
     * @return Duke's reply
     */
    public static String getResponse(String command) {
        String[] words = Parser.convertToWords(command);
        if (words.length > 0) {
            try {
                //could try grouping words.length == 1 cases
                if (words.length == 1 && words[0].equals("bye")) {
                    Duke.deactivate();
                } else if (words.length == 1 && words[0].equals("list")) {
                    TaskList.listTasks();
                } else if (words.length == 1 && words[0].equals("SAVE")) {
                    Storage.wipeDataOnExit(false);
                } else if (words.length == 1 && words[0].equals("WIPE")) {
                    Storage.wipeDataOnExit(true);
                } else if (words[0].equals("todo")) {
                    TaskList.addTodo(words);
                } else if (words[0].equals("deadline")) {
                    TaskList.addDeadline(words);
                } else if (words[0].equals("event")) {
                    TaskList.addEvent(words);
                } else if (words[0].equals("mark")) {
                    TaskList.markTaskAsDone(words);
                } else if (words[0].equals("unmark")) {
                    TaskList.markTaskAsNotDone(words);
                } else if (words[0].equals("delete")) {
                    TaskList.deleteTask(words);
                } else if (words[0].equals("find")) {
                    TaskList.findTasksContainingKeyword(words);
                } else {
                    setReply(new String[]{"I'm sorry, I don't know that command"});
                }
            } catch (IllegalArgumentException | IOException e) {
                setReply(new String[]{e.getMessage()});
            }
        }
        return getReply();
    }

    /**
     * Updates duke's reply to the given lines
     */
    public static void setReply(String[] lines) {
        StringBuilder replyBuilder = new StringBuilder();
        System.out.println("____________________________________________________________");
        for (String line : lines) {
            System.out.println(line);
            replyBuilder.append(line).append("\n");
        }
        System.out.println("____________________________________________________________\n");
        reply = replyBuilder.toString();
    }

    public static String getReply() {
        return reply;
    }

    /**
     * Duke performs its deactivation behaviour. Data is saved.
     *
     * @throws IOException From IO errors when saving data
     */
    public static void deactivate() throws IOException {
        Storage.saveData();
        setReply(new String[] {
                "Bye. Hope to see you again soon!",
        });
        isActive = false;
    }

    public static boolean isActive() {
        return isActive;
    }
}
