package duke;

class Todo extends Task {
    Todo (String name) {
        super(name);
    }
    Todo (String name, boolean done) {
        super(name, done);
    }

    @Override
    public String toString() {
        String out = "[T][";
        if (super.getStatus()) {
            out += "X";
        } else {
            out += " ";
        }
        out += "] " + super.toString();
        return out;
    }
}
