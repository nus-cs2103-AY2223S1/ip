public class Deadlines extends Task{
    String by;
    String type;
    Deadlines(String name, String by){
        super(name);
        this.by = by;
        this.type = "[D]";
    }
    @Override
    public String toString() {
        return this.type + super.getStatus() + "(" + this.by + ")";
    }
}
