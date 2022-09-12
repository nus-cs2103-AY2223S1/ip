package duke.tasks;

import duke.TaskList;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public boolean isCategory(TaskList.Categories c) {
        return c == TaskList.Categories.TODO;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public HashMap<String, String> getInfoPair() {
        return new HashMap<String, String>() {{
            put("Type", "TODO");
            put("Description", description);
        }};
    }

    @Override
    public JSONObject toJsonObject() {
        JSONObject object = new JSONObject();
        object.put("type", "Todo");
        object.put("description", this.description);
        object.put("done", this.isDone);
        return object;
    }

    public static Todo fromJSONObject(JSONObject jsonObject) {
        Todo t = new Todo(jsonObject.getString("description"));
        t.isDone = jsonObject.getBoolean("done");
        return t;
    }
}
