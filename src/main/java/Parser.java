import java.util.ArrayList;
import java.util.stream.Stream;

public class Parser {
    public static ArrayList<Integer> getIndicesOfStr1InStr2(String str1, String str2) {
        int len1 = str1.length();
        int len2 = str2.length();

        ArrayList<Integer> result = new ArrayList<>();
        Stream.iterate(0, x -> x + 1).limit(len2 - len1 + 1)
                .filter(i -> str2.substring(i, i + len1).equals(str1))
                .forEach(i -> result.add(i));
        return result;
    }

    public static int getFirstIndexOfStr1InStr2(String str1, String str2) {
        ArrayList<Integer> indices = getIndicesOfStr1InStr2(str1, str2);
        return indices.size() == 0 ? -1 : indices.get(0);
    }

    public static Command parseCommand(String s) {
        s = s.trim();
        String[] words = s.split(" ");
        Action action = Action.getAction(words[0]);
        switch (action) {
            case GREET:
                return new Command(Action.GREET);
            case EXIT:
                return new Command(Action.EXIT);
            case MARK:
                String idMark = s.substring(Action.MARK.toString().length()).trim();
                return new Command(Action.MARK, idMark);
            case UNMARK:
                String idUnmark = s.substring(Action.UNMARK.toString().length()).trim();
                return new Command(Action.UNMARK, idUnmark);
            case LIST:
                return new Command(Action.LIST);
            case ADD:
                return new Command(Action.ADD, s);
            default:
                return null;
        }
    }

}
