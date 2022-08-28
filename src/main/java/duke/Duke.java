package duke;

import java.io.IOException;
import duke.functions.*;

public class Duke {

    private Ui newUser;
    private TaskList userTaskList;
    private Storage data;

    public Duke(String filePath) throws IOException {
        this.newUser = new Ui();
        this.userTaskList = newUser.getTaskList();
        this.data = new Storage(this.userTaskList, filePath);
    }

    public static void main(String[] args) throws IOException {
        new Duke("duke.txt");
    }

}

