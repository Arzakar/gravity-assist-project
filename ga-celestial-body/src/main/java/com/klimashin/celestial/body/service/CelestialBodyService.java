package com.klimashin.celestial.body.service;

import com.klimashin.celestial.body.dto.CelestialBodyDto;
import com.klimashin.celestial.body.mapper.CelestialBodyMapper;
import com.klimashin.celestial.body.repository.CelestialBodyRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CelestialBodyService {

    private final CelestialBodyRepository celestialBodyRepository;
    private final CelestialBodyMapper celestialBodyMapper;

    public CelestialBodyDto getCelestialBodyByName(String name) {
        return celestialBodyMapper.toDto(celestialBodyRepository
                .findFirstByName(name)
                .orElseThrow(() -> new RuntimeException("Ошибка при попытке обратиться к БД"))
        );
    }
}
