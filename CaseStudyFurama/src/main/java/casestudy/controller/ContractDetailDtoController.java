package casestudy.controller;

import casestudy.model.contract.ContractDetail;
import casestudy.service.contract.IContracDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin("*")
@RestController
@RequestMapping("/contractdetaildto")
public class ContractDetailDtoController {
    @Autowired
    private IContracDetailService contracDetailService;

    @GetMapping("/viewContractDetail/{id}")
    public ResponseEntity<List<ContractDetail>> viewContractDetail(@PathVariable int id) {
        List<ContractDetail> contractDetailList =contracDetailService.findListcontractId(id);
        if (contractDetailList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(contractDetailList, HttpStatus.OK);
    }
}
