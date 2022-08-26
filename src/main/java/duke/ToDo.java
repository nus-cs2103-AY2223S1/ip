package duke;

<<<<<<< HEAD:src/main/java/duke/ToDos.java
public class ToDos extends Task{
    private String type;

    ToDos(String name, boolean isDone) {
=======
public class ToDo extends Task{
    private String type;
    ToDo(String name, boolean isDone) {
>>>>>>> branch-A-CodingStandard:src/main/java/duke/ToDo.java
        super(name, isDone);
        this.type = "[T]";
    }

    @Override
    public String toString() {
        return this.type + super.getStatus();
    }
}

