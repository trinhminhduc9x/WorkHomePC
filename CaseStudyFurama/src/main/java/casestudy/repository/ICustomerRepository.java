package casestudy.repository;

import casestudy.model.customer.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ICustomerRepository extends JpaRepository<Customer,Integer> {
    @Query(value = " select *  from customer join customer_type on customer.customer_type_id = customer_type.id where customer.name like :name and customer.email like :email and customer_type.name like :customerTypeName", nativeQuery = true)
    Page<Customer> findAllByNameAndEmailAndCustomerType(Pageable pageable, @Param("name") String name,@Param("email") String email,@Param("customerTypeName") String customerTypeName);

    @Query(value = " select *  from customer where name like :name and email like :email ", nativeQuery = true)
    Page<Customer> findAllByNameAndEmail(Pageable pageable, @Param("name") String name,@Param("email") String email);

    @Query(value = " select *  from customer where name like :name and email like :email and customer_type_id like :CustomerTypeID", nativeQuery = true)
    Page<Customer> findAllByNameAndEmailAndCustomerTypeID(Pageable pageable, @Param("name") String name,@Param("email") String email,@Param("CustomerTypeID") String CustomerTypeID);

    @Query(value = " select * from customer where name like :name", nativeQuery = true)
    Page<Customer> findAllByName(Pageable pageable, @Param("name") String name);

    @Query(value = " select * from customer where category_id = :id ", nativeQuery = true)
    List<Customer> findListById(@Param("id") Integer id);

    @Query(value = " UPDATE customer SET id_delete = '1' where id = :id ", nativeQuery = true)
    List<Customer> deleteById_delete(@Param("id") Integer id);

}
