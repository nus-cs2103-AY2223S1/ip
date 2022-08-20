public class Deadline extends Task {

    public Deadline(String description) {
        super(description);
    }

    // Adapted from
    // https://stackoverflow.com/questions/14316487/java-getting-a-substring-from-a-string-starting-after-a-particular-character

    protected String by = description.substring(description.lastIndexOf("/") + 3);

    @Override
    public String toString() {
        return "[D]" + super.toString().substring(0, 4) + getSubstring() + "(by:" + by + ")";
    }

    // To return substring before "/"
    // Adapted from
    // https://stackoverflow.com/questions/7683448/in-java-how-to-get-substring-from-a-string-till-a-character-c
    private String getSubstring() {
        int index = description.indexOf("/");
        if (index != - 1) {
            return description.substring(9, index);
        }
        return null;
    }

    /*
    @Override
    public String saveString() {
        return "D " + "| " + getStringStatusIcon() + " | " + getSubstring() + "|" + by + "\n";
    }
    */
}

