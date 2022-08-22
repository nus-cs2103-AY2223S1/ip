public class ToDo extends Task{

    public ToDo(String description) {
        this.description = description;
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[T] " + "[" + this.getStatusIcon() + "] " + this.description;
    }

}
