public class ToDos extends Task{
    private String type;
    ToDos(String name) {
        super(name);
        this.type = "[T]";
        //test
    }
    @Override
    public String toString() {
        return this.type + super.getStatus();
    }
}
