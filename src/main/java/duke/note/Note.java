package duke.note;

public class Note {
    private static final char DEFAULT_TAG = 'N';

    private String description;

    public Note(String description) {
        this.description = description;
    }

    public static Note fromSaveString(String saveString) {
        String[] splitSaveString = saveString.split("(\",\")|(\",)|(,\")|\"");
        assert splitSaveString[0].startsWith("N") : "Save data is not a note.";
        String description = splitSaveString[1];
        return new Note(description);
    }

    public String toSaveData() {
        return String.format("%s,\"%s\"", DEFAULT_TAG, description);
    }

    public boolean descriptionContains(String toFind) {
        return description.contains(toFind);
    }

    @Override
    public String toString() {
        return description;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Note) {
            Note other = (Note) obj;
            return description.equals(other.description);
        }
        return false;
    }
}
