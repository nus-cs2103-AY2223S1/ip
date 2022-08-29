package Dan;

public class ToDo extends Task {
    private static final String ICON = "T";

    ToDo(String description) {
        super(description);
    }

    @Override
    public String toDataString(String separator) {
        return String.format("%s%s", ICON, super.toDataString(separator));
    }

    @Override
    public String toString() {
        return String.format("[%s]%s", ICON, super.toString());
    }
}
