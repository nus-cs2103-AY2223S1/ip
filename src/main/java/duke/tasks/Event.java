package duke.tasks;

import org.json.JSONObject;
import duke.parser.Parser;

import java.time.LocalDate;

public class Event extends Task {
    protected LocalDate at;

    public Event(String description, String at) {
        super(description);
        this.at = Parser.parseStringtoDate(at);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + Parser.parseDatetoString(at) + ")";
    }

    @Override
    public JSONObject toJSONObject() {
        JSONObject object = new JSONObject();
        object.put("type", "Event");
        object.put("description", this.description);
        object.put("done", this.isDone);
        object.put("at", Parser.parseDatetoString(this.at));
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