package com.example.societyMaintenanceMgmt.serviceImpl;

import com.example.societyMaintenanceMgmt.dto.LoginRequestDto;
import com.example.societyMaintenanceMgmt.dto.LoginResponseDto;
import com.example.societyMaintenanceMgmt.dto.SocietyRegistrationRequestDto;
import com.example.societyMaintenanceMgmt.entity.Society;
import com.example.societyMaintenanceMgmt.entity.User;
import com.example.societyMaintenanceMgmt.exception.AlreadyExistException;
import com.example.societyMaintenanceMgmt.exception.ResourceNotFoundException;
import com.example.societyMaintenanceMgmt.repository.*;
import com.example.societyMaintenanceMgmt.service.iAuthService;
import com.example.societyMaintenanceMgmt.utility.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements iAuthService {

    private final UserRepository userRepository;
    private final SocietyRepository societyRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;


    @Override
    public LoginResponseDto login(LoginRequestDto request) {


//        User user = userRepository.findByEmail(request.getEmail())
//                .orElseThrow(() -> new ResourceNotFoundException("User","Email", request.getEmail()));

        User user = userRepository.findByLoginId(request.getLoginId())
                .orElseThrow(() -> new ResourceNotFoundException("User","Login Id", request.getLoginId()));


//        System.out.println("ENCODED : "+passwordEncoder.encode("password"));
//        System.out.println(user.toString());
//        System.out.println("RAW: " + request.getPassword());
//        System.out.println("DB : " + user.getPassword());
//        System.out.println("MATCH: " + passwordEncoder.matches(
//                request.getPassword(),
//                user.getPassword()
//        ));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }
        String token=jwtUtil.generateToken(user.getUserId(), user.getSocietyId(), user.getRole());

        Society society = societyRepository.findBySocietyId(user.getSocietyId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Society Details", "Society Id", user.getSocietyId().toString()
                ));


        LoginResponseDto loginResponseDto=new LoginResponseDto();
        loginResponseDto.setToken(token);
        loginResponseDto.setRole(user.getRole());
        loginResponseDto.setUserId(user.getUserId());
        loginResponseDto.setUserName(user.getUserName());
        loginResponseDto.setSociety(society);

        return loginResponseDto;

    }

    @Transactional
    @Override
    public LoginResponseDto register(SocietyRegistrationRequestDto request) {

        if (societyRepository.existsBySocietyName(request.getSocietyName())) {
            throw new AlreadyExistException("Society already exists");
//            new ResourceNotFoundException("User","Login Id", request.getLoginId())
        }

        if (userRepository.existsByLoginId(request.getLoginId())) {
            throw new AlreadyExistException("Email already registered");
        }

        // 1️⃣ Create Society
        Society society = new Society();
        society.setSocietyName(request.getSocietyName());
        society.setSocietyAddress(request.getSocietyAddress());
        society.setSocietyRegistrationNumber(request.getSocietyRegistrationNumber());
        societyRepository.save(society);

        // 2️⃣ Create Admin User
        User user = new User();
        user.setLoginId(request.getLoginId());
        user.setEmail(request.getEmail());
        user.setUserName(request.getUserName());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole("Admin");
        user.setSocietyId(society.getSocietyId());

        userRepository.save(user);

        // 3️⃣ Generate Token
        String token=jwtUtil.generateToken(user.getUserId(), user.getSocietyId(), user.getRole());

        LoginResponseDto loginResponseDto=new LoginResponseDto();
        loginResponseDto.setToken(token);
        loginResponseDto.setRole(user.getRole());
        loginResponseDto.setUserId(user.getUserId());
        loginResponseDto.setUserName(user.getUserName());
        loginResponseDto.setSociety(society);

        return loginResponseDto;
    }


}
