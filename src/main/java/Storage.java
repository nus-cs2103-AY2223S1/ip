import java.util.ArrayList;
import java.util.List;

public class Storage {
    List<String> taskList = new ArrayList<>();

    public void add(String str) {
        taskList.add(str);
        System.out.println(str);
    }

    public void iterate() {
        int i = 1;
        for(String str : taskList) {
            System.out.println(i + ". " + str);
            i++;
        }
    }
}
