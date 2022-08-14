import java.util.ArrayList;

public class ChatList extends ArrayList<String> {
    private String getEntry(int entry) {
        return entry + ". " + this.get(entry - 1);
    }

    @Override
    public String toString() {
        String result = "";
        for (int entry = 1; entry < this.size() + 1; entry++) {
            result += "\t" + this.getEntry(entry) + "\n";
        }
        return result;
    }
}
