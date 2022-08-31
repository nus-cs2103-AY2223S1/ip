package ip;

class ToDo extends Task {

    ToDo(String description) {
        this(description, false);
    }

    protected ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        String parStr = super.toString();
        return String.format("[T]%s", parStr);
    }
}
