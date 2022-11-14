package com.example.hokhau.repository;



import com.example.hokhau.model.SoHoKhau;
import com.example.hokhau.model.ThanhVien;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IThanhVienRepository extends JpaRepository<ThanhVien, Integer> {
    @Query(value = " select * from thanh_vien where so_ho_khau = :hoKhauId ", nativeQuery = true)
    List<ThanhVien> findListById(@Param("hoKhauId") Integer hoKhauId);

}
