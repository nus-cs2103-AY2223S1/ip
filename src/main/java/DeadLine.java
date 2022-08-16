public class DeadLine extends Task {
    String byWhen;

    public DeadLine(String userInput) {
        super(userInput.substring(9, userInput.indexOf("/by")));
        byWhen = userInput.substring(userInput.indexOf("/by") + 4);
    }

    @Override
    public String getStatus() {
        return String.format("[D]%s (by: %s)", super.getStatus(), this.byWhen);
    }
}
