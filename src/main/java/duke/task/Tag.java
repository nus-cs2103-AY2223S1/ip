package duke.task;

public class Tag {
    private String name;

    public Tag(String name) {
        this.name = name;
    }

    @Override
    public String toString(){
        return "#" + this.name;
    }

    public String getName() {
        return this.name;
    }
}
