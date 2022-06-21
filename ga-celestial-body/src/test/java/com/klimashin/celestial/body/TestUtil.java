package com.klimashin.celestial.body;

import com.fasterxml.jackson.databind.ObjectMapper;
import liquibase.pro.packaged.O;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

import org.springframework.core.io.ClassPathResource;

import java.nio.file.Files;
import java.util.List;

@UtilityClass
public class TestUtil {

    private final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @SneakyThrows
    public String readResourceAsString(String filePath) {
        return Files.readString(new ClassPathResource(filePath).getFile().toPath());
    }

    @SneakyThrows
    public <T> List<T> readListValue(String filePath, Class<?> storedClass) {
        return OBJECT_MAPPER.readValue(
                TestUtil.readResourceAsString(filePath),
                OBJECT_MAPPER.getTypeFactory().constructCollectionType(List.class, storedClass)
        );
    }
}
