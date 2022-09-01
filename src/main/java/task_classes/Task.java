package task_classes;

import org.json.JSONObject;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    public void setDone() {
        this.isDone = true;
    }

    public void setNotDone() {
        this.isDone = false;
    }

    public abstract JSONObject toJSONObject();

    public static Task fromJSONObject(JSONObject jsonObject) {
        switch (jsonObject.getString("type")) {
            case "Todo":
                return Todo.fromJSONObject(jsonObject);
            case "Event":
                return Event.fromJSONObject(jsonObject);
            case "Deadline":
                return Deadline.fromJSONObject(jsonObject);
        }
        return null;
    }


}
