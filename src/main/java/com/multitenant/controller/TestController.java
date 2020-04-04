package com.multitenant.controller;

import com.multitenant.model.ResponseModel;
import com.multitenant.repository.TestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/test")
public class TestController {

    @Autowired
    private TestRepo testRepo;

    @CrossOrigin
    @GetMapping(path = "/")
    public ResponseEntity<?> index() {
        return ResponseModel.success(testRepo.findAll());
    }
}
