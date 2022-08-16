import java.util.ArrayList;

public class DukeList {
    private final ArrayList<String> listItems = new ArrayList<>();

    public DukeList() {

    }

    public String add(String item) {
        listItems.add(item);
        return "added: " + item;
    }

    public String toString() {
        StringBuilder listItemsStrBuilder = new StringBuilder();
        for (int i = 0; i < listItems.size(); i++) {
            listItemsStrBuilder.append(i + 1).append(". ").append(listItems.get(i));

            if (i != listItems.size() - 1) {
                listItemsStrBuilder.append("\n");
            }
        }
        return listItemsStrBuilder.toString();
    }
}