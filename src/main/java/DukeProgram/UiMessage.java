package DukeProgram;

public class UiMessage {
    private final String message;

    public UiMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
