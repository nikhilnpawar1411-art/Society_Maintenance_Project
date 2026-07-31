package com.example.societyMaintenanceMgmt.controller;

import com.example.societyMaintenanceMgmt.dto.UserRequestDto;
import com.example.societyMaintenanceMgmt.dto.UserResponseDto;
import com.example.societyMaintenanceMgmt.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    IUserService userService;

    @PostMapping
    public UserResponseDto createUser(
            @RequestBody UserRequestDto dto){

        return userService.createUser(dto);
    }

    @GetMapping
    public List<UserResponseDto> getAllUsersFromSociety(){

        return userService.getAllUsersFromSociety();
    }

    @GetMapping("/{id}")
    public UserResponseDto getUser(
            @PathVariable Long id){

        return userService.getUser(id);
    }

    @PutMapping("/{id}")
    public UserResponseDto updateUser(
            @PathVariable Long id,
            @RequestBody UserRequestDto dto){

        return userService.updateUser(id,dto);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(
            @PathVariable Long id){

        userService.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully.");
    }
}
