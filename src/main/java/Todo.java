public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Rewrites this to do task into the save file format
     * @return String to be stored in save file
     */
    @Override
    public String saveData() {
        String marked = this.isDone ? "1" : "0";
        return "T | " + marked + " | " + this.description;
    }
}