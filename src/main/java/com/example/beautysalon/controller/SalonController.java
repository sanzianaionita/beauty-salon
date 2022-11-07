package com.example.beautysalon.controller;


import com.example.beautysalon.dto.SalonDTO;
import com.example.beautysalon.model.Salon;
import com.example.beautysalon.service.SalonService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/salon")
public class SalonController {
    private final SalonService salonService;

    public SalonController(SalonService salonService) {
        this.salonService = salonService;
    }

    @GetMapping("")
    public List<SalonDTO> getSalons() {
        return salonService.getSalons();
    }

    @GetMapping("/by-location")
    public List<SalonDTO> getSalonsByLocation(@RequestParam UUID locationId) {
        return salonService.getSalonsByLocation(locationId);
    }

    @GetMapping("/by-name")
    public List<SalonDTO> getSalonsBySalonName(@RequestParam String salonName) {
        System.out.println("test");
        return salonService.getSalonsBySalonName(salonName);
    }

    @PostMapping("")
    public SalonDTO createSalon(@RequestBody Salon salon) {
        return salonService.createSalon(salon);
    }
}
