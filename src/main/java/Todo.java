import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Todo {

    protected final String title;
    protected boolean completed;

    public Todo(String title, boolean completed) {
        this.title = title;
        this.completed = completed;
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

    public void writeToFile(FileWriter writer) throws IOException {
        writer.write(String.format("T;%s;%d\n", this.title, this.completed ? 1 : 0));
    }
    public String toString() {
        String s = completed ? "[X]" : "[ ]";
        return String.format("[T] %s %s",s , this.title);
    }
}
