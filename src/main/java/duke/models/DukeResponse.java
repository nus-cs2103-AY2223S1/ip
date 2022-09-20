package duke.models;

public class DukeResponse {
    private boolean isExit = false;
    private String content;

    public DukeResponse(String content, boolean isExit) {
        this.isExit = isExit;
        this.content = content;
    }

    public DukeResponse(String content) {
        this.content = content;
    }

    public boolean isExit() {
        return this.isExit;
    }

    public String getContent() {
        return this.content;
    }
}
