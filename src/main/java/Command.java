import java.util.ArrayList;

public class Command {
    public static ArrayList<String> commandList;

    public Command() {
        commandList = new ArrayList<>();
    }

    public void addCommand(String input) {
        commandList.add(input);
    }

    public void printList() {
        int ListLength = commandList.size();
        for (int i = 0; i < ListLength; i++) {
            System.out.println((i + 1) + ". " + commandList.get(i));
        }
    }
}
