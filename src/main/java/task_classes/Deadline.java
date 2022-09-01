package task_classes;

import org.json.JSONObject;

public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public JSONObject toJSONObject() {
        JSONObject object = new JSONObject();
        object.put("type", "Deadline");
        object.put("description", this.description);
        object.put("done", this.isDone);
        object.put("by", this.by);
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