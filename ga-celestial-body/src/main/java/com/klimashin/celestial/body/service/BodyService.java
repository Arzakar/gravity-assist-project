package com.klimashin.celestial.body.service;

import com.klimashin.celestial.body.dto.BodyDto;
import com.klimashin.celestial.body.exception.BodyException;
import com.klimashin.celestial.body.exception.BodyException.NotFoundBodyException;
import com.klimashin.celestial.body.mapper.BodyMapper;
import com.klimashin.celestial.body.repository.CelestialBodyRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BodyService {

    private final CelestialBodyRepository celestialBodyRepository;
    private final BodyMapper bodyMapper;

    public BodyDto getCelestialBodyByName(String name) {
        return bodyMapper.toDto(celestialBodyRepository
                .findFirstByName(name)
                .orElseThrow(() -> new NotFoundBodyException(String.format("Небесное тело с именем '%s' не найдено", name))));
    }

    public String saveBody(BodyDto bodyDto) {
        if (celestialBodyRepository.findFirstByName(bodyDto.getName()).isPresent()) {
            throw new BodyException.BadRequestBodyException(String.format("Небесное тело с именем '%s' уже существует", bodyDto.getName()));
        }
        return celestialBodyRepository.save(bodyMapper.toEntity(bodyDto)).getName();
    }
}
