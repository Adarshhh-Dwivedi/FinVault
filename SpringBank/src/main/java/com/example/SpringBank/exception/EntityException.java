package com.example.FinVault.exception;

import org.apache.commons.lang3.StringUtils;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Base class for Entity-related exceptions (NotFound, Duplicate).
 * Provides automated message generation for specific search parameters.
 */
public class EntityException extends RuntimeException { // Switched to RuntimeException for cleaner code

    public EntityException(Class<?> clazz, String indicator, String... searchParamsMap) {
        super(generateMessage(clazz.getSimpleName(), indicator, toMap(searchParamsMap)));
    }

    private static String generateMessage(String entity, String indicator, Map<String, String> searchParams) {
        return StringUtils.capitalize(entity) + indicator + searchParams;
    }

    private static Map<String, String> toMap(String... entries) {
        if (entries.length % 2 != 0) {
            throw new IllegalArgumentException("Invalid entries: searchParamsMap must have an even number of elements.");
        }
        return IntStream.range(0, entries.length / 2)
                .map(i -> i * 2)
                .boxed()
                .collect(Collectors.toMap(
                        i -> entries[i],
                        i -> entries[i + 1]
                ));
    }
}