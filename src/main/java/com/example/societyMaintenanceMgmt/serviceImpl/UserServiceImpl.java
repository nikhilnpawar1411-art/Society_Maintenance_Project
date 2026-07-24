package com.example.societyMaintenanceMgmt.serviceImpl;

import com.example.societyMaintenanceMgmt.dto.UserRequestDto;
import com.example.societyMaintenanceMgmt.dto.UserResponseDto;
import com.example.societyMaintenanceMgmt.entity.User;
import com.example.societyMaintenanceMgmt.exception.AlreadyExistException;
import com.example.societyMaintenanceMgmt.exception.ResourceNotFoundException;
import com.example.societyMaintenanceMgmt.repository.UserRepository;
import com.example.societyMaintenanceMgmt.service.IUserService;
import com.example.societyMaintenanceMgmt.utility.LoggedInUser;
import com.example.societyMaintenanceMgmt.utility.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements IUserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserResponseDto createUser(UserRequestDto request) {

        LoggedInUser currentUser = SecurityUtils.getCurrentUser();

        if(userRepository.existsByLoginIdAndSocietyId(request.getLoginId(),currentUser.getSocietyId())){
            throw new AlreadyExistException("Login Id already exists");
        }

        //Temporarily commented bcz society may have only 1 mail id
       /* if(userRepository.existsByEmail(request.getEmail())){
            throw new RuntimeException("Email already exists");
        }
        */

        User user = new User();

        user.setSocietyId(currentUser.getSocietyId());
        user.setLoginId(request.getLoginId());
        user.setUserName(request.getUserName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());
        user.setIsActive(true);

        user = userRepository.save(user);

        return map(user);
    }

    @Override
    public List<UserResponseDto> getAllUsersFromSociety() {

        Long societyId = SecurityUtils.getCurrentUser().getSocietyId();

        return userRepository
                .findBySocietyIdAndIsActiveTrue(societyId)
                .stream()
                .map(this::map)
                .toList();
    }

    @Override
    public UserResponseDto getUser(Long userId) {

        Long societyId = SecurityUtils.getCurrentUser().getSocietyId();

        User user = userRepository.findByUserId(userId)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        if(!user.getSocietyId().equals(societyId)){
            throw new RuntimeException("Access denied");
        }

        return map(user);
    }

    @Override
    public UserResponseDto updateUser(Long userId,
                                      UserRequestDto request) {

        Long societyId = SecurityUtils.getCurrentUser().getSocietyId();

        User user = userRepository.findByUserId(userId)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        if(!user.getSocietyId().equals(societyId)){
            throw new RuntimeException("Access denied");
        }
        user.setLoginId(request.getLoginId());
        user.setUserName(request.getUserName());
        user.setEmail(request.getEmail());
        user.setRole(request.getRole());

        if(request.getPassword()!=null &&
                !request.getPassword().isBlank()){

            user.setPassword(passwordEncoder.encode(request.getPassword()));
        }

        return map(userRepository.save(user));
    }

    @Override
    public void deleteUser(Long userId) {

        Long societyId = SecurityUtils.getCurrentUser().getSocietyId();

        User user = userRepository.findByUserId(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User","User id",""));

        if(!user.getSocietyId().equals(societyId)){
            throw new RuntimeException("Access denied");
        }

        user.setIsActive(false);

        userRepository.save(user);
    }

    private UserResponseDto map(User user){

        return UserResponseDto.builder()
                .userId(user.getUserId())
                .loginId(user.getLoginId())
                .userName(user.getUserName())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }
}
