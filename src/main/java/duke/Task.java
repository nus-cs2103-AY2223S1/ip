package duke;

import java.time.LocalDateTime;

public class Task{

    private String status;
    private String type;
    private String name;
    private LocalDateTime time;

    public Task() {
    };

    public Task(String name) {
        this.status = "[ ]";
        this.name = name;
    }

    public String getType() {
        return this.type;
    }

    public String getName() {
        return this.name;
    }

    public String getStatus() {
        return this.status;
    }

    public LocalDateTime getTime() {
        return this.time;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void print() {
        TaskList t = new TaskList();
        System.out.println(Duke.LINE);
        for (int i = 0, j = 1; i < Duke.count; i++, j++) {
            System.out.println(j + ". " + t.getList().get(i).status + t.getList().get(i).name );
        }
        System.out.println(Duke.LINE + "\n");
    }

    public void list() {
    };

    public String toString() {
        return " ";
    };

    public String description() {
        return "";
    }
}
