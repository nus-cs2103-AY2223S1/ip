/*
This class encapsulates the idea of a to do
 */
public class ToDo extends Task {

    public ToDo(String description, boolean status) {
        super(description, status);
    }

    @Override
    public String getDescription() {
        String status = super.getStatus() ? "T" : "F";
        return "T | " + status + " | " + super.toString() +  "\n";
    }

    @Override
    public String toString() {
        return "[T]" + super.getStatusIcon() + " " + super.toString();
    }
}