package duke;

import java.util.Scanner;


/**
 * Represents a ChatBot for managing tasks such as deadline, todo and event.
 */

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private final static String FILE_LOCATION = "./data/duke.txt";

    /**
     * Create ChatBot.
     * @param filePath
     * @throws Exception
     */
    public Duke(String filePath) throws Exception {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.readFile());
    }

    /**
     * Run the ChatBot.
     * Receive command from the users and implement it.
     * @throws Exception
     */
    public void run() throws Exception {
        ui.greet();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String str = sc.nextLine();
            String[] strArr = str.split(" ");
            String command = strArr[0];
            if (command.equals("bye")) {
                ui.exit();
                storage.saveNewChanges(this.tasks);
                break;
            } else if (command.equals("list")) {
                tasks.getPrintedList();
            } else if (command.equals("mark")) {
                int taskNo = Integer.parseInt(strArr[1]);
                tasks.mark(taskNo);
            } else if (command.equals("unmark")) {
                int taskNo = Integer.parseInt(strArr[1]);
                tasks.unmark(taskNo);
            } else if (command.equals("todo") || command.equals("deadline") || command.equals("event")) {
                tasks.add(str);
            } else if (command.equals("delete")) {
                int taskNo = Integer.parseInt(strArr[1]);
                tasks.delete(taskNo);
            } else {
                storage.saveNewChanges(this.tasks);
                throw new MismatchInputException(":( OOPS!!! I'm sorry, but I don't know what that means");
            }
        }
    }

    /**
     * Start ChatBot.
     */
    public static void main(String[] args) throws Exception {
        new Duke(FILE_LOCATION).run();
    }

}

