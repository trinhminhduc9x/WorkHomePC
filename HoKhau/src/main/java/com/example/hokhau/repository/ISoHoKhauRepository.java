package com.example.hokhau.repository;


import com.example.hokhau.model.SoHoKhau;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

@Transactional
public interface ISoHoKhauRepository extends JpaRepository<SoHoKhau, Integer> {

    @Query(value = " select *  from so_ho_khau where name like %:name% and ma_ho_khau like %:maHoKhau% and number_people like %:numberPeople%", nativeQuery = true)
    Page<SoHoKhau> findAllByNameAndAndThanhVienAndSoHoKhau(Pageable pageable, @Param("name") String name, @Param("maHoKhau") String maHoKhau, @Param("numberPeople") String numberPeople);


}
