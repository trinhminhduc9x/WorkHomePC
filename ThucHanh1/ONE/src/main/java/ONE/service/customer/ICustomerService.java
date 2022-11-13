package ONE.service.customer;


import ONE.model.Customer;
import ONE.service.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICustomerService extends IGeneralService<Customer> {
    @Override
    List<Customer> findListAll();

    @Override
    List<Customer> findListById(Integer id);

    @Override
    void save(Customer customer);

    @Override
    Customer findById(Integer id);

    @Override
    void update(Customer customer);

    @Override
    Page<Customer> findPageNameAll(Pageable pageable, String name);

    @Override
    void remove(Integer id);
    void removeNew(Integer id);

    Page<Customer> findPageNameEmailCustomerType(Pageable pageable, String name,String email,String CustomerType);

    Page<Customer> findPageNameEmail(Pageable pageable, String name,String email);

    Page<Customer> findPageNameEmailType(Pageable pageable, String name,String email,String CustomerTypeID);

}
