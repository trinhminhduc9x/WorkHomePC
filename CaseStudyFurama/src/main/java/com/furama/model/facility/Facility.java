package com.furama.model.facility;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.furama.model.contract.Contract;
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
public class Facility {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private int area;
    private double cost;
    private int maxPeople;

//    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "rentType", referencedColumnName = "id")
    private RentType rentType;

//    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "facilityType", referencedColumnName = "id")
    private FacilityType facilityType;

    private String standardRoom;
    private String descriptionOtherConvenience;
    private double poolArea;
    private int numberOfFloors;
    private String facilityFree;

    @JsonBackReference
    @OneToMany(mappedBy = "facility")
    private List<Contract> contracts;
}
