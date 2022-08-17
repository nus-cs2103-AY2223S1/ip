class Event extends Task {
    public static String FLAG = " /at";
    private String time;

    private static String extractName(String input) {
        int flagIndex = input.indexOf(Event.FLAG);
        if (flagIndex == -1) {
            CarbonException invalidFlag = new InvalidFlagException(input, "event");
            throw invalidFlag;
        } else {
            String name = input.substring("event ".length(), flagIndex);
            return name;
        }
    }

    private static String extractTime(String input) {
        int len = input.length();
        int flagIndex = input.indexOf(Event.FLAG);
        if (len <= flagIndex + Event.FLAG.length() + 1) {
            CarbonException invalidParam = new InvalidParamException(input);
            throw invalidParam;
        } else {
            String time = input.substring(flagIndex + Event.FLAG.length() + 1);
            return time;
        }
    }

    public Event(String input) {
        super(Event.extractName(input));
        this.time = Event.extractTime(input);
    }

    @Override
    public String toString() {
        String type = "\u001B[34m(EVNT)\u001B[0m";
        return String.format("%s %s @ %s", type, super.toString(), this.time);
    }
}
