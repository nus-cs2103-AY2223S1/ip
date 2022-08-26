public class EmptyTask extends Task {
    EmptyTask() {
        super("");
    }

    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public String getFormattedString() {
        return "";
    }
}
