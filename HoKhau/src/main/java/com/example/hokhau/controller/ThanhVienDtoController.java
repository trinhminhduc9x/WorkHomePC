package com.example.hokhau.controller;

import com.example.hokhau.model.SoHoKhau;
import com.example.hokhau.model.ThanhVien;
import com.example.hokhau.service.ISoHoKhauService;
import com.example.hokhau.service.IThanhVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/thanhVienDto")
public class ThanhVienDtoController {
    @Autowired
    private IThanhVienService iThanhVienService;

    @GetMapping("/view/{id}")
    public ResponseEntity<List<ThanhVien>> viewContractDetail(@PathVariable int id) {
        List<ThanhVien> thanhVienList =iThanhVienService.findLishokhauIdId(id);
        if (thanhVienList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(thanhVienList, HttpStatus.OK);
    }
}
