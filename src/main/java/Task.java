public class Task {
    protected String description;
    protected boolean isDone;
    protected String icon;

    public Task(String description, String icon) {
        this.description = description;
        this.isDone = false;
        this.icon = icon;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getDescription() {
        return this.description;
    }

    public void mark() {
        this.isDone = true;
    }

    public void unMark() {
        this.isDone = false;
    }

    @Override
    public boolean equals(Object o) {
        // If the object is compared with itself then return true
        if (o == this) {
            return true;
        }

        if (!(o instanceof Task)) {

            return false;
        }

        Task c = (Task) o;

        // Compare the data members and return accordingly

        return this.description.equals(c.description);
    }

    @Override
    public String toString() {
        return this.description;
    }

    public String getIcon() {
        return this.icon;
    }
}