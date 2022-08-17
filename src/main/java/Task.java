public class Task {
    private String title;
    private Boolean isDone;

    public Task(String title) {
        this.title = title;
        this.isDone = false;
    }

    public void markIsDone() {
        this.isDone = true;
    }

    public void unmarkIsDone() {
        this.isDone = false;
    }

    public Boolean getStatus() {
        return isDone;
    }

    public String getTitle() {
        return title;
    }
}
