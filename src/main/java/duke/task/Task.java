package duke.task;

public class Task {
    private String content;
    private boolean done;

    public Task(String content) {
        this.content = content;
        this.done = false;
    }

    public void markDone() {
        this.done = true;
    }

    public void unmarkDone() {
        this.done = false;
    }

    public String getContent() {
        return this.content;
    }

    /**
     * Check if a task description contains given keyword.
     * @param keyword
     * @return boolean value
     */
    public boolean match(String keyword) {
        return this.content.contains(keyword);
    }

    public String isDoneString() {
        return this.done == true ? "1" : "0";
    }

    @Override
    public String toString() {
        return "[" + (this.done ? "X" : " ") + "] " + this.content;
    }

    public String toMemoryString() {
        return isDoneString() + " | " + content;
    }
}
