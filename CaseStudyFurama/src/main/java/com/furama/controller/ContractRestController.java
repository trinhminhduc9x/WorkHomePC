package com.furama.controller;

import com.furama.model.contract.Contract;
import com.furama.service.IContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = "/contractRest")
public class ContractRestController {
    @Autowired
    private IContractService iContractService;

    @GetMapping
    public ResponseEntity<Page<Contract>> viewList(@RequestParam(defaultValue = "") String name,
                                                   @PageableDefault(size = 5)Pageable pageable){
        Page<Contract> contracts = this.iContractService.findByCustomer(name, pageable);
        if (!contracts.hasContent()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(contracts, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Contract> viewContract(@PathVariable int id){
        Contract contract = this.iContractService.findById(id);
        if (contract == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(contract, HttpStatus.OK);
    }
}
