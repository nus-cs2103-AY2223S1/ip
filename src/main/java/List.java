import java.util.ArrayList;

public class List {

    public ArrayList<String> dir = new ArrayList<String>();

    public List(){

    }

    public void addList(String cmd) {
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
