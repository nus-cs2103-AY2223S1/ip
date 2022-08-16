class Deadline extends Task {

    String deadline;

    Deadline (int id, String name, String deadline) {
        super(id, name);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        String out = super.getId() + ".[D][";
        if (super.getStatus())
            out += "X";
        else
            out += " ";
        out += "] " + super.toString() + "(by : " + deadline + ")";
        return out;
    }


}
