package duke;

import java.io.File;
import java.nio.file.Paths;
import java.util.Scanner;

public class Duke {
    private Scanner sc;
    private Storage storage;
    private TaskList tasklist;
    private Ui ui = new Ui();

    /**
     * Sets up required objects, loads the data from the storage file.
     */
    public Duke() {
        String workingDir = System.getProperty("user.dir");
        System.out.println(workingDir);
        String pathToDuke = workingDir + "/src/filestorage" + "/dummylist.txt";
        storage = new Storage(pathToDuke);
        try {
            storage.readData();
        } catch (DukeException e) {
            this.tasklist = new TaskList();
            ui.errorMsg(e.getMessage());
        }
    }

    /**
     * Gets the response from Duke.
     * @param input the input command.
     * @return the corresponding response.
     */
    String getResponse(String input) {
        TaskHandler taskHandler = new TaskHandler(tasklist, ui);
        if (input.equals("bye")) {
            storage.writeData();
            return ui.farewellMsg();
        } else if (input.equals("list")) {
            return tasklist.showList();
        } else if (input.startsWith("mark ")) {
            return taskHandler.markChild(input);
        } else if (input.startsWith("unmark ")) {
            return taskHandler.unmarkChild(input);
        } else if (input.startsWith("delete ")) {
            return taskHandler.deleteTask(input);
        } else if (input.startsWith("find ")) {
            return taskHandler.findTask(input);
        } else {
            return taskHandler.addTask(input);
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
        new Duke().run();
    }
}
