public class ToDo extends Task {

    public ToDo(String taskDescription) {
        super(taskDescription);
    }

    public static ToDo ToDoFromData(String taskFromData, boolean isDone) {
        ToDo result = new ToDo(taskFromData);
        result.isDoneSetter(isDone);
        return result;
    }

    @Override
    public String getTypeIcon() {
        return "[T]";
    }

    @Override
    public String toFile() {
        return "T" + "|" + super.toFile();
    }
}
