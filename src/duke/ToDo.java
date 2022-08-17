package duke;

public class ToDo extends Task{

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return this.getStatusIcon() + " " + super.description;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ToDo) {
            ToDo x = (ToDo) obj;
            if (this.description == null || x.description == null) {
                return false;
            }
            return this.description.equals(x.description);
        }

        return false;
    }
}
