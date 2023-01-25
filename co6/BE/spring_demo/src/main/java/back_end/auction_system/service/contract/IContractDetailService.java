package case_study.furama_resort.service.contract;

import case_study.furama_resort.model.contract.ContractDetail;
import case_study.furama_resort.model.dto.contract.ContractDetailAttachFacilityDto;

import java.util.List;

public interface IContractDetailService {

    List<ContractDetail> findAll();

    void remove(int id);

    void save(ContractDetail contractDetail);

    List<ContractDetailAttachFacilityDto> findByContractId(int id);

    List<ContractDetailAttachFacilityDto> findByCustomer(int id);
}
