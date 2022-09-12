public class ToDo extends Task {


    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String saveToDisk() {
        String output = "";
        String taskStatus = (this.isDone) ? "1" : "0";
        output += "T" + SEPARATOR + taskStatus + SEPARATOR + this.description + "\n";
        return output;
    }
}