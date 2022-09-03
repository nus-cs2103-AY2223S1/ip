import java.util.List;

public class Parser {

    public Parser() {

    }

    public void listTasks(List<Task> tasks, int curr) {
        for (int i = 0; i < curr; i++) {
            System.out.println(tasks.get(i));
        }
    }

    
}

