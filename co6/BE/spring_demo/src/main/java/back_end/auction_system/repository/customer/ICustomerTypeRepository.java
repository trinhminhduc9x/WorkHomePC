package case_study.furama_resort.repository.customer;

import case_study.furama_resort.model.customer.CustomerType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ICustomerTypeRepository extends JpaRepository<CustomerType, Integer> {

    @Query(value = "select * from customer_type where status = 1", nativeQuery = true)
    List<CustomerType> getCustomerTypes();
}
