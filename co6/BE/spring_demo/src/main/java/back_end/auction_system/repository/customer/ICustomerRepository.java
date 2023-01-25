package case_study.furama_resort.repository.customer;

import case_study.furama_resort.model.customer.Customer;
import org.hibernate.Session;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public interface ICustomerRepository extends JpaRepository<Customer, Integer> {

    @Query(value = "select c.* from `customer` c inner join `customer_type` ct " +
            "on c.customer_type_id = ct.id " +
            "where c.name like %:name% and c.email like %:email% and ct.name like %:typeName% and c.status= 1", nativeQuery = true)
    Page<Customer> findByNameAndEmailAndCustomerType(@Param("name") String name,
                                                     @Param("email") String email,
                                                     @Param("typeName") String typeName,
                                                     Pageable pageable);

    @Query(value = "select * from `customer` where id=:id and status = 1", nativeQuery = true)
    Optional<Customer> findById(@Param("id") int id);

    @Transactional
    @Modifying
    @Query(value = "update customer set status = 0 where id = :id", nativeQuery = true)
    void remove(@Param("id") int id);

    @Transactional
    @Modifying
    @Query(value = "update customer set status = 0 where id in :ids", nativeQuery = true)
    void remove1(@Param("ids") List<Integer> ids);

    @Query(value = "select * from `customer` where status= 1", nativeQuery = true)
    List<Customer> findAllCustomer();

    @Query(value = "select cu.* from `customer`cu " +
            "inner join `contract` co on cu.id = co.customer_id " +
            "inner join `customer_type` ct on cu.customer_type_id = ct.id " +
            "where cu.name like %:name% and cu.email like %:email% and ct.name like %:typeName% and cu.status= 1 and co.end_date > now() " +
            "group by cu.id ", nativeQuery = true)
    Page<Customer> findCustomerUsingService(@Param("name") String name,
                                            @Param("email") String email,
                                            @Param("typeName") String typeName,
                                            Pageable pageable);

//    @Transactional
//    @Modifying
//    @Query(value = "INSERT INTO customer ( name, date_of_birth,gender,id_card, phone_number, email, address, customer_type_id, status)\n" +
//            "VALUE ( :name, :date_of_birth,:gender,:id_card, :phone_number, :email, :address, :customer_type_id, :status)", nativeQuery = true)
//    void save(@Param("name") String name,
//              @Param("date_of_birth") Date dateOfBirth,
//              @Param("gender") int gender,
//              @Param("id_card") String idCard,
//              @Param("phone_number") String phoneNumber,
//              @Param("email") String email,
//              @Param("address") String address,
//              @Param("customer_type_id") int customerTypeId,
//              @Param("status") int status);

}
