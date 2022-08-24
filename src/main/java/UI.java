/**
 * Deals with interactions from the user.
 */
public class UI {
    public void greeting() {
        /**
         * Greeting message to the user during chat-bot startup.
         */

        String logo = "" +
                "\t ▄▄▄▄▄▄▄ ▄▄   ▄▄ ▄▄   ▄▄ ▄▄   ▄▄ \n" +
                "\t█       █  █ █  █  █▄█  █  █ █  █\n" +
                "\t█▄     ▄█  █ █  █       █  █ █  █\n" +
                "\t  █   █ █  █▄█  █       █  █▄█  █\n" +
                "\t  █   █ █       █       █       █\n" +
                "\t  █   █ █       █ ██▄██ █       █\n" +
                "\t  █▄▄▄█ █▄▄▄▄▄▄▄█▄█   █▄█▄▄▄▄▄▄▄█\n\n";
        String greetingMessage = "\tHi! I am Tumu. Nice to meet you!\n" +
                "\tWhat is on your mind today?\n";

        System.out.println(logo + greetingMessage);
    }

    public void goodbye() {
        /**
         * Says goodbye to the user.
         * User exits the chat-bot.
         */

        String goodbyeMessage = "\tGoodbye, and have a nice day ahead!\n";
        String smileyFace = "\t٩(ˊᗜˋ )و";
        System.out.println(goodbyeMessage + smileyFace);
    }

    public String showLine() {
        return "\t" + "_".repeat(60);
    }

    /**
     * Where all the messages seen by the user will pass through.
     * @param message
     */
    public void notifyUser(String message) {
        System.out.println(message);
    }
}
