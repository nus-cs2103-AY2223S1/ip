public class Event extends Task {
    protected String by;

    public Event(String description, String by, boolean done) {
        super(description, 'E', done);
        this.by = by;
    }

    public Event(String description, String by) {
        super(description, 'E', false);
        this.by = by;
    }

    public static Event fromSaveString(String saveString) throws RuntimeException {
        String[] splitSaveString = saveString.split("(\",\")|(\",)|(,\")|\"");
        if(splitSaveString.length != 3) {
            throw new RuntimeException("Tried to read unexpected save data.");
        }
        String description = splitSaveString[1];
        String by = splitSaveString[2];
        boolean done = splitSaveString[0].endsWith("1");
        return new Event(description, by, done);
    }

    @Override
    public String toString() {
        return super.toString() + " (" + by.replaceFirst(" ", ": ") + ")";
    }

    @Override
    public String saveData() {
        return super.saveData() + String.format(",\"%s\"", this.by);
    }
}
