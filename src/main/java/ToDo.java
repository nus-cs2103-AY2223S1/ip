public class ToDo extends Task {
    public ToDo(String name) {
        super(name);
    }

    @Override
    public String getStatus() {
        return String.format("[T]%s", super.getStatus());
    }
}
