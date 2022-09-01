package alpha.task;

import alpha.Ui;

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
        return description;
    }

    public String getStatus() {
        return (status ? "X" : " ");
    }

    public String getTaskType() {
        return taskType;
    }

    public void changeStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return String.format(uI.getANSI_CODE("ANSI_BLUE") + "[ %s ] [ %s ] %s", this.getTaskType(),
                this.getStatus(), this.getDescription() + uI.getANSI_CODE("ANSI_RESET"));
    }
}
