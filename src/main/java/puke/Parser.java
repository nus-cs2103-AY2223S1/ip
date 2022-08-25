package puke;

public class Parser {
    public static String getMessage(String s, String type) throws DukeException{
        String result = "";
        for (int i = 0 ; i < s.length(); i++) {
            if (i == 0) {
                continue;
            }
            if (s.charAt(i) == "/".charAt(0)) {
                break;
            }
            result += s.charAt(i);
        }

        if (result.length() == 0) {
            throw new DukeException("    ____________________________________________________________\n" +
                    "     OOPS!!! The Description of a " + type + " cannot be empty.\n" +
                    "    ____________________________________________________________");
        }
        return result;
    }

    public static String getDate(String s) {
        String result = "";
        int temp = 0;
        for (int i = 0 ; i < s.length(); i++) {
            if (s.charAt(i) == "/".charAt(0)) {
                temp = i;
            }
        }
        for (int r = temp + 4 ; r < s.length(); r++) {
            result += s.charAt(r);
        }
        return result;
    }

    public static String getFindTask(String s) {
        String temp = "";
        for (int i = 0; i < s.length(); i++ ) {
            if (i == 0) { continue;}
            temp += s.charAt(i);
        }
        return temp;
    }
} 
