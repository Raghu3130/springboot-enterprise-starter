package com.accio.spring.starter.exceptions;

import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public final class BaseExceptionHelper {

    private BaseExceptionHelper() {

    }

    /**
     * The generateMessage method will generate a String message from given
     * inputs.
     *
     * @param message      The exception message as String
     * @param entity       The Entity name or AnyClass.class
     * @param searchParams Map of string
     * @return String
     */
    public static String generateMessage(
            final String message,
            final String entity,
            final Map<String, String> searchParams
    ) {
        return StringUtils.capitalize(entity) + message + searchParams;
    }

    /**
     * The generateSimpleMessage will generate String message from given message
     * and Entity name.
     *
     * @param message String
     * @param entity  Entity name or AnyClass.class as String
     * @return String
     */
    public static String generateSimpleMessage(
            final String message,
            final String entity
    ) {
        return StringUtils.capitalize(entity) + message;
    }

    /**
     * The method will generate a map in format from give inputs.
     *
     * @param keyType   Class<K>
     * @param valueType Class<V>
     * @param entries   Object
     * @param <K>       AnyClass
     * @param <V>       AnyClass
     * @return Map<K, V>
     */
    public static <K, V> Map<K, V> toMap(
            final Class<K> keyType,
            final Class<V> valueType,
            final Object... entries
    ) {
        if (entries.length % 2 == 1) {
            throw new IllegalArgumentException("Invalid entries");
        }
        return IntStream.range(
                        0,
                        entries.length / 2
                ).map(i -> i * 2)
                .collect(
                        HashMap::new,
                        (m, i) -> m.put(
                                keyType.cast(entries[i]),
                                valueType.cast(entries[i + 1])
                        ),
                        Map::putAll
                );
    }

}
