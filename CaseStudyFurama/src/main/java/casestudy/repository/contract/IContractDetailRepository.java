package casestudy.repository.contract;

import casestudy.model.contract.ContractDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IContractDetailRepository extends JpaRepository<ContractDetail,Integer> {
}
