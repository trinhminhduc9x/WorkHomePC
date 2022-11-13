package com.furama.controller;

import com.furama.model.facility.Facility;
import com.furama.service.IFacilityService;
import com.furama.service.IFacilityTypeService;
import com.furama.service.IRentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = "/facility")
public class FacilityRestController {
    @Autowired
    private IFacilityTypeService iFacilityTypeService;

    @Autowired
    private IRentTypeService iRentTypeService;

    @Autowired
    private IFacilityService iFacilityService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Facility> showDetail(@PathVariable Integer id){
        Facility facility = this.iFacilityService.findById(id);
        if(facility== null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(facility, HttpStatus.OK);
    }
}
