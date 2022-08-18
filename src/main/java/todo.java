public class todo extends Task {
    public todo(String desc) {
        super(desc);
    }

    @Override
    public String toString() {
        return "[T][" + super.getStatusIcon() + "] " + super.description;
    }
}
