package projectbackend.repository.customer;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import projectbackend.dto.customer.ICustomerDto;
import projectbackend.dto.customer.ICustomerStatementDto;
import projectbackend.model.customer.Customer;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface ICustomerRepository extends JpaRepository<Customer, Integer> {

    @Modifying
    @Query(value = "update account set password = :password where accountname = :accountname and is_delete =0", nativeQuery = true)
    void setPassword(@Param("accountname") String accountname, @Param("password") String password);


    @Modifying
    @Transactional
    @Query(value = "call sign_up(:#{#c.account.accountname},:#{#c.account.password},:#{#c.nameCustomer} ,:#{#c.dateOfBirthCustomer},:#{#c.genderCustomer},:#{#c.idCardCustomer}," +
            ":#{#c.emailCustomer},:#{#c.addressCustomer})", nativeQuery = true)
    void saveCustomer(@Param("c") Customer customer);


}