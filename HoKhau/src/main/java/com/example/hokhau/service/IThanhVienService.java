package com.example.hokhau.service;


import com.example.hokhau.model.SoHoKhau;
import com.example.hokhau.model.ThanhVien;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IThanhVienService extends IGeneralService<ThanhVien> {


    @Override
    List<ThanhVien> findListAll();

    @Override
    List<ThanhVien> findListById(Integer id);

    @Override
    void save(ThanhVien thanhVien);

    @Override
    ThanhVien findById(Integer id);

    @Override
    void update(ThanhVien thanhVien);

    @Override
    void remove(Integer id);

    @Override
    Page<ThanhVien> findPageNameAll(Pageable pageable, String name);

    Page<ThanhVien> findAllByNameAndAndThanhVienAndSoHoKhau(Pageable pageable, String name, Integer thanh_vien, Integer so_ho_khau);


    List<ThanhVien> findLishokhauIdId( Integer hokhauId);

}
