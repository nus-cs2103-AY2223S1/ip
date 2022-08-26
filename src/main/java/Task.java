public class Task {
    String name;
    boolean isDone;
    String type = "[T]";

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }


    public String getStatus() {
        if (isDone) {
            return "[X]";
        } else {
            return "[ ]";
        }
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public void markDone() {
        this.isDone = true;
    }

    public void markUndone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return this.getStatus() + ' ' + this.getName();
    }


}
