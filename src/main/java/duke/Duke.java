package duke;

public class Duke {
    public static void main(String[] args) {
        Ui myUi = new Ui();
        myUi.startMessage();
        Storage myStorage = new Storage();
        TaskList myTasks = new TaskList(myStorage.setUp());
        myUi.getUserCommand(myTasks);
    }
}

