class Deadline extends Task {
    public static String FLAG = " /by";
    private static int typeKey = Task.getTypeKey(Task.Type.DEADLINE);

    private static String extractName(String input) throws CarbonException {
        int flagIndex = input.indexOf(Deadline.FLAG);
        if (flagIndex == -1) {
            CarbonException invalidFlag = new InvalidFlagException(input, "deadline");
            throw invalidFlag;
        } else {
            String name = input.substring("deadline ".length(), flagIndex);
            return name;
        }
    }

    private static String extractTime(String input) throws CarbonException {
        int len = input.length();
        int flagIndex = input.indexOf(Deadline.FLAG);
        if (len <= flagIndex + Deadline.FLAG.length() + 1) {
            CarbonException invalidParam = new InvalidParamException(input);
            throw invalidParam;
        } else {
            String time = input.substring(flagIndex + Deadline.FLAG.length() + 1);
            return time;
        }
    }

    public static Task createTask(String input) {
        String name = Deadline.extractName(input);
        String time = Deadline.extractTime(input);
        Task deadline = new Deadline(name, false, time);
        return deadline;
    }

    public static Task loadTask(String name, Boolean isDone, String time) {
        Task deadline = new Deadline(name, isDone, time);
        return deadline;
    }

    private String time;
    
    private Deadline(String name, Boolean isDone, String time) {
        super(name, isDone);
        this.time = time;
    }

    @Override
    public String encode() {
        int typeKey = Deadline.typeKey;
        int isDone = this.isDone ? 1 : 0;
        String result = String.format(
                "%d|%d|%s|%s\n", typeKey, isDone, this.name, this.time
                );
        return result;
    }

    @Override
    public String toString() {
        String type = "\u001B[32m(DEAD)\u001B[0m";
        return String.format("%s %s < %s", type, super.toString(), this.time);
    }
}
