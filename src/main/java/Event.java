public class Event extends Task {
    String atWhen;

    public Event(String userInput) {
        super(userInput.substring(6, userInput.indexOf("/at")));
        atWhen = userInput.substring(userInput.indexOf("/at") + 4);
    }

    @Override
    public String getStatus() {
        return String.format("[E]%s (at: %s)", super.getStatus(), this.atWhen);
    }
}
