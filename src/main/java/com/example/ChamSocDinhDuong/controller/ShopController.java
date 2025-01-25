package com.example.ChamSocDinhDuong.controller;

import com.example.ChamSocDinhDuong.model.Shop;
import com.example.ChamSocDinhDuong.repository.ShopRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/v1/shop")
public class ShopController {

    private final ShopRepository shopRepository;

    public ShopController(ShopRepository shopRepository) {
        this.shopRepository = shopRepository;
    }

    @GetMapping("")
    public ResponseEntity<List<Shop>> getShopInfo() {
        return ResponseEntity.ok(shopRepository.findAll());
    }

    @PostMapping("/{id}")
    public ResponseEntity<Shop> editShopInfo(@PathVariable String id, @RequestBody Shop shop) {

        Shop shopUpdate = shopRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Shop'id is not found "+ id)
        );

        shopUpdate.setName(shop.getName());
        shopUpdate.setEmail(shop.getEmail());
        shopUpdate.setPhoneNumber(shop.getPhoneNumber());
        shopUpdate.setAddress(shop.getAddress());
        shopUpdate.setFacebookAddress(shop.getFacebookAddress());
        shopUpdate.setYoutubeAddress(shop.getYoutubeAddress());

        return ResponseEntity.ok(shopRepository.save(shopUpdate));
    }
}
