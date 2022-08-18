public class Seaward {

    private final static String welcome = "Hello! I'm Seaward,\n" +
            "your friendly neighbourhood chatbot. \n" +
            "Type something and I will reply!";

//    private String inputString;

    public Seaward() {

    }

    public String getWelcome() {
        return welcome;
    }

    public String readInputString(String s) {
        if (s.equals("bye")) {
            return "Seaward out!";
        } else {
            return s;
        }
    }
}
