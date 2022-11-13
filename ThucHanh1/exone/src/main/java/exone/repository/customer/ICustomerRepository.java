package exone.repository.customer;

import exone.model.customer.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ICustomerRepository extends JpaRepository<Customer,Integer> {

    @Query(value = " select *  from customer where name like :name and email like :email and customer_type_id like :CustomerTypeID", nativeQuery = true)
    Page<Customer> findAllByNameAndEmailAndCustomerTypeID(Pageable pageable, @Param("name") String name,@Param("email") String email,@Param("CustomerTypeID") String CustomerTypeID);

    @Query(value = " select * from customer.customer where category_id = :id ", nativeQuery = true)
    List<Customer> findListById(@Param("id") Integer id);


}