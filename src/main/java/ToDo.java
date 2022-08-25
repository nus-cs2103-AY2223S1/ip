public class ToDo extends Task {
    protected ToDo(String name) {
        super(name, null);
        type = "T";
    }

    @Override
    public String stringType() {
        return "todo";
    }

    @Override
    public String getDateTime() {
        return "";
    }
}
