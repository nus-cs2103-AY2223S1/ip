package duke;

import java.util.Scanner;


public class Duke {
    private Scanner sc;
    private Storage storage;
    private TaskList tasklist;
    private Ui ui = new Ui();

    /**
     * Sets up required objects, loads the data from the storage file.
     * @param filePath Filepath to the text file.
     */
    public Duke(String filePath) {
        storage = new Storage(filePath);
        try {
            storage.readData();
        } catch (DukeException e) {
            this.tasklist = new TaskList();
            ui.errorMsg(e.getMessage());
        }
    }

    /**
     * Sends welcome greeting and processes inputs.
     */
    public void run() {
        TaskHandler taskHandler = new TaskHandler(tasklist, ui);
        ui.welcomeMsg();
        boolean isDone = false;
        sc = new Scanner(System.in);
        while (!isDone) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                isDone = true;
                ui.farewellMsg();
            } else if (input.equals("list")) {
                tasklist.showList();
            } else if (input.startsWith("mark ")) {
                taskHandler.markChild(input);
            } else if (input.startsWith("unmark ")) {
                taskHandler.unmarkChild(input);
            } else if (input.startsWith("delete ")) {
                taskHandler.deleteTask(input);
            } else if (input.startsWith("find ")) {
                taskHandler.findTask(input);
            } else {
                taskHandler.addTask(input);
            }
        }
        storage.writeData();
    }

    public static void main(String[] args) {
        new Duke("src/filestorage/dummylist.txt").run();
    }
}
