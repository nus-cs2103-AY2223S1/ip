import java.io.FileWriter;
import java.io.File;
import java.io.IOException;

public class Task {
    protected String desc;
    protected boolean isDone;

    public Task(String desc) {
        this.desc = desc;
        isDone = false;
    }

    public String getStatusIcon() {
        return this.isDone ? "X" : " ";
    }

    public void complete() {
        this.isDone = true;
        System.out.println("Nice I have marked the following task as done:");
        System.out.println(this);
    }

    public void undo() {
        this.isDone = false;
        System.out.println("Ok, I have marked this task as not done yet:");
        System.out.println(this);
    }

    public void add() {
        Tracker.list.add(this);
        System.out.println("Got it. I've added this task:");
        writeToTaskList(this.toString(), Tracker.filePath);
        System.out.println(this);
        System.out.println("Now you have " + Tracker.list.size() + " tasks in the list");
    }

    public void writeToTaskList(String s, String filePath) {
        try {
            File file = new File(filePath);
            FileWriter writer = new FileWriter(filePath, true);
            if (file.length() != 0) {
                writer.write(System.getProperty("line.separator"));
            }
            writer.write(s);
            writer.close();
        } catch (IOException e){
            System.out.println("Oops file not found");
        }

    }


    @Override
    public String toString() {
        return "[" + getStatusIcon() +"] " + this.desc;
    }
}
