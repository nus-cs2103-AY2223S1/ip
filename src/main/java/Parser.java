public class Parser {
    public boolean parse(String request) {
        if (request.equals("bye")) {
            return true;
        }
        return false;
    }
}
