public class ToDo extends Task {
    public ToDo(String title) {
        super(title);
    };

    @Override
    public String print() {
        return String.format("[T]%s", super.print());
    }
}
