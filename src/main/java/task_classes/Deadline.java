package task_classes;

import org.json.JSONObject;
import utils.InputParser;

import java.time.LocalDate;

public class Deadline extends Task {
    protected LocalDate by;

    public Deadline(String description, String by) {
        super(description);
        this.by = InputParser.parseStringtoDate(by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + InputParser.parseDatetoString(by) + ")";
    }

    @Override
    public JSONObject toJSONObject() {
        JSONObject object = new JSONObject();
        object.put("type", "Deadline");
        object.put("description", this.description);
        object.put("done", this.isDone);
        object.put("by", InputParser.parseDatetoString(this.by));
        return object;
    }

    public static Event fromJSONObject(JSONObject jsonObject) {
        Event e = new Event(
                jsonObject.getString("description"),
                jsonObject.getString("by"));

        e.isDone = jsonObject.getBoolean("done");
        return e;
    }
}