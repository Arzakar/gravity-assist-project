package com.klimashin.celestial.body.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.klimashin.celestial.body.TestUtil;
import com.klimashin.celestial.body.dto.BodyDto;
import com.klimashin.celestial.body.dto.BodyRequestDto;
import com.klimashin.celestial.body.entity.Body;
import com.klimashin.celestial.body.exception.BodyException;
import com.klimashin.celestial.body.mapper.BodyMapper;
import com.klimashin.celestial.body.repository.BodyRepository;

import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ExtendWith(MockitoExtension.class)
class BodyServiceTest {

    @Mock
    private BodyRepository bodyRepository;

    @Spy
    private BodyMapper bodyMapper = Mappers.getMapper(BodyMapper.class);

    @InjectMocks
    private BodyService bodyService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @SneakyThrows
    @DisplayName("Должен вернуть все небесные тела из БД")
    void shouldReturnAllBodies() {
        List<Body> expectedResponse = TestUtil.readListValue("service/body/response/find-all-bodies.json", Body.class);

        when(bodyRepository.findAll()).thenReturn(expectedResponse);

        List<BodyDto> actualResult = bodyService.getAllBodies();

        assertEquals(expectedResponse.size(), actualResult.size());

        List<BodyDto> expectedResult = expectedResponse.stream()
                .map(bodyMapper::toDto)
                .collect(Collectors.toList());

        assertEquals(expectedResult, actualResult);
    }

    @Test
    @DisplayName("Должен вернуть небесное тело с конкретным именем")
    void shouldReturnBodyByName() {
        String testName = "Test Solar";
        Body expectedResponse = new Body()
                .setName(testName)
                .setMass(100_000_000_000d)
                .setRadius(10_000d)
                .setGravParameter(100_000_000d)
                .setGravRadius(100_000_000_000d);

        when(bodyRepository.findFirstByName(testName)).thenReturn(Optional.of(expectedResponse));

        BodyDto actualResult = bodyService.getBodyByName(testName);

        assertEquals(bodyMapper.toDto(expectedResponse), actualResult);
    }

    @Test
    @DisplayName("Должен выбросить ошибку, т.к. тело с конкретным именем не найдено")
    void shouldThrowNotFoundBodyExceptionWhileGettingBodyByName() {
        String testName = "Test Solar";
        var expectedException = new BodyException.NotFoundBodyException(testName);

        when(bodyRepository.findFirstByName(any(String.class))).thenReturn(Optional.empty());

        var thrown = assertThrows(BodyException.NotFoundBodyException.class, () ->
                bodyService.getBodyByName(testName));

        assertEquals(expectedException.getMessage(), thrown.getMessage());
    }

    @Test
    @DisplayName("Должен сохранить тело, когда переданы все параметры")
    void shouldSaveBodyWhenAllFieldsFill() {
        BodyRequestDto testRequest = BodyRequestDto.builder()
                .name("Test Solar")
                .mass(100_000_000_000d)
                .radius(10_000d)
                .gravParameter(100_000_000d)
                .gravRadius(100_000_000_000d)
                .build();

        BodyDto expectedResult = BodyDto.builder()
                .name(testRequest.getName())
                .mass(testRequest.getMass())
                .radius(testRequest.getRadius())
                .gravParameter(testRequest.getGravParameter())
                .gravRadius(testRequest.getGravRadius())
                .build();

        when(bodyRepository.findFirstByName(any(String.class))).thenReturn(Optional.empty());
        when(bodyRepository.save(testRequest)).thenReturn(testRequest);

        assertEquals(expectedResult, bodyService.saveBody(testRequest));
    }

    @Test
    @DisplayName("Должен сохранить тело и рассчитать гравитационный радиус, если он не передан")
    void shouldSaveBodyWhenGravRadiusInNull() {
        BodyRequestDto testRequest = BodyRequestDto.builder()
                .name("Test Mercury")
                .mass(333022000000000000000000d)
                .radius(null)
                .gravParameter(22032000000000d)
                .gravRadius(null)
                .build();

        BodyDto expectedResult = BodyDto.builder()
                .name(testRequest.getName())
                .mass(testRequest.getMass())
                .radius(testRequest.getRadius())
                .gravParameter(testRequest.getGravParameter())
                .gravRadius(354200000d)
                .build();

        when(bodyRepository.findFirstByName(any(String.class))).thenReturn(Optional.empty());
        when(bodyRepository.save(testRequest)).thenReturn(testRequest);

        assertEquals(expectedResult, bodyService.saveBody(testRequest));
    }

    @Test
    @DisplayName("Должен выбросить ошибку, т.к. тело с таким именем уже существует")
    void shouldThrowBadRequestBodyExceptionWhenSaveExistBody() {
        String testName = "Test Solar";
        var expectedException = new BodyException.BadRequestBodyException(BodyException.BadRequestReason.BODY_ALREADY_EXIST, testName);

        BodyRequestDto testRequest = BodyRequestDto.builder()
                .name(testName)
                .mass(100_000_000_000d)
                .radius(10_000d)
                .gravParameter(100_000_000d)
                .gravRadius(100_000_000_000d)
                .build();

        Body responseFindFirstByName = new Body()
                .setName(testName)
                .setMass(testRequest.getMass())
                .setRadius(testRequest.getRadius())
                .setGravParameter(testRequest.getGravParameter())
                .setGravRadius(testRequest.getGravRadius());

        when(bodyRepository.findFirstByName(testName)).thenReturn(Optional.of(responseFindFirstByName));

        BodyException.BadRequestBodyException thrown = assertThrows(BodyException.BadRequestBodyException.class, () ->
                bodyService.saveBody(testRequest));

        assertEquals(expectedException.getMessage(), thrown.getMessage());
    }

    @Test
    @DisplayName("Должен обновить гравитационный радиус в записях, где его нет или он некорректный")
    void shouldUpdateGravRadiusInAllBodies() {
        List<Body> responseFindAll = TestUtil.readListValue("service/body/response/find-all-bodies-with-incorrect-grav-radius.json", Body.class);
        List<BodyDto> expectedResponse = TestUtil.readListValue("service/body/response/updated-bodies-dto.json", BodyDto.class);

        when(bodyRepository.findAll()).thenReturn(responseFindAll);

        assertEquals(expectedResponse, bodyService.updateGravRadiusInAllBodies());
        verify(bodyRepository, times(2)).updateGravRadius(any(String.class), any(Double.class));
    }

    @Test
    @DisplayName("Должен вернуть пустой список, если в базе не было записей, которые необходимо обновить")
    void shouldNotUpdateGravRadiusInAllBodies() {
        List<Body> responseFindAll = Collections.emptyList();

        when(bodyRepository.findAll()).thenReturn(responseFindAll);

        assertTrue(bodyService.updateGravRadiusInAllBodies().isEmpty());
        verify(bodyRepository, never()).updateGravRadius(any(String.class), any(Double.class));
    }
}