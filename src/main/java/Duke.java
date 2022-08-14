import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello, I'm Duke!\n" + logo + "\nWhat can I do for you?");

        Scanner sc = new Scanner(System.in);
        TasksList tasksList = new TasksList();

        while (sc.hasNext()) {
            String command = sc.nextLine();
            if (command.equals("bye")) {
                message("Bye! Hope to see you again soon!");
                sc.close();
                break;
            } else if (command.equals("list")) {
                tasksList.listTasks();
            } else if (command.startsWith("mark") || command.startsWith("unmark")) {
                String[] words = command.split(" ", 2);
                if (words.length == 1) {
                    System.out.println("Please specify the task by its id!");
                } else if (words[0].equals("mark")) {
                    tasksList.markTask(Integer.parseInt(words[1]));
                } else {
                    tasksList.unmarkTask(Integer.parseInt(words[1]));
                }
            } else {
                tasksList.addToList(command);
            }
        }
    }

    /**
     * Prints the message from the bot.
     *
     * @param text the message for the bot to reply with.
     */
    private static void message(String text) {
        System.out.println("Duke: " + text);
    }
}
