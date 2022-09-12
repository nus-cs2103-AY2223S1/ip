package duke.tasks;

import duke.TaskList;
import org.json.JSONObject;
import duke.parser.Parser;

import java.time.LocalDate;

public class Deadline extends Task {
    protected LocalDate by;

    public Deadline(String description, String by) {
        super(description);
        this.by = Parser.parseStringtoDate(by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + Parser.parseDatetoString(by) + ")";
    }

    @Override
    public JSONObject toJsonObject() {
        JSONObject object = new JSONObject();
        object.put("type", "Deadline");
        object.put("description", this.description);
        object.put("done", this.isDone);
        object.put("by", Parser.parseDatetoString(this.by));
        return object;
    }

    public static Event fromJSONObject(JSONObject jsonObject) {
        Event e = new Event(
                jsonObject.getString("description"),
                jsonObject.getString("by"));

        e.isDone = jsonObject.getBoolean("done");
        return e;
    }

    public boolean isCategory(TaskList.Categories c) {
        return (c == TaskList.Categories.DEADLINE);
    }
}