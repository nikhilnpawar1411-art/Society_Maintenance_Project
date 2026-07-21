package com.example.societyMaintenanceMgmt.serviceImpl;

import com.example.societyMaintenanceMgmt.dto.SocietyResponseDto;
import com.example.societyMaintenanceMgmt.entity.Society;
import com.example.societyMaintenanceMgmt.exception.ResourceNotFoundException;
import com.example.societyMaintenanceMgmt.repository.SocietyRepository;
import com.example.societyMaintenanceMgmt.service.ISocietyService;
import com.example.societyMaintenanceMgmt.utility.LoggedInUser;
import com.example.societyMaintenanceMgmt.utility.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SocietyServiceImpl implements ISocietyService {

    private final SocietyRepository societyRepository;

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public Society updateSociety(Society request) {
        LoggedInUser currentUser = SecurityUtils.getCurrentUser();
        Long societyId = currentUser.getSocietyId();

        Society society = societyRepository.findById(societyId)
                .orElseThrow(() -> new ResourceNotFoundException("User","Login Id", String.valueOf(societyId)));

        society.setSocietyAddress(request.getSocietyAddress());
        society.setSocietyName(request.getSocietyName());
        society.setSocietyRegistrationNumber(request.getSocietyRegistrationNumber());

        return societyRepository.save(society);
    }

    @Override
    public SocietyResponseDto getSociety() {
        SocietyResponseDto societyResponseDto=new SocietyResponseDto();
        LoggedInUser loggedInUser = SecurityUtils.getCurrentUser();

        Society society= societyRepository.findById(loggedInUser.getSocietyId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Society",
                                "Society Id",
                                loggedInUser.getSocietyId().toString()));
        societyResponseDto.setSocietyName(society.getSocietyName());
        societyResponseDto.setSocietyRegistrationNumber(society.getSocietyRegistrationNumber());
        societyResponseDto.setSocietyAddress(society.getSocietyAddress());
        societyResponseDto.setActive(society.isActive());

        return societyResponseDto;
    }
}
