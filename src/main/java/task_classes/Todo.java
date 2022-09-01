package task_classes;

import org.json.JSONObject;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public JSONObject toJSONObject() {
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
