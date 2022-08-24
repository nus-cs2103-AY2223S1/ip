public class ToDo extends Task { 
    public ToDo (String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String saveFormat() {
        if (this.isDone) {
            return "T | 1 | " + this.description;
        } else {
            return "T | 0 | " + this.description;
        }
    }
} 
