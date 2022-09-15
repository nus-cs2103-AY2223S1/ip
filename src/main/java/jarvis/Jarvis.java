package jarvis;

import jarvis.task.TaskList;

/**
 * Jarvis (credit: Iron Man) is a a Personal Assistant Chatbot that helps a person
 * to keep track of their todos, events and deadlines
 */
public class Jarvis {
    static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    static TaskList taskList1;
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public Jarvis(String filePath) {

        storage = new Storage(filePath);
        try {
            taskList = storage.loadTaskList();
        } catch (DukeException e) {
            ui.showLoadingError();
            taskList = new TaskList();
        }
        ui = new Ui(taskList, storage);
    }

    public void run() {
        ui.run();
    }


    public static void main(String[] args) {

        Jarvis jarvis = new Jarvis("data/task_list.txt");
        jarvis.run();
        // branch Level 8

    }
}
