public class Todo {

    private final String title;
    private boolean completed;

    public Todo(String title) {
        this.title = title;
        this.completed = false;
    }
    /**
     * @return String where string is the objects title
     **/
    public String getTitle() {
        return title;
    }
    /**
     * @return boolean where boolean is the object's completion status
     **/
    public boolean isCompleted() {
        return completed;
    }
    /**
     * sets the object to completed
     **/
    public void completeTask() {
        this.completed = true;
    }
    /**
     * sets the object to uncompleted
     **/
    public void unCompleteTask() {
        this.completed = false;
    }
    public String toString() {
        String s = completed ? "[X]" : "[ ]";
        return String.format("[T] %s %s",s , this.title);
    }
}
