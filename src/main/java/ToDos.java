public class ToDos extends Task{

    public ToDos(String description){
        super(description);
    }

    @Override
    public String getDescription() {
        return "[T]" + super.getDescription();
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
