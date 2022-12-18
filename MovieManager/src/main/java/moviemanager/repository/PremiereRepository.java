package moviemanager.repository;

import moviemanager.model.Premiere;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PremiereRepository extends JpaRepository<Premiere,Integer> {

    @Query(value = " select * from premiere where number_premiere like %:numberPremiere% and day_premiere like %:dayPremiere% and movie_id like %:movieId% ", nativeQuery = true)
    Page<Premiere> findPageSearchAll(Pageable pageable, @Param("numberPremiere") String numberPremiere, @Param("dayPremiere") String dayPremiere, @Param("movieId") String movieId);



}
