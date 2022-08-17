public class InputChecker {

    private static final BotUI UI = new BotUI();
    public static void checkInput(String input) throws DukeException {
        if (input.startsWith("todo")) {
            if (input.replace("todo", "").replace(" ", "").isEmpty()) {
                throw new DukeException(UI.invalidFormat());
            }
        } else if (input.startsWith("deadline")) {
            String description = input.replace("deadline", "").replace(" ", "");
            if (!description.contains("/by") || description.substring(0, description.indexOf("/by")).isEmpty() ||
                    description.substring(description.indexOf("/by")).length() == 3) {
                throw new DukeException(UI.invalidFormat());
            }
        } else if (input.startsWith("event")) {
            String description = input.replace("event", "").replace(" ", "");
            if (!description.contains("/at") || description.substring(0, description.indexOf("/at")).isEmpty() ||
                    description.substring(description.indexOf("/at")).length() == 3) {
                throw new DukeException(UI.invalidFormat());
            }
        }
    }
}
