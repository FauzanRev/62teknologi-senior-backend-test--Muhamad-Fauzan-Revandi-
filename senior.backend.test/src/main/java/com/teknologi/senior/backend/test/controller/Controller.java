package com.teknologi.senior.backend.test.controller;

import com.teknologi.senior.backend.test.model.entity.Business;
import com.teknologi.senior.backend.test.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/v1")
public class Controller {
    @Autowired
    BusinessService service;

    @GetMapping("/get")
    public List<Business> getAll(
    ) throws ChangeSetPersister.NotFoundException {
        return service.getListBusiness();
    }

    @PostMapping("create")
    public ResponseEntity<String> createBusiness(
            @RequestBody Business newBusiness
    ) {
        try {
            Boolean isCreated = service.createdBusiness(newBusiness);

            if (isCreated) {
                return ResponseEntity.ok("Business has been created successfully.");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to create Business.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create");
        }
    }

    @PutMapping("update/{id}")
    public ResponseEntity<String> updateBusinessById(
            @PathVariable Integer id,
            @RequestBody Business updatedBusiness
    ) {
        try {
            Boolean isUpdated = service.updateBusinessById(id, updatedBusiness);

            if (isUpdated) {
                return ResponseEntity.ok("Business with ID " + id + " updated successfully.");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Business with ID " + id + " not found.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Business ID Not Found");
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteBusinessById(
            @PathVariable Integer id
    ) {
        try {

            Boolean isDeleted = service.deleteBusinessById(id);

            if (isDeleted) {
                return ResponseEntity.ok("Business with ID " + id + " delete successfully.");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Business with ID " + id + " not found.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Business with ID Not Found");
        }
    }

    @GetMapping("get/search")
    public List<Business> getListBusinessBySearch(@RequestBody String body) throws Exception {
        return service.getListBusinessBySearch(body);
    }
}
