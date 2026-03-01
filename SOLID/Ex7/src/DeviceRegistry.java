import java.util.*;

public class DeviceRegistry {
    private final java.util.List<Object> devices = new ArrayList<>();

    public void add(Object d) { devices.add(d); }

    @SuppressWarnings("unchecked")
    public <T> T getFirstOfType(Class<T> capability) {
        for (Object d : devices) {
            if (capability.isInstance(d)) return (T) d;
        }
        throw new IllegalStateException("Missing device with capability: " + capability.getSimpleName());
    }

    public Powerable getFirstPowerableOfType(String simpleName) {
        for (Object d : devices) {
            if (d.getClass().getSimpleName().equals(simpleName) && d instanceof Powerable) {
                return (Powerable) d;
            }
        }
        throw new IllegalStateException("Missing: " + simpleName);
    }
}
