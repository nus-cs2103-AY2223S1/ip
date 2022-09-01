package task_classes;

import org.json.JSONObject;

public class Event extends Task {
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

    @Override
    public JSONObject toJSONObject() {
        JSONObject object = new JSONObject();
        object.put("type", "Event");
        object.put("description", this.description);
        object.put("done", this.isDone);
        object.put("at", this.at);
        return object;
    }


    public static Event fromJSONObject(JSONObject jsonObject) {
        Event e = new Event(
                jsonObject.getString("description"),
                jsonObject.getString("at"));

        e.isDone = jsonObject.getBoolean("done");
        return e;
    }
}