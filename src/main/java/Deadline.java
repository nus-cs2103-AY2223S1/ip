public class Deadline extends Task{
    private String by;

    public Deadline(String name, boolean completed, String by){
        super(name,completed);
        this.by = by;
    }

    @Override
    public Task toggleCompleted(){
        return new Deadline(getName(),!isCompleted(),by);
    }

    @Override
    public String toString(){
        return String.format("[D][%s] %s (by: %s)",checkMarked(),getName(),by);
    }
}
