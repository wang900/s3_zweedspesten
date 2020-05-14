package messages.server;

import java.util.Map;

public class ReadyResultMessage {
    private Map<String, Boolean> map;

    public ReadyResultMessage(Map<String, Boolean> map) {
        this.map = map;
    }

    public Map<String, Boolean> getMap() {
        return map;
    }
}
