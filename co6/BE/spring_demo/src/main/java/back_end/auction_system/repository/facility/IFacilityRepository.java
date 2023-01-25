package case_study.furama_resort.repository.facility;

import case_study.furama_resort.model.employee.Employee;
import case_study.furama_resort.model.facilities.Facility;
import case_study.furama_resort.model.facilities.FacilityType;
import case_study.furama_resort.model.facilities.RentType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface IFacilityRepository extends JpaRepository<Facility, Integer> {

    @Query(value = "select f.* from `facility` f inner join `facility_type` t " +
            "on f.facility_type_id = t.id " +
            "where f.name like %:name% and t.name like %:typeName% and f.status= 1", nativeQuery = true)
    Page<Facility> findByNameAndFacilityType(@Param("name") String name,
                                             @Param("typeName") String typeName,
                                             Pageable pageable);

    @Query(value = "select * from `facility` where id=:id and status = 1", nativeQuery = true)
    Optional<Facility> findById(@Param("id") int id);

    @Transactional
    @Modifying
    @Query(value = "update facility set status = 0 where id = :id", nativeQuery = true)
    void remove(@Param("id") int id);

//    @Transactional
//    @Modifying
//    @Query(value =
//            "INSERT INTO facility ( `name`, `area`, `cost`, `max_people`, `standard_room`, `description_other_convenience`, `pool_area`, `number_of_floors`, `facility_free`, `rent_type_id`, `facility_type_id`, `status`)" +
//            "VALUE (:name, :area, :cost, :max_people, :standard_room, :description_other_convenience, :pool_area, :number_of_floors, :facility_free, :rent_type_id, :facility_type_id, :status)"
//            , nativeQuery = true)
//    void save(@Param("name") String name,
//              @Param("area") int area,
//              @Param("cost") double cost,
//              @Param("max_people") int maxPeople,
//              @Param("standard_room") String standardRoom,
//              @Param("description_other_convenience") String descriptionOtherConvenience,
//              @Param("pool_area") double poolArea,
//              @Param("number_of_floors") int numberOfFloors,
//              @Param("facility_free") String facilityFree,
//              @Param("rent_type_id") RentType rentType,
//              @Param("facility_type_id") FacilityType facilityType,
//              @Param("status") int status);


    @Query(value = "select * from `facility` where status= 1", nativeQuery = true)
    List<Facility> findAll();
}
