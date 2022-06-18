package com.klimashin.celestial.body.service;

import static com.klimashin.celestial.body.exception.BodyException.BadRequestReason.BODY_ALREADY_EXIST;

import com.klimashin.celestial.body.dto.BodyDto;
import com.klimashin.celestial.body.dto.BodyRequestDto;
import com.klimashin.celestial.body.exception.BodyException.BadRequestBodyException;
import com.klimashin.celestial.body.exception.BodyException.NotFoundBodyException;
import com.klimashin.celestial.body.mapper.BodyMapper;
import com.klimashin.celestial.body.model.GravRadiusCalculator;
import com.klimashin.celestial.body.repository.BodyRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BodyService {

    private final BodyRepository bodyRepository;
    private final BodyMapper bodyMapper;

    public List<BodyDto> getAllBodies() {
        return bodyRepository.findAll().stream()
                .map(bodyMapper::toDto)
                .collect(Collectors.toList());
    }

    public BodyDto getBodyByName(String name) {
        return bodyRepository.findFirstByName(name)
                .map(bodyMapper::toDto)
                .orElseThrow(() -> new NotFoundBodyException(name));
    }

    @Transactional
    public BodyDto saveBody(BodyRequestDto bodyRequestDto) {
        if (bodyRepository.findFirstByName(bodyRequestDto.getName()).isPresent()) {
            throw new BadRequestBodyException(BODY_ALREADY_EXIST, bodyRequestDto.getName());
        }

        if (Objects.isNull(bodyRequestDto.getGravRadius())) {
            bodyRequestDto.setGravRadius(GravRadiusCalculator.calculateGravRadius(bodyRequestDto.getMass()));
        }

        return bodyMapper.toDto(bodyRepository.save(bodyRequestDto));
    }

    @Transactional
    public List<BodyDto> updateGravRadiusInAllBodies() {
        List<BodyDto> updatedBodiesDto = new ArrayList<>();

        getAllBodies().forEach(bodyDto -> {
            double gravRadius = GravRadiusCalculator.calculateGravRadius(bodyDto.getMass());

            if (Objects.isNull(bodyDto.getGravRadius()) || !bodyDto.getGravRadius().equals(gravRadius)) {
                bodyRepository.updateGravRadius(bodyDto.getName(), gravRadius);
                updatedBodiesDto.add(bodyDto.setGravRadius(gravRadius));
            }
        });

        return updatedBodiesDto;
    }
}
