package alpha.task;

import alpha.Ui;
import alpha.command.Mark;

public abstract class Task {

    Ui uI = new Ui();
    protected String description;
    protected boolean status;

    protected String taskType;
    public Task(String description, String taskType) {
        this.description = description;
        this.status = false;
        this.taskType = taskType;
    }
    public String getDescription() {
        return description; // mark done alpha.task with X
    }
    public String getStatus() {
        return (status ? "X" : " "); // mark done alpha.task with X
    }

    public String getTaskType() {
        return taskType;
    }
    public void changeStatus(boolean status) {
        this.status = status;
    }

    public String toString() {
        return String.format(uI.ANSI_BLUE + "[ %s ] [ %s ] %s", this.getTaskType(), this.getStatus(), this.getDescription() + uI.ANSI_RESET);
    }

    //@Override
    /*public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        } else if(obj instanceof Task) {
            Task t = (Task) obj;
            return (t.getDescription().equals(this.getDescription()) && t.getStatus().equals(this.getStatus()) && t.getTaskType().equals(this.getTaskType()));
        }
        return false;
    }*/
}
