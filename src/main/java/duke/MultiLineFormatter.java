package duke;

public class MultiLineFormatter {
    private final StringBuilder fullMessage = new StringBuilder();

    public void add(String message) {
        fullMessage.append(message);
    }

    public String getFullMessage() {
        return fullMessage.toString();
    }
}
