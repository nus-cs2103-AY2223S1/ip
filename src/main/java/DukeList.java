import java.util.ArrayList;

public class DukeList {
    private final ArrayList<String> list = new ArrayList<>();

    public void add(String str) {
        this.list.add(str);
    }

    @Override
    public String toString() {
        String res = "";
        for (int i = 0; i < this.list.size(); i++) {
            String nextListItem = String.format("%d. %s", i + 1, this.list.get(i));
            res += TextFormatter.formatLine(nextListItem);
        }
        return res;
    }
}

