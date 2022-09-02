package duke;

/**
 * Represents the Duke class to run the Duke program
 * @author Reuben Chay
 */
public class Duke {

    /**
     * Main driver function to run Duke program
     * @throws DukeException exception specific to Duke
     * @throws IllegalArgumentException exception specifying incorrect input
     */
    public static void main(String[] args) throws DukeException, IllegalArgumentException {

        TaskList taskList = new TaskList();
        Storage storage = new Storage("./tasks.txt");
        Ui ui = new Ui();
        StringReplacer stringReplacer = new StringReplacer();
        Parser parser = new Parser();

        // attempt to open file within same folder as src code, create file if file doesn't exist
        storage.openFile();

        // at this point file will exist, init taskList
        storage.listInit(taskList.getList());

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String welcomeMsg = "Hello! I'm\n" + logo + "\nWhat can I do for you?\n";
        System.out.println(welcomeMsg);

        String input = "";

        while (true) {
            try {
                input = ui.scan();
                if (input.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                } else {
                    parser.parse(input, storage, taskList);
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.toString());
            } catch (DukeException e) {
                System.out.println(e.toString());
            }
        }
        ui.close();
    }
}
