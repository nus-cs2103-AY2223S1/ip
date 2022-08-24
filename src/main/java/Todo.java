public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String getLetterTag() {
        return "T";
    }

    @Override
    public String getAdditionalInfo() {
        return "";
    }


    @Override
    public String toString() {
        return "[" + this.getLetterTag() + "][" + this.getStatusIcon() + "] " + this.description;
    }

}