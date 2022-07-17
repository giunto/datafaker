package net.datafaker;

import net.datafaker.service.RandomService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.Random;
import java.util.UUID;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UniqueTest {

    RandomService fakerRandomService;
    private Faker faker;

    private Random random;

    @BeforeEach
    public void before() {
        fakerRandomService = mock(RandomService.class);
        faker = mock(Faker.class);
        when(faker.random()).thenReturn(fakerRandomService);

        random = new Random();
    }

    @Test
    public void get_shouldReturnObjectFromSupplier() {
        Object firstValue = mock(Object.class);
        Object secondValue = mock(Object.class);
        Object thirdValue = mock(Object.class);

        @SuppressWarnings("unchecked")
        Supplier<Object> supplier = (Supplier<Object>) mock(Supplier.class);

        when(supplier.get())
            .thenReturn(firstValue)
            .thenReturn(secondValue)
            .thenReturn(thirdValue);

        Unique unique = new Unique(new Faker());

        assertEquals(firstValue, unique.get(supplier));
        assertEquals(secondValue, unique.get(supplier));
        assertEquals(thirdValue, unique.get(supplier));
    }

    @Test
    public void get_shouldNotReturnTheSameObjectFromSupplierMoreThanOnce() {
        Object firstValue = mock(Object.class);
        Object secondValue = mock(Object.class);
        Object thirdValue = mock(Object.class);

        @SuppressWarnings("unchecked")
        Supplier<Object> supplier = (Supplier<Object>) mock(Supplier.class);

        when(supplier.get())
            .thenReturn(firstValue)
            .thenReturn(firstValue)
            .thenReturn(secondValue)
            .thenReturn(firstValue)
            .thenReturn(secondValue)
            .thenReturn(thirdValue);

        Unique unique = new Unique(new Faker());

        assertEquals(firstValue, unique.get(supplier));
        assertEquals(secondValue, unique.get(supplier));
        assertEquals(thirdValue, unique.get(supplier));
    }

    @Test
    public void get_shouldTimeOutAndThrowRuntimeException_whenSupplierDoesNotProduceUniqueValues() {
        Object firstValue = mock(Object.class);

        @SuppressWarnings("unchecked")
        Supplier<Object> supplier = (Supplier<Object>) mock(Supplier.class);

        when(supplier.get()).thenReturn(firstValue);

        Unique unique = new Unique(new Faker());

        assertDoesNotThrow(() -> unique.get(supplier));

        assertThrows(
            RuntimeException.class,
            () -> assertTimeoutPreemptively(Duration.ofSeconds(15), () -> unique.get(supplier)),
            "Supplier did not throw RuntimeException"
        );
    }

    @Test
    public void resolve_shouldNotReturnSameValueMoreThanOnce() {
        String key = UUID.randomUUID().toString();

        String firstValue = UUID.randomUUID().toString();
        String secondValue = UUID.randomUUID().toString();
        String thirdValue = UUID.randomUUID().toString();

        when(faker.resolve(key))
            .thenReturn(firstValue)
            .thenReturn(firstValue)
            .thenReturn(secondValue)
            .thenReturn(firstValue)
            .thenReturn(secondValue)
            .thenReturn(thirdValue);

        Unique unique = new Unique(faker);

        assertEquals(firstValue, unique.resolve(key));
        assertEquals(secondValue, unique.resolve(key));
        assertEquals(thirdValue, unique.resolve(key));
    }

    @Test
    public void expression_shouldNotReturnSameValueMoreThanOnce() {
        String expression = UUID.randomUUID().toString();

        String firstValue = UUID.randomUUID().toString();
        String secondValue = UUID.randomUUID().toString();
        String thirdValue = UUID.randomUUID().toString();

        when(faker.expression(expression))
            .thenReturn(firstValue)
            .thenReturn(firstValue)
            .thenReturn(secondValue)
            .thenReturn(firstValue)
            .thenReturn(secondValue)
            .thenReturn(thirdValue);

        Unique unique = new Unique(faker);

        assertEquals(firstValue, unique.expression(expression));
        assertEquals(secondValue, unique.expression(expression));
        assertEquals(thirdValue, unique.expression(expression));
    }

    @Test
    public void nextInt_withNoParameters_shouldNotReturnSameValueMoreThanOnce() {
        int firstValue = random.nextInt(9) + 1;
        int secondValue = firstValue + random.nextInt(9) + 1;
        int thirdValue = secondValue + random.nextInt(9) + 1;

        when(fakerRandomService.nextInt())
            .thenReturn(firstValue)
            .thenReturn(firstValue)
            .thenReturn(secondValue)
            .thenReturn(firstValue)
            .thenReturn(secondValue)
            .thenReturn(thirdValue);

        Unique unique = new Unique(faker);

        assertEquals(firstValue, unique.nextInt());
        assertEquals(secondValue, unique.nextInt());
        assertEquals(thirdValue, unique.nextInt());
    }

    @Test
    public void nextInt_withBound_shouldNotReturnSameValueMoreThanOnce() {
        int bound = random.nextInt();

        int firstValue = random.nextInt(9) + 1;
        int secondValue = firstValue + random.nextInt(9) + 1;
        int thirdValue = secondValue + random.nextInt(9) + 1;

        when(fakerRandomService.nextInt(bound))
            .thenReturn(firstValue)
            .thenReturn(firstValue)
            .thenReturn(secondValue)
            .thenReturn(firstValue)
            .thenReturn(secondValue)
            .thenReturn(thirdValue);

        Unique unique = new Unique(faker);

        assertEquals(firstValue, unique.nextInt(bound));
        assertEquals(secondValue, unique.nextInt(bound));
        assertEquals(thirdValue, unique.nextInt(bound));
    }

    @Test
    public void nextInt_withMinAndMax_shouldNotReturnSameValueMoreThanOnce() {
        int min = random.nextInt();
        int max = random.nextInt();

        int firstValue = random.nextInt(9) + 1;
        int secondValue = firstValue + random.nextInt(9) + 1;
        int thirdValue = secondValue + random.nextInt(9) + 1;

        when(fakerRandomService.nextInt(min, max))
            .thenReturn(firstValue)
            .thenReturn(firstValue)
            .thenReturn(secondValue)
            .thenReturn(firstValue)
            .thenReturn(secondValue)
            .thenReturn(thirdValue);

        Unique unique = new Unique(faker);

        assertEquals(firstValue, unique.nextInt(min, max));
        assertEquals(secondValue, unique.nextInt(min, max));
        assertEquals(thirdValue, unique.nextInt(min, max));
    }

    @Test
    public void nextLong_withNoParameters_shouldNotReturnSameValueMoreThanOnce() {
        long firstValue = random.nextInt(9) + 1;
        long secondValue = firstValue + random.nextInt(9) + 1;
        long thirdValue = secondValue + random.nextInt(9) + 1;

        when(fakerRandomService.nextLong())
            .thenReturn(firstValue)
            .thenReturn(firstValue)
            .thenReturn(secondValue)
            .thenReturn(firstValue)
            .thenReturn(secondValue)
            .thenReturn(thirdValue);

        Unique unique = new Unique(faker);

        assertEquals(firstValue, unique.nextLong());
        assertEquals(secondValue, unique.nextLong());
        assertEquals(thirdValue, unique.nextLong());
    }

    @Test
    public void nextLong_withBound_shouldNotReturnSameValueMoreThanOnce() {
        long bound = random.nextLong();

        long firstValue = random.nextInt(9) + 1;
        long secondValue = firstValue + random.nextInt(9) + 1;
        long thirdValue = secondValue + random.nextInt(9) + 1;

        when(fakerRandomService.nextLong(bound))
            .thenReturn(firstValue)
            .thenReturn(firstValue)
            .thenReturn(secondValue)
            .thenReturn(firstValue)
            .thenReturn(secondValue)
            .thenReturn(thirdValue);

        Unique unique = new Unique(faker);

        assertEquals(firstValue, unique.nextLong(bound));
        assertEquals(secondValue, unique.nextLong(bound));
        assertEquals(thirdValue, unique.nextLong(bound));
    }

    @Test
    public void nextLong_withMinAndMax_shouldNotReturnSameValueMoreThanOnce() {
        long min = random.nextLong();
        long max = random.nextLong();

        long firstValue = random.nextInt(9) + 1;
        long secondValue = firstValue + random.nextInt(9) + 1;
        long thirdValue = secondValue + random.nextInt(9) + 1;

        when(fakerRandomService.nextLong(min, max))
            .thenReturn(firstValue)
            .thenReturn(firstValue)
            .thenReturn(secondValue)
            .thenReturn(firstValue)
            .thenReturn(secondValue)
            .thenReturn(thirdValue);

        Unique unique = new Unique(faker);

        assertEquals(firstValue, unique.nextLong(min, max));
        assertEquals(secondValue, unique.nextLong(min, max));
        assertEquals(thirdValue, unique.nextLong(min, max));
    }
}
