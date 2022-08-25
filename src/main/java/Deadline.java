public class Deadline extends Task {


    protected Deadline(String name, String dueDate) {
        super(name, dueDate);
        type = "D";
    }

    @Override
    public String stringType() {
        return "deadline";
    }

}
