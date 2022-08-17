import java.util.ArrayList;

class Bot {

private final String name;
private final ArrayList<String> tasks;
private static final String SPACE = "    ";
private static final String BORDER = "    ____________________________________________________________";

    Bot() {
        this.name = "Bocil";
        this.tasks = new ArrayList<String>();
    }

    void introduce() {
        String line1 = String.format("%sHello! I'm %s", SPACE, this.name);
        String line2 = String.format("%sWhat can I do for you?", SPACE);
        System.out.println(String.format("%s\n%s\n%s\n%s\n", BORDER, line1, line2, BORDER));
    }
    void answer(String input) {
        String response = "";
        if (input.equals("bye")) {
            response = response.concat(String.format("%sBye. Hope to see you again soon!", SPACE));
        } else if (input.equals("list")) {
            for (int i=0; i<this.tasks.size(); i++) {
                String line = String.format("%s. %s", i+1, this.tasks.get(i));
                response = response.concat(String.format("%s%s", SPACE, line));
                if (i<this.tasks.size()-1) {
                    response = response.concat("\n");
                }
            }
        }
        else {
            this.tasks.add(input);
            response = response.concat(String.format("%sadded: %s", SPACE, input));
        }
        System.out.println(String.format("%s\n%s\n%s\n", BORDER, response, BORDER));
        }
    }
