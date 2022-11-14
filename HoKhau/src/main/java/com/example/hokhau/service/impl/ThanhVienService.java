package com.example.hokhau.service.impl;


import com.example.hokhau.model.ThanhVien;
import com.example.hokhau.repository.IThanhVienRepository;
import com.example.hokhau.service.IThanhVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThanhVienService implements IThanhVienService {

    @Autowired
    private IThanhVienRepository iThanhVienRepository;


    @Override
    public List<ThanhVien> findListAll() {
        return iThanhVienRepository.findAll();
    }

    @Override
    public List<ThanhVien> findListById(Integer id) {
        return null;
    }

    @Override
    public void save(ThanhVien thanhVien) {

    }

    @Override
    public ThanhVien findById(Integer id) {
        return null;
    }

    @Override
    public void update(ThanhVien thanhVien) {

    }

    @Override
    public void remove(Integer id) {

    }

    @Override
    public Page<ThanhVien> findPageNameAll(Pageable pageable, String name) {
        return null;
    }

    @Override
    public Page<ThanhVien> findAllByNameAndAndThanhVienAndSoHoKhau(Pageable pageable, String name, Integer thanh_vien, Integer so_ho_khau) {
        return null;
    }

    @Override
    public List<ThanhVien> findLishokhauIdId(Integer hokhauId) {
         return iThanhVienRepository.findListById(hokhauId);
    }



}
