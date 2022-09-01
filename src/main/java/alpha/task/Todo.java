package alpha.task;

public class Todo extends Task {
    public Todo(String description, String taskType) {
        super(description, taskType);
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        } else if(obj instanceof Todo) {
            Todo t = (Todo) obj;
            return (t.getDescription().equals(this.getDescription()) && t.getStatus().equals(this.getStatus()) && t.taskType.equals(this.taskType));
        }
        return false;
    }
}
