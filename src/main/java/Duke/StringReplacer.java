
package duke;

public class StringReplacer {

    public String[] editCommandHelper(String input) {
        String[] result = input.trim().split("/");
        result[0].trim();
        result[1].trim();
        return result;
    }


    public String replaceAll(String input, String regex) {
        return input.replaceAll(regex, "").trim();
    }

}
