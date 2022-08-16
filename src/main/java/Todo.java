class Todo extends Task {
    Todo (int id, String name) {
        super(id, name);
    }

    @Override
    public String toString() {
        String out = super.getId() + ".[";
        if (super.getStatus())
            out += "X";
        else
            out += " ";
        out += "] " + super.toString();
        return out;
    }
}
