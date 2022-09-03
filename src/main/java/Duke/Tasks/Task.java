package Duke.Tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Task
 * Abstract Class to represent takes from users
 */
public abstract class Task {
    protected String discription;
    protected boolean isDone;


    public Task(String discription) {
        this(discription, false);
    }

    public Task(String discription, boolean isDone){
        this.discription = discription;
        this.isDone = isDone;
    }


    public void setIsDone(boolean isDone) { this.isDone = isDone; }

    public boolean getIsDone() { return this.isDone; }

    public String getDiscription() { return this.discription; }

    public String outputIsDone() { return String.format("[%s]", isDone ? "X":" "); }

    public boolean searchKeyWord(String keyWord) { return this.discription.contains(keyWord); }

    @Override
    public String toString() { return this.outputIsDone() + this.getDiscription(); }

    public abstract String save();

    public abstract String getTaskType();

    public abstract LocalDateTime getDateTime();

}
