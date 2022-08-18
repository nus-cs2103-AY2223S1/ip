public class ToDo extends Task{

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        String parStr = super.toString();
        return String.format("[T]%s", parStr);
    }
}
