package com.example.societyMaintenanceMgmt.serviceImpl;

import com.example.societyMaintenanceMgmt.dto.FlatRequestDto;
import com.example.societyMaintenanceMgmt.dto.FlatResponseDto;
import com.example.societyMaintenanceMgmt.entity.Flat;
import com.example.societyMaintenanceMgmt.entity.Society;
import com.example.societyMaintenanceMgmt.exception.AlreadyExistException;
import com.example.societyMaintenanceMgmt.exception.ResourceNotFoundException;
import com.example.societyMaintenanceMgmt.repository.FlatRepository;
import com.example.societyMaintenanceMgmt.repository.SocietyRepository;
import com.example.societyMaintenanceMgmt.repository.WingRepository;
import com.example.societyMaintenanceMgmt.service.IFlatService;
import com.example.societyMaintenanceMgmt.utility.LoggedInUser;
import com.example.societyMaintenanceMgmt.utility.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class FlatServiceImpl implements IFlatService {

    @Autowired
    FlatRepository flatRepository;

    @Autowired
    SocietyRepository societyRepository;

    @Autowired
    WingRepository wingRepository;

    @Override
    public FlatResponseDto createFlat(FlatRequestDto request) {

        LoggedInUser loggedInUser = SecurityUtils.getCurrentUser();

        Society society = societyRepository.findBySocietyId(loggedInUser.getSocietyId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Society",
                        "Society Id",
                        loggedInUser.getSocietyId().toString()));
        if (flatRepository.existsBySocietyIdAndFlatNumberAndActiveTrue(
                society.getSocietyId(),
                request.getFlatNumber())) {

            throw new AlreadyExistException( request.getFlatNumber()+" Flat already exists.");
        }
        if (Boolean.TRUE.equals(society.getHasWing())) {

            if (request.getWingId() == null) {
                throw new RuntimeException("Wing is mandatory.");
            }

            wingRepository.findByWingIdAndSocietyAndActiveTrue(
                            request.getWingId(), society)
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Wing",
                            "Wing Id",
                            request.getWingId().toString()));

        } else {

            if (request.getWingId() != null) {
                throw new RuntimeException("This society does not have wings.");
            }
        }



        Flat flat = new Flat();

        flat.setSocietyId(society.getSocietyId());
        flat.setWingId(request.getWingId());
        flat.setFlatNumber(request.getFlatNumber());
        flat.setAreaSqFt(request.getAreaSqFt());
        flat.setFloorNumber(request.getFloorNumber());
        flat.setActive(true);

        flat = flatRepository.save(flat);

        return map(flat);
    }

    @Override
    @Transactional(readOnly = true)
    public List<FlatResponseDto> getAllFlats() {

        LoggedInUser loggedInUser = SecurityUtils.getCurrentUser();

        return flatRepository
                .findBySocietyIdAndActiveTrue(loggedInUser.getSocietyId())
                .stream()
                .map(this::map)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public FlatResponseDto getFlat(Long flatId) {

        LoggedInUser loggedInUser = SecurityUtils.getCurrentUser();

        Flat flat = flatRepository.findByFlatIdAndActiveTrue(flatId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Flat",
                                "Flat Id",
                                flatId.toString()));

        if (!flat.getSocietyId().equals(loggedInUser.getSocietyId())) {
            throw new RuntimeException("Access denied.");
        }

        return map(flat);
    }

    @Override
    public FlatResponseDto updateFlat(Long flatId,
                                      FlatRequestDto request) {

        LoggedInUser loggedInUser = SecurityUtils.getCurrentUser();

        Flat flat = flatRepository.findByFlatIdAndActiveTrue(flatId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Flat",
                                "Flat Id",
                                flatId.toString()));

        if (!flat.getSocietyId().equals(loggedInUser.getSocietyId())) {
            throw new RuntimeException("Access denied.");
        }

        flat.setWingId(request.getWingId());
        flat.setFlatNumber(request.getFlatNumber());
        flat.setAreaSqFt(request.getAreaSqFt());
        flat.setFloorNumber(request.getFloorNumber());

        flat = flatRepository.save(flat);

        return map(flat);
    }

    @Override
    public void deleteFlat(Long flatId) {

        LoggedInUser loggedInUser = SecurityUtils.getCurrentUser();

        Flat flat = flatRepository.findByFlatIdAndActiveTrue(flatId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Flat",
                                "Flat Id",
                                flatId.toString()));

        if (!flat.getSocietyId().equals(loggedInUser.getSocietyId())) {
            throw new RuntimeException("Access denied.");
        }

        flat.setActive(false);

        flatRepository.save(flat);
    }

    private FlatResponseDto map(Flat flat) {

        return FlatResponseDto.builder()
                .flatId(flat.getFlatId())
                .wingId(flat.getWingId())
                .flatNumber(flat.getFlatNumber())
                .areaSqFt(flat.getAreaSqFt())
                .floorNumber(flat.getFloorNumber())
                .active(flat.getActive())
                .build();
    }
}