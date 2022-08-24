class Event extends Task {
    public static String FLAG = " /at";
    private static int typeKey = Task.getTypeKey(Task.Type.EVENT);

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

    public static Task createTask(String input) {
        String name = Event.extractName(input);
        String time = Event.extractTime(input);
        Task event = new Event(name, false, time);
        return event;
    }

    public static Task loadTask(String name, Boolean isDone, String time) {
        Task event = new Event(name, isDone, time);
        return event;
    }

    private String time;

    private Event(String name, Boolean isDone, String time) {
        super(name, isDone);
        this.time = time;
    }

    @Override
    public String encode() {
        int typeKey = Event.typeKey;
        int isDone = this.isDone ? 1 : 0;
        String result = String.format(
                "%d|%d|%s|%s\n", typeKey, isDone, this.name, this.time
                );
        return result;
    }

    @Override
    public String toString() {
        String type = "\u001B[34m(EVNT)\u001B[0m";
        return String.format("%s %s @ %s", type, super.toString(), this.time);
    }
}
