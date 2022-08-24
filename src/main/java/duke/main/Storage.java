package duke.main;

import duke.task.Formatting;
import duke.task.Task;

import java.util.ArrayList;

public class Storage {
    protected ArrayList<Task> arr;
    protected String filePath;

    private Formatting f = new Formatting();

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws DukeException {
        this.arr = FileManipulation.read(filePath);
        return this.arr;
    }

    public void overwrite() {
        FileManipulation.overwrite(this.filePath, this.f.apply(this.arr));
    }
}
