public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString().substring(0, 4)
                + super.toString().substring(9);
    }

    /*
    @Override
    public String saveString() {
        return "T " + "| " + getStringStatusIcon() + " | " + super.toString().substring(9) + "\n";
    }
    */
}
