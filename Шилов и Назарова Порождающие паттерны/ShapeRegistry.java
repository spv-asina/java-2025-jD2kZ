import java.util.*;

class ShapeRegistry {
    private Map<String, Shape> prototypes = new HashMap<>();

    public void register(String key, Shape prototype) {
        prototypes.put(key, prototype);
    }

    public Shape create(String key) {
        Shape prototype = prototypes.get(key);
        if (prototype == null) {
            throw new IllegalArgumentException("Нет прототипа: " + key);
        }
        return prototype.clone();
    }
}