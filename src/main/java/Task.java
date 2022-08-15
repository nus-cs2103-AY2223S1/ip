public class Task {
    String name;
    boolean isDone;

    public Task(String name) {
        setName(name);
        setIsDone(false);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    public String getName() {
        return this.name;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    @Override
    public String toString() {
        return (isDone ? "[X]" : "[ ]") + " " + this.getName();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof Task) {
            Task that = (Task) obj;
            if (that == null) {
                return false;
            } else {
                return this.getName().equals(that.getName()) && this.isDone == that.getIsDone();
            }
        }
        return false;
    }
}
