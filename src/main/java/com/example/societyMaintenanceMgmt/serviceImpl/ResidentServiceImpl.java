package com.example.societyMaintenanceMgmt.serviceImpl;

import com.example.societyMaintenanceMgmt.dto.ResidentRequestDto;
import com.example.societyMaintenanceMgmt.dto.ResidentResponseDto;
import com.example.societyMaintenanceMgmt.entity.Flat;
import com.example.societyMaintenanceMgmt.entity.Resident;
import com.example.societyMaintenanceMgmt.entity.Society;
import com.example.societyMaintenanceMgmt.exception.AlreadyExistException;
import com.example.societyMaintenanceMgmt.exception.ResourceNotFoundException;
import com.example.societyMaintenanceMgmt.repository.FlatRepository;
import com.example.societyMaintenanceMgmt.repository.ResidentRepository;
import com.example.societyMaintenanceMgmt.repository.SocietyRepository;
import com.example.societyMaintenanceMgmt.service.IResidentService;
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
public class ResidentServiceImpl implements IResidentService {

    @Autowired
    private final ResidentRepository residentRepository;
    @Autowired
    private final FlatRepository flatRepository;
    @Autowired
    private final SocietyRepository societyRepository;

    @Override
    public ResidentResponseDto createResident(ResidentRequestDto request) {

        LoggedInUser user = SecurityUtils.getCurrentUser();

        Society society = societyRepository.findBySocietyId(user.getSocietyId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Society",
                        "Society Id",
                        user.getSocietyId().toString()));

        Flat flat = flatRepository.findByFlatIdAndActiveTrue(request.getFlatId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Flat",
                        "Flat Id",
                        request.getFlatId().toString()));

        if (!flat.getSocietyId().equals(society.getSocietyId())) {
            throw new RuntimeException("Flat does not belong to your society.");
        }

        if (Boolean.TRUE.equals(request.getPrimaryResident())
                && residentRepository.existsByFlatIdAndResidentTypeAndPrimaryResidentTrueAndActiveTrue(
                request.getFlatId(),
                request.getResidentType())) {

            throw new AlreadyExistException(
                    "Primary " + request.getResidentType() + " already exists.");
        }

        Resident resident = new Resident();

        resident.setSocietyId(society.getSocietyId());
        resident.setFlatId(request.getFlatId());
        resident.setResidentName(request.getResidentName());
        resident.setMobileNumber(request.getMobileNumber());
        resident.setEmail(request.getEmail());
        resident.setResidentType(request.getResidentType());
        resident.setPrimaryResident(request.getPrimaryResident());
        resident.setActive(true);

        resident = residentRepository.save(resident);

        return map(resident);
    }

    @Override
    public List<ResidentResponseDto> getAllResidents() {

        Long societyId = SecurityUtils.getCurrentUser().getSocietyId();

        return residentRepository.findBySocietyIdAndActiveTrue(societyId)
                .stream()
                .map(this::map)
                .toList();
    }

    @Override
    public ResidentResponseDto getResident(Long residentId) {

        Resident resident = residentRepository
                .findByResidentIdAndActiveTrue(residentId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Resident",
                        "Resident Id",
                        residentId.toString()));

        return map(resident);
    }

    @Override
    public ResidentResponseDto updateResident(Long residentId,
                                              ResidentRequestDto request) {

        Resident resident = residentRepository
                .findByResidentIdAndActiveTrue(residentId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Resident",
                        "Resident Id",
                        residentId.toString()));

        resident.setResidentName(request.getResidentName());
        resident.setMobileNumber(request.getMobileNumber());
        resident.setEmail(request.getEmail());
        resident.setResidentType(request.getResidentType());
        resident.setPrimaryResident(request.getPrimaryResident());

        resident = residentRepository.save(resident);

        return map(resident);
    }

    @Override
    public void deleteResident(Long residentId) {

        Resident resident = residentRepository
                .findByResidentIdAndActiveTrue(residentId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Resident",
                        "Resident Id",
                        residentId.toString()));

        resident.setActive(false);

        residentRepository.save(resident);
    }

    private ResidentResponseDto map(Resident resident) {

        return ResidentResponseDto.builder()
                .residentId(resident.getResidentId())
                .flatId(resident.getFlatId())
                .residentName(resident.getResidentName())
                .mobileNumber(resident.getMobileNumber())
                .email(resident.getEmail())
                .residentType(resident.getResidentType())
                .primaryResident(resident.getPrimaryResident())
                .active(resident.getActive())
                .build();
    }
}