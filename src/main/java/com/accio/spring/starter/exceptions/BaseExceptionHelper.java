package com.accio.spring.starter.exceptions;

import com.accio.spring.starter.responses.StandardResponse;
import org.springframework.util.StringUtils;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class BaseExceptionHelper {

    public static String generateMessage(String message, String entity, Map<String, String> searchParams) {
        return StringUtils.capitalize(entity) +
                message +
                searchParams;
    }

    public static String generateSimpleMessage(String message, String entity) {
        return StringUtils.capitalize(entity) + message;
    }

    public static <K, V> Map<K, V> toMap(
            Class<K> keyType, Class<V> valueType, Object... entries) {
        if (entries.length % 2 == 1)
            throw new IllegalArgumentException("Invalid entries");
        return IntStream.range(0, entries.length / 2).map(i -> i * 2)
                .collect(HashMap::new,
                        (m, i) -> m.put(keyType.cast(entries[i]), valueType.cast(entries[i + 1])),
                        Map::putAll);
    }

}
