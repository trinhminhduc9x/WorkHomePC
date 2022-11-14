package com.example.hokhau.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class SoHoKhau {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String maHoKhau;
    private String name;
    private String numberPeople;
    private String dayStar;
    private  String address;

    @OneToMany(mappedBy = "soHoKhau",cascade = CascadeType.REMOVE)
    @JsonBackReference
    private List<ThanhVien> thanhViens;

}
