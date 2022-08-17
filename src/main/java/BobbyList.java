import java.util.ArrayList;
import java.util.List;

public class BobbyList {

    private static List<String> bobbyList = new ArrayList<>();
    public void add(String s) {
        bobbyList.add(s);
    }

    @Override
    public String toString() {
        String bobbyListString = "";
        int i = 0;
        for(String bL : bobbyList) {
            bobbyListString += (i+1) + "." + bobbyList.get(i) + "\n\t";
            i++;
        }
        return bobbyListString;
    }
}
