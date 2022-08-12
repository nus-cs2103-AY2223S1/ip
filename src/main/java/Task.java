public abstract class Task implements SetStatus {
    private final String title;
    private final boolean status; 

    Task(String title, boolean status) {
        this.title = title;
        this.status = status;
    }

    String getTitle() {
        return this.title;
    }

    boolean getStatus() {
        return this.status;
    }

    @Override
    public String toString() {
        return "[" + (this.status ? "X" : " ") + "] " + this.title;
    }
}
