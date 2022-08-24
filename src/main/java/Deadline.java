public class Deadline extends Task {
    private final String doBy;

    public Deadline(String name, String doBy) {
        super(name);
        this.doBy = doBy;
    }

    public Deadline(String name, String doBy, boolean isDone) {
        super(name, isDone);
        this.doBy = doBy;
    }

    public static Deadline fromString(String inputString) {
        boolean isDone = inputString.charAt(4) == 'X';
        String name = inputString.substring(7, inputString.indexOf("(by"));
        String doBy = inputString.substring(inputString.indexOf("(by: ") + 5, inputString.length() - 1);
        return new Deadline(name, doBy, isDone);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + this.doBy + ")";
    }
}
