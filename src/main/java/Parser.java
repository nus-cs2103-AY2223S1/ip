enum InputType {
    mark,
    unmark,
    todo,
    deadline,
    event,
}

public class Parser {

    public ParsedInput parse(InputType type, String input) {
        ParsedInput result;
        switch (type) {

            case todo:
            case deadline:
            case event:
                result = parseTask(input);
                break;
            default:
                //for now default is for mark and unmark
                result = parseMark(input);
        }

        return result;
    }

    private ParsedInput parseMark(String input) {
        String[] first = input.split(" ", 2);
        String command = first[0];
        int listIndex = Integer.parseInt(first[1]);

        return new ParsedInput(command, listIndex - 1);
    }

    private ParsedInput parseTask(String input) {
        String command, task, during, time;
        String[] first = input.split(" ", 2);
        command = first[0];

        String[] second = first[1].split(" /", 2);
        task = second[0];

        if (second.length > 1) {
            String[] third = second[1].split(" ", 2);
            during = third[0];
            time = third[1];

            return new ParsedInput(command, task, during, time);
        }


        return new ParsedInput(command, task);
    }
}
