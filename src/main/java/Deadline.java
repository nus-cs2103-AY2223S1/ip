class Deadline extends Task {
    public static String FLAG = " /by";
    private String time;

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

    public Deadline(String input) {
        super(Deadline.extractName(input));
        this.time = Deadline.extractTime(input);
    }

    @Override
    public String toString() {
        String type = "\u001B[32m(DEAD)\u001B[0m";
        return String.format("%s %s < %s", type, super.toString(), this.time);
    }
}
