public class Deadline extends Task {
    protected String by;

    Deadline(String description, String by, boolean done) {
        super(description, 'D', done);
        this.by = by;
    }

    public Deadline(String description, String by) {
        super(description, 'D', false);
        this.by = by;
    }

    public static Deadline fromSaveString(String saveString) throws RuntimeException {
        String[] splitSaveString = saveString.split("(\",\")|(\",)|(,\")|\"");
        if(splitSaveString.length != 3) {
            throw new RuntimeException("Tried to read unexpected save data.");
        }
        String description = splitSaveString[1];
        String by = splitSaveString[2];
        boolean done = splitSaveString[0].endsWith("1");
        return new Deadline(description, by, done);
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
