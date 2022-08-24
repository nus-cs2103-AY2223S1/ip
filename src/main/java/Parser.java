public class Parser {
    String stringToParse;
    Parser(String stringToParse) {
        this.stringToParse = stringToParse;
    }
    boolean isSubstringForMarkCommand(){
       return this.stringToParse.length() > 5 && this.stringToParse.substring(0, 4).equals("mark");
    }

    boolean isSubstringForUnmarkCommand() {
        return this.stringToParse.length() > 7 && this.stringToParse.substring(0, 6).equals("unmark");

    }

    boolean isSubstringForDeadlineCommand() {
        return this.stringToParse.length() >= 8 && this.stringToParse.substring(0, 8).equals("deadline");
    }

    boolean isSubstringForEventCommand() {
        return this.stringToParse.length() >= 5 && this.stringToParse.substring(0, 5).equals("event");
    }

    boolean isSubstringForTodoCommand() {
        return this.stringToParse.length() >= 4 && this.stringToParse.substring(0, 4).equals("todo");
    }

    boolean isSubstringForDeleteCommand() {
        return this.stringToParse.length() > 7 && this.stringToParse.substring(0, 6).equals("delete");
    }
}

