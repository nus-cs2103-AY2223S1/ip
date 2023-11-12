package Models;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;

public class Todo {
    protected String title;
    protected boolean isCompleted;

    /**
     * Class that encapsulates a Todo object
     * It has several subclasses including Deadline and Event
     * @param title title encapsulates the title of the todo object
     * @param isCompleted is a boolean value to keep track of the marked/unmarked status
     */
    public Todo(String title, boolean isCompleted) {
        this.title = title;
        this.isCompleted = isCompleted;
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
        return isCompleted;
    }

    public void completeTask() {
        this.isCompleted = true;
    }

    public void unCompleteTask() {
        this.isCompleted = false;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Objects individually write themselves into the file,
     * this allows them to individually handle their written form
     *
     * @param writer the writers specifications, IE what file to write into
     * @throws IOException Exception happens when writer cannot be accessed
     */
    public void writeToFile(FileWriter writer) throws IOException {
        writer.write(String.format("T;%s;%d\n", this.title, this.isCompleted ? 1 : 0));
    }
    public String toString() {
        String s = isCompleted ? "[X]" : "[ ]";
        return String.format("[T] %s %s",s , this.title);
    }
}
