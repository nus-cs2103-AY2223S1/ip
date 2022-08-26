public class ToDoTask extends Task{

<<<<<<< HEAD
=======
    /**
     * Constructs for an todo task.
     * @param description A description of the todo
     */
>>>>>>> 038da11 (Refactor using OOP)
    public ToDoTask(String description) {
        super(description);
    }

<<<<<<< HEAD
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
=======
    /**
     * Encodes the todo for storage.
     * Format of the todo is TASK_TYPE|IS_MARKED|DESCRIPTION.
     * @return String encoding of the todo.
     */
    public ToDoTask(String description, boolean isMarked) {
        super(description, TaskType.TODO, isMarked);
>>>>>>> 038da11 (Refactor using OOP)
    }
}
