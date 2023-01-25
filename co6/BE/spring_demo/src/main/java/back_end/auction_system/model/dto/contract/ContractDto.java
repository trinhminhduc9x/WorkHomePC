package case_study.furama_resort.model.dto.contract;

import case_study.furama_resort.model.contract.ContractDetail;
import case_study.furama_resort.model.customer.Customer;
import case_study.furama_resort.model.employee.Employee;
import case_study.furama_resort.model.facilities.Facility;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.LinkedList;
import java.util.Set;

public class ContractDto implements Validator {

    private int id;
    private String startDate;
    private String endDate;
    private String deposit;
    private int status = 1;
    private Set<ContractDetail> contractDetails;

    @NotNull(message = "Vui lòng chọn")
    private Employee employee;
    @NotNull(message = "Vui lòng chọn")
    private Customer customer;
    @NotNull(message = "Vui lòng chọn")
    private Facility facility;
    private LinkedList<Integer> quantities;
    private double total;

    public ContractDto() {
    }

    @Override
    public void validate(Object target, Errors errors) {
        ContractDto contractDto = (ContractDto) target;

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate startLocalDate = null;
        LocalDate endLocalDate = null;

        try {
            startLocalDate = LocalDate.parse(contractDto.getStartDate(), fmt);
            if ( startLocalDate == null || !startLocalDate.isAfter(LocalDate.now())) {
                errors.rejectValue("startDate", "", "Ngày bắt đầu phải ở tương lai");
            }
        } catch (DateTimeParseException e) {
            errors.rejectValue("startDate", "", "Ngày bắt đầu không đúng định dạng DD/mm/YYYY hoặc không đúng");
        }

        try {
            endLocalDate = LocalDate.parse(contractDto.getEndDate(), fmt);
            if ( endLocalDate == null || !endLocalDate.isAfter(LocalDate.now())) {
                errors.rejectValue("endDate", "", "Ngày kết thúc phải ở tương lai");
            }
        } catch (DateTimeParseException e) {
            errors.rejectValue("endDate", "", "Ngày bắt đầu không đúng định dạng DD/mm/YYYY hoặc không đúng");
        }

        if (startLocalDate == null || endLocalDate == null || !startLocalDate.isBefore(endLocalDate)) {
            errors.rejectValue("startDate", "", "Ngày bắt đầu và ngày kết thúc chưa đúng dòng thời gian");
            errors.rejectValue("endDate", "", "Ngày bắt đầu và ngày kết thúc chưa đúng dòng thời gian");
        }

        try {
            double num = Double.parseDouble(contractDto.getDeposit());
            if (num <= 0) {
                errors.rejectValue("deposit", "", "Tiền đặt cọc phải lớn hơn 0");
            }
        } catch (NumberFormatException e) {
            errors.rejectValue("deposit", "", "Tiền đặt cọc phải là một số dương");
        }

        for (Integer i : contractDto.getQuantities()) {
            if (i != null && i < 0) {
                errors.rejectValue("quantities", "", "Số lượng mỗi dịch vụ không được âm, vui lòng kiểm tra lại");
                break;
            }
        }
    }

    public ContractDto(int id, String startDate, String endDate, String deposit, int status, Set<ContractDetail> contractDetails, Employee employee, Customer customer, Facility facility) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.deposit = deposit;
        this.status = status;
        this.contractDetails = contractDetails;
        this.employee = employee;
        this.customer = customer;
        this.facility = facility;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getDeposit() {
        return deposit;
    }

    public void setDeposit(String deposit) {
        this.deposit = deposit;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Set<ContractDetail> getContractDetails() {
        return contractDetails;
    }

    public void setContractDetails(Set<ContractDetail> contractDetails) {
        this.contractDetails = contractDetails;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Facility getFacility() {
        return facility;
    }

    public void setFacility(Facility facility) {
        this.facility = facility;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public LinkedList<Integer> getQuantities() {
        return quantities;
    }

    public void setQuantities(LinkedList<Integer> quantities) {
        this.quantities = quantities;
    }

    public void getTotalCost() {
        this.total = this.facility == null ? 0 : Double.parseDouble(this.facility.getCost());
        if (this.contractDetails != null) {
            for (ContractDetail contractDetail : this.contractDetails) {
                this.total += contractDetail == null ? 0 : contractDetail.getQuantity() * contractDetail.getAttachFacility().getCost();
            }
        }
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

}
