public class Event extends Task {

    public Event(String description) {
        super(description);
    }

    protected String at = description.substring(description.lastIndexOf("/") + 3);

    @Override
    public String toString() {
        return "[E]" + super.toString().substring(0, 4) + getSubstring() +  "(at:" + at + ")";
    }

    private String getSubstring() {
        int index = description.indexOf("/");
        if (index != - 1) {
            return description.substring(6, index);
        }
        return null;
    }

    /*
    @Override
    public String saveString() {
        return "D " + "| " + getStringStatusIcon() + " | " + getSubstring() + "|" + at + "\n";
    }
    */
}
