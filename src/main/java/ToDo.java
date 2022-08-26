public class ToDo extends Task{

    public ToDo(String description) {
        super(description);
    }

    @Override
    protected String getStorageString() {
        String parStr = super.getStorageString();
        return String.format("%s|%s|", "T", parStr);
    }

    @Override
    public String toString() {
        String parStr = super.toString();
        return String.format("[T]%s", parStr);
    }
}
