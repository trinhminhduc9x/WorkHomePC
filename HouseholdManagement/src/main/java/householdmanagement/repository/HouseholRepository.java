package householdmanagement.repository;

import householdmanagement.dtoView.HouseholView;
import householdmanagement.model.Househol;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HouseholRepository extends JpaRepository<Househol,Integer> {

//    @Query(value = "select househol.id ,member.name,househol.number_member,househol.start_day,househol.address from househol join member on househol.member_id = member.id where member.member_type_id = 1 ",nativeQuery = true)
//    Page<HouseholView> showPageT(Pageable pageable);


    @Query(value = "SELECT * FROM househol.househol JOIN househol.member on househol.member_id = member.id_member",nativeQuery = true)
    Page<HouseholView> showPage(Pageable pageable);

}
