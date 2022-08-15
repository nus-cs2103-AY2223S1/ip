import java.util.ArrayList;

public class DukeControl {
    ArrayList<String> arrayList;

    public DukeControl() {
        arrayList = new ArrayList<>();
    }

    public void evaluate(String input) {
        if (input.equals("list")) {
            list();
        } else {
            add(input);
        }
    }

    public void echo(String input) {
        System.out.println(input);
    }

    public void list() {
        for (int i = 0; i < arrayList.size(); i++) {
            System.out.println(String.format("%d. %s", i + 1, arrayList.get(i)));
        }
    }

    public void add(String task) {
        arrayList.add(task);
        System.out.println(String.format("added: %s", task));
    }
}
