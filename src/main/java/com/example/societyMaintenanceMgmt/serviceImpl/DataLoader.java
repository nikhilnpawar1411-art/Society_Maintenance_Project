package com.example.societyMaintenanceMgmt.serviceImpl;

import com.example.societyMaintenanceMgmt.entity.Society;
import com.example.societyMaintenanceMgmt.entity.User;
import com.example.societyMaintenanceMgmt.repository.SocietyRepository;
import com.example.societyMaintenanceMgmt.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataLoader {

    private final UserRepository repo;
    private final SocietyRepository socRepo;

    private final PasswordEncoder encoder;

    @PostConstruct
    public void load1() {
        if (repo.count() == 0) {
            Society soc = new Society();
            soc.setSocietyId(1L);
            soc.setSocietyName("Vishnushree");
            soc.setSocietyAddress("ShreeNagar");
            soc.setSocietyRegistrationNumber("REG/1999/2000");

            socRepo.save(soc);
        }
    }

    @PostConstruct
    public void load() {
        if (repo.count() == 0) {
            User user = new User();
            user.setLoginId("GuruDatta");
            user.setEmail("admin@test.com");
            user.setPassword(encoder.encode("password"));
            user.setRole("ADMIN");
            user.setSocietyId(1L);
            user.setName("Nikhil");
            repo.save(user);
        }
    }


}
