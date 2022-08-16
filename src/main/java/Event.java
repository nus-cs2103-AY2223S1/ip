public class Event extends Task {
    String atWhen = null;

    public Event() {
        super();
    }

    public void addName(String userInput) throws DukeException {
        if (userInput.length() <= 6) {
            throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.");
        }
        int index = userInput.indexOf("/at") - 1;
        if (index <= 5) {
            throw new DukeException("☹ OOPS!!! Please indicate when the event is happening with '/at'.");
        }
        super.addName(userInput.substring(6, index));
        this.atWhen = userInput.substring(index + 5);
    }

    @Override
    public String getStatus() {
        return String.format("[E]%s (at: %s)", super.getStatus(), this.atWhen);
    }
}
