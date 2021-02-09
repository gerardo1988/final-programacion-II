package com.example.mutantefinal.controller;

import com.example.mutantefinal.dto.AdnDTO;
import com.example.mutantefinal.service.MutanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/elmutante")
public class MutanteController{

    @Autowired
    private MutanteService mutanteService;

    public MutanteController(MutanteService mutanteService) {
        this.mutanteService = mutanteService;
    }


    @PostMapping("")
    public ResponseEntity<?> post(@RequestBody AdnDTO adnDTO) {

        try{

            return mutanteService.analizaMutante(adnDTO);


        }catch (Exception e){

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":Error. Por favor intente más tarde.\"}");

        }
    }

}
