package net.datafaker;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Supplier;

import static java.lang.System.currentTimeMillis;

public class Unique {
    private final Faker faker;
    private final Set<Object> uniqueValueStore;

    private static final long LOOP_TIMEOUT_MILLIS = 10000;

    public Unique(Faker faker) {
        this.faker = faker;
        this.uniqueValueStore = new HashSet<>();
    }

    public <T> T get(Supplier<T> supplier) {
        T value = supplier.get();
        long millisBeforeCheck = currentTimeMillis();
        while (uniqueValueStore.contains(value)) {
            handleInfiniteLoop(millisBeforeCheck);
            value = supplier.get();
        }
        uniqueValueStore.add(value);
        return value;
    }

    public String resolve(String key) {
        return get(() -> faker.resolve(key));
    }

    public String expression(String expression) {
        return get(() -> faker.expression(expression));
    }

    public int nextInt() {
        return get(() -> faker.random().nextInt());
    }

    public int nextInt(int n) {
        return get(() -> faker.random().nextInt(n));
    }

    public int nextInt(int min, int max) {
        return get(() -> faker.random().nextInt(min, max));
    }

    public long nextLong() {
        return get(() -> faker.random().nextLong());
    }

    public long nextLong(long n) {
        return get(() -> faker.random().nextLong(n));
    }

    public long nextLong(long min, long max) {
        return get(() -> faker.random().nextLong(min, max));
    }

    private void handleInfiniteLoop(long initialMillis) {
        if (currentTimeMillis() - initialMillis > LOOP_TIMEOUT_MILLIS) {
            throw new RuntimeException("Unable to get unique value from supplier");
        }
    }
}
