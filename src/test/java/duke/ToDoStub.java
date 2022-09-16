package duke;

import duke.task.Task;
import duke.task.TaskType;

class ToDoStub extends Task {
    ToDoStub() {
        super("Test todo for Duke !", TaskType.TODO);
    }

    @Override
    public String getBy() {
        return "";
    }

    @Override
    public String toString() {
        return String.format("%s%s", "[T]", "[ ] Test todo for Duke !");
    }
}
