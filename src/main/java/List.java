import java.util.ArrayList;

public class List {

    public static ArrayList<String> dir = new ArrayList<String>();

    public List(String cmd) {
        if (!cmd.equals("list")) {
            dir.add(cmd);
        }
    }


    public String listOut() {
        String tmp = "";
        for(int i = 0; i < dir.size(); i++) {
            tmp += String.format("%d. %s\n", i + 1, dir.get(i));
        }
        return tmp;
    }
}
