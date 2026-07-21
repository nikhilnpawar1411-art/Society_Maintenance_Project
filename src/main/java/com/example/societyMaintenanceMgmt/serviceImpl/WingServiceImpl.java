package com.example.societyMaintenanceMgmt.serviceImpl;

import com.example.societyMaintenanceMgmt.dto.WingRequestDto;
import com.example.societyMaintenanceMgmt.dto.WingResponseDto;
import com.example.societyMaintenanceMgmt.entity.Society;
import com.example.societyMaintenanceMgmt.entity.Wing;
import com.example.societyMaintenanceMgmt.exception.AlreadyExistException;
import com.example.societyMaintenanceMgmt.exception.ResourceNotFoundException;
import com.example.societyMaintenanceMgmt.repository.SocietyRepository;
import com.example.societyMaintenanceMgmt.repository.WingRepository;
import com.example.societyMaintenanceMgmt.service.IWingService;
import com.example.societyMaintenanceMgmt.utility.LoggedInUser;
import com.example.societyMaintenanceMgmt.utility.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WingServiceImpl implements IWingService {

    @Autowired
    SocietyRepository societyRepository;

    @Autowired
    WingRepository wingRepository;

    @Override
    public WingResponseDto createWing(WingRequestDto request) {


        LoggedInUser currentUser = SecurityUtils.getCurrentUser();

        Society society = societyRepository.findById(currentUser.getSocietyId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Society",
                        "Id",
                        currentUser.getSocietyId().toString()));

        if (wingRepository.existsBySocietyAndWingNameIgnoreCase(
                society,
                request.getWingName())) {

            throw new AlreadyExistException("Wing already exists.");
        }

        Wing wing = new Wing();
        wing.setSociety(society);
        wing.setWingName(request.getWingName());
        wing.setActive(true);

        wing = wingRepository.save(wing);

        return new WingResponseDto(
                wing.getWingId(),
                wing.getWingName(),
                wing.getActive());
    }

    @Override
    public List<WingResponseDto> getAllWings() {

        LoggedInUser currentUser = SecurityUtils.getCurrentUser();

        Society society = societyRepository.findById(currentUser.getSocietyId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Society",
                                "Id",
                                currentUser.getSocietyId().toString()));

        List<Wing> wings =
                wingRepository.findBySocietyAndActiveTrueOrderByWingNameAsc(society);

        return wings.stream()
                .map(wing -> new WingResponseDto(
                        wing.getWingId(),
                        wing.getWingName(),
                        wing.getActive()))
                .toList();
    }

    @Override
    public WingResponseDto updateWing(WingRequestDto request) {

        LoggedInUser currentUser = SecurityUtils.getCurrentUser();

        Society society = societyRepository.findById(currentUser.getSocietyId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Society",
                                "Id",
                                currentUser.getSocietyId().toString()));

        Wing wing = wingRepository
                .findByWingIdAndSocietyAndActiveTrue(request.getWingId(), society)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Wing",
                                "Wing Id",
                                request.getWingName().toString()));

        if (!wing.getWingName().equalsIgnoreCase(request.getWingName())
                && wingRepository.existsBySocietyAndWingNameIgnoreCase(
                society,
                request.getWingName())) {

            throw new AlreadyExistException("Wing already exists.");
        }

        wing.setWingName(request.getWingName());

        wingRepository.save(wing);

        return new WingResponseDto(
                wing.getWingId(),
                wing.getWingName(),
                wing.getActive());
    }

    @Override
    public void deleteWing(Long wingId) {

        LoggedInUser currentUser = SecurityUtils.getCurrentUser();

        Society society = societyRepository.findById(currentUser.getSocietyId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Society",
                                "Id",
                                currentUser.getSocietyId().toString()));

        Wing wing = wingRepository
                .findByWingIdAndSocietyAndActiveTrue(wingId, society)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Wing",
                                "Wing Id",
                                wingId.toString()));

        wing.setActive(false);

        wingRepository.save(wing);
    }
}
