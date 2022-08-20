import java.util.ArrayList;
import java.util.function.Function;
public class Formatting implements Function<ArrayList<Task>, String> {
    public String apply(ArrayList<Task> arr) {
        String s = "";
        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i) instanceof ToDo) {
                s += "T | " + checkMark(arr.get(i)) + " | " + arr.get(i).description
                + "\n";
            } else if (arr.get(i) instanceof Event) {
                s += "E | " + checkMark(arr.get(i)) + " | " + arr.get(i).description
                        + " | " + ((Event) arr.get(i)).at + "\n";
            } else {
                s += "D | " + checkMark(arr.get(i)) + " | " + arr.get(i).description
                        + " | " + ((Deadline) arr.get(i)).by + "\n";
            }
        }
        return s;
    }

    private String checkMark(Task t) {
        return t.isDone ? "1" : "0";
    }

}
