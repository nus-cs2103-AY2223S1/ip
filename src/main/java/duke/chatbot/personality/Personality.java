package duke.chatbot.personality;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

import duke.chatbot.personality.exceptions.LoadPersonalityException;

/**
 * Personality class provides a personality with unique responses.
 */
public class Personality {
    private final Map<String, Map<String, List<String>>> responseTable;

    /**
     * Creates a personality and loads the response table based on the yaml file provided.s
     *
     * @param name name of the personality to load
     */
    public Personality(String name) throws LoadPersonalityException {
        try {
            InputStream inputStream = this.getClass().getResourceAsStream("/personalities/" + name + ".yml");
            Yaml yaml = new Yaml();
            this.responseTable = yaml.load(inputStream);
        } catch (Exception exception) {
            throw new LoadPersonalityException();
        }
    }

    /**
     * Returns the formulated response according to the personality and response content.
     *
     * @param command the command to respond to
     * @param responseContent the content of the response
     * @return A formutead response based on personality
     */
    public String formulateResponse(String command, String... responseContent) {
        String responseHead = this.responseTable.get(command).get("HEAD").get(0);
        String responseTail = this.responseTable.get(command).get("TAIL").get(0);

        if (responseContent.length == 0) {
            return responseHead;
        }

        if (responseContent.length == 1) {
            return responseHead + responseContent[0] + responseTail;
        }

        if (responseContent.length == 2) {
            String responseBody = this.responseTable.get(command).get("BODY").get(0);
            return responseHead + responseContent[0] + responseBody + responseContent[1] + responseTail;
        }
        return "";
    }
}
