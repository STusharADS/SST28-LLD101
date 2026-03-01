package com.example.metrics;

import java.io.Serial;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Thread-safe Singleton metrics registry using Bill Pugh Singleton pattern.
 * 
 * Features:
 * - Lazy initialization via static holder
 * - Thread-safe without explicit synchronization
 * - Reflection-protected
 * - Serialization-safe (returns same instance)
 */
public class MetricsRegistry implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private final Map<String, Long> counters = new HashMap<>();
    private static volatile boolean instanceCreated = false;

    /**
     * Private constructor - prevents direct instantiation.
     * Also prevents reflection attacks by checking if instance already exists.
     */
    private MetricsRegistry() {
        // Reflection attack prevention
        synchronized (MetricsRegistry.class) {
            if (instanceCreated) {
                throw new IllegalStateException(
                    "Singleton instance already exists! Cannot create another instance via reflection."
                );
            }
            instanceCreated = true;
        }
    }

    /**
     * Static holder class - loaded only when getInstance() is called.
     * Provides thread-safe lazy initialization without synchronization overhead.
     * (Bill Pugh Singleton pattern)
     */
    private static class SingletonHolder {
        private static final MetricsRegistry INSTANCE = new MetricsRegistry();
    }

    /**
     * Thread-safe lazy initialization via static holder.
     * No synchronization needed - class loading mechanism guarantees thread safety.
     */
    public static MetricsRegistry getInstance() {
        return SingletonHolder.INSTANCE;
    }

    /**
     * Preserve singleton property during deserialization.
     * Returns the existing singleton instance instead of creating a new one.
     */
    @Serial
    private Object readResolve() {
        return getInstance();
    }

    public synchronized void setCount(String key, long value) {
        counters.put(key, value);
    }

    public synchronized void increment(String key) {
        counters.put(key, getCount(key) + 1);
    }

    public synchronized long getCount(String key) {
        return counters.getOrDefault(key, 0L);
    }

    public synchronized Map<String, Long> getAll() {
        return Collections.unmodifiableMap(new HashMap<>(counters));
    }
}
