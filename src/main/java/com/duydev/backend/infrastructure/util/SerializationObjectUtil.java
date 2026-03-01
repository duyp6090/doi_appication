package com.duydev.backend.infrastructure.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "SerializationObjectUtil")
public class SerializationObjectUtil {
    public static String serialize(Object object) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }

    public static <T> T deserialize(String object, Class<T> cls) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(object, cls);
    }
}
