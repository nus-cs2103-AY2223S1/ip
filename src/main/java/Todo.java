public class Todo extends Task {

    public Todo(ParsedData parsedData) {
        super(parsedData);
    }

    @Override
    public String getTypeIcon() {
        return "[T]";
    }

    @Override
    public String getTypeLetter() {
        return "T";
    }

    @Override
    public String getDuring() {
        return "";
    }

    @Override
    public String getTime() {
        return "";
    }

    @Override
    public String toString() {
        String result = this.getTypeIcon() + this.getStatusIcon() + this.description;
        return result;
    }
}
