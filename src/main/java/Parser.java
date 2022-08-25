class Parser {
    public static int parseInteger(String input) throws WrongMessageException {
        if (input.length() == 0) {
            throw new WrongMessageException("Did you forget to specify which task to delete?");
        }
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new WrongMessageException("Come on. Give me a number instead!");
        }
    }

    public static Todo todo(String require) throws WrongMessageException{
        String content = require.substring(4).trim();
        if (content.equals("")) {
            throw new WrongMessageException();
        }
        Todo td = new Todo(content);
        return td;
    }

    public static Deadline deadline(String require) throws WrongMessageException {
        String info = require.substring(8).trim();
        String ddlDate = require.split("/by")[1].trim();
        String content = info.split("/by")[0].trim();
        if (content.equals("") || info.startsWith("/by")) {
            throw new WrongMessageException();
        }
        Deadline ddl = new Deadline(content, ddlDate);
        return ddl;
    }

    public static Event event(String require) throws WrongMessageException {
        String info = require.substring(5).trim();
        String happenTime = require.split("/at")[1].trim();
        String content = info.split("/at")[0].trim();
        if (content.equals("") || info.startsWith("/at")) {
            throw new WrongMessageException();
        }
        Event evt = new Event(content, happenTime);
        return evt;
    }
}