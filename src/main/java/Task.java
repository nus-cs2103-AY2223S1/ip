import java.util.*;
public class Task {
    private final String val;
    private final boolean done;

    public Task(String input, String dummy) {
        this.val = input;
        done = false;
    }

    public Task(String input, boolean done, String dummy) {
        this.val = input;
        this.done = done;
    }

    public Task markDone() {
        return new Task(this.val, true, "");
    }

    public Task markUndone() {
        return new Task(this.val, false, "");
    }

    public boolean getDone() {
        return this.done;
    }

    public String getVal() {
        return this.val;
    }

    public String getTiming() {
        return "";
    }

    /*@Override
    public int compareTo(Task other){
        return 1;
    }*/
    @Override
    public String toString(){
        return "";
    }
}
