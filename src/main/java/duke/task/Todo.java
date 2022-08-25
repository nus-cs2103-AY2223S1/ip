package duke.task;

import duke.Task;

public class Todo extends Task {
    public Todo(String item){
        this.setItem(item);
    }

    public String getTask() {
        return "[T] " + this.getStatusIcon() + " " + this.getItem();
    }

    public String getFileLine() {
        return "[T]" + "##" + this.getStatusIcon() + "##" + this.getItem();
    }
}
