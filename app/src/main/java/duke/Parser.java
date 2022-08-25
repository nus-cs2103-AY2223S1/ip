package duke;

public class Parser {
    public String[] splitOnFirst(String str, String target) {
        int split = str.indexOf(target);
        if (split < 0) {
            return new String[] {str, ""};
        } else {
            String[] out = new String[2];
            out[0] = str.substring(0, split);
            out[1] = str.substring(split + target.length());
            return out;
        }
    }
}
