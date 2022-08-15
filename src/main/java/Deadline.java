class Deadline extends Task {
    public static String FLAG = " /by ";

    public static String extractName(String input) {
        int flagIndex = input.indexOf(Deadline.FLAG);
        String name = input.substring("deadline ".length(), flagIndex);
        return name;
    }

    public static String extractTime(String input) {
        int flagIndex = input.indexOf(Deadline.FLAG);
        String name = input.substring(flagIndex + Deadline.FLAG.length());
        return name;
    }

    public Deadline(String input) {
        super(input);
    }

    @Override
    public String toString() {
        String type = "\u001B[32m(DEAD)\u001B[0m";
        return String.format("%s %s", type, super.toString());
    }
}
