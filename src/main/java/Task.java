import java.time.LocalDateTime;
import java.util.Scanner;

public class Task {
    private String status;
    private String type;
    private String name;

    private LocalDateTime time;

    public Task() {};

    public Task(String name) {
        status = "[ ]";
        this.name = name;
    }

    public String getType() {
        return type;
    }
    public String getName() {
        return name;
    }

    public  String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public void print() {
        TaskList t = new TaskList();
        System.out.println(
                Duke.line);

        for (int i = 0, j = 1; i < Duke.count; i++, j++) {

            System.out.println(j + ". " + t.getList().get(i).status + t.getList().get(i).name );
        }
        System.out.println(
                Duke.line + "\n"
        );

    }
    public void list() {};

    public String toString() {
        return " ";
    };

    public LocalDateTime getTime() {
        return time;
    }

    public String description() {
        return "";
    }
}
