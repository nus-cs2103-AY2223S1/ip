package duke.tasks;

import duke.TaskList;
import org.json.JSONObject;
import duke.parser.JsonParsable;

import java.util.HashMap;

public abstract class Task implements JsonParsable {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public abstract boolean isCategory(TaskList.Categories c);
    /**
     * Get task description.
     * @return a string representing the task description.
     */
    public String getDescription() {
        return this.description;
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

    public abstract HashMap<String, String> getInfoPair();
    public abstract JSONObject toJsonObject();

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
