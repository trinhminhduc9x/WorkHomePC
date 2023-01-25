package case_study.furama_resort.model.dto.facility_dto;

import case_study.furama_resort.model.facilities.FacilityType;
import case_study.furama_resort.model.facilities.RentType;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class FacilityDto implements Validator {


    private Integer id;

    @Pattern(regexp = "^\\p{Lu}[\\p{Ll}\\d]+(\\s\\p{Lu}[\\p{Ll}\\d]+)*$", message = "Tên chưa đúng định dạng! (Vd: Villa Beach2 )")
    private String name;
    private String area;
    private String cost;
    private String maxPeople;


    private String standardRoom;
    private String descriptionOtherConvenience;
    private String poolArea;
    private String numberOfFloors;
    private String facilityFree;

    private int status = 1;
    private RentType rentType;

    @NotNull(message = "Hãy chọn 1 dịch vụ")
    private FacilityType facilityType;

    public FacilityDto() {
    }

    public void dataFormat() {
        switch (this.getFacilityType().getId()) {
            case 1:
                this.facilityFree = null;
                break;
            case 2:
                this.facilityFree = null;
                this.poolArea = null;
                break;
            case 3:
                this.standardRoom = null;
                this.descriptionOtherConvenience = null;
                this.poolArea = null;
                this.numberOfFloors = null;
                break;
        }
    }

    @Override
    public void validate(Object target, Errors errors) {
        FacilityDto facilityDto = (FacilityDto) target;

        //Validate các trường chung
        try {
            int num = Integer.parseInt(facilityDto.getArea());
            if (num <= 0) {
                errors.rejectValue("area", "", "Diện tích phải lớn hơn 0");
            }
        } catch (NumberFormatException e) {
            errors.rejectValue("area", "", "Diện tích phải là một số nguyên dương");

        }

        try {
            double num = Double.parseDouble(facilityDto.getCost());
            if (num <= 0) {
                errors.rejectValue("cost", "", "Giá phải lớn hơn 0");
            }
        } catch (NumberFormatException e) {
            errors.rejectValue("cost", "", "Giá phải là một số dương");

        }

        try {
            int num = Integer.parseInt(facilityDto.getMaxPeople());
            if (num <= 0) {
                errors.rejectValue("maxPeople", "", "Số lượng khách phải lớn hơn 0");
            }
        } catch (NumberFormatException e) {
            errors.rejectValue("maxPeople", "", "Số lượng khách phải là một số nguyên dương");

        }


        if (facilityDto.getFacilityType() != null) {
            switch (facilityDto.getFacilityType().getId()) {
                //villa
                case 1:
                    if (facilityDto.standardRoom.matches("^\\s*$")) {
                        errors.rejectValue("standardRoom", "", "Không được để trống");
                    }

                    if (facilityDto.descriptionOtherConvenience.matches("^\\s*$")) {
                        errors.rejectValue("descriptionOtherConvenience", "", "Không được để trống");
                    }

                    try {
                        double num = Double.parseDouble(facilityDto.getPoolArea());
                        if (num <= 0) {
                            errors.rejectValue("poolArea", "", "Diện tích hồ bơi phải lớn hơn 0");
                        }
                    } catch (NumberFormatException e) {
                        errors.rejectValue("poolArea", "", "Diện tích hồ bơi phải là một số dương");

                    }

                    try {
                        int num = Integer.parseInt(facilityDto.getNumberOfFloors());
                        if (num <= 0) {
                            errors.rejectValue("numberOfFloors", "", "Số tầng phải lớn hơn 0");
                        }
                    } catch (NumberFormatException e) {
                        errors.rejectValue("numberOfFloors", "", "Số tầng phải là một số nguyên dương");

                    }
                    break;
                //house
                case 2:
                    if (facilityDto.standardRoom.matches("^\\s*$")) {
                        errors.rejectValue("standardRoom", "", "Không được để trống");
                    }

                    if (facilityDto.descriptionOtherConvenience.matches("^\\s*$")) {
                        errors.rejectValue("descriptionOtherConvenience", "", "Không được để trống");
                    }

                    try {
                        int num = Integer.parseInt(facilityDto.getNumberOfFloors());
                        if (num <= 0) {
                            errors.rejectValue("numberOfFloors", "", "Số tầng phải lớn hơn 0");
                        }
                    } catch (NumberFormatException e) {
                        errors.rejectValue("numberOfFloors", "", "Số tầng phải là một số nguyên dương");

                    }
                    break;

                //roo
                case 3:
                    if (facilityDto.facilityFree.matches("^\\s*$")) {
                        errors.rejectValue("facilityFree", "", "Không được để trống");
                    }
                    break;
            }
        }
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getMaxPeople() {
        return maxPeople;
    }

    public void setMaxPeople(String maxPeople) {
        this.maxPeople = maxPeople;
    }

    public String getStandardRoom() {
        return standardRoom;
    }

    public void setStandardRoom(String standardRoom) {
        this.standardRoom = standardRoom;
    }

    public String getDescriptionOtherConvenience() {
        return descriptionOtherConvenience;
    }

    public void setDescriptionOtherConvenience(String descriptionOtherConvenience) {
        this.descriptionOtherConvenience = descriptionOtherConvenience;
    }

    public String getPoolArea() {
        return poolArea;
    }

    public void setPoolArea(String poolArea) {
        this.poolArea = poolArea;
    }

    public String getNumberOfFloors() {
        return numberOfFloors;
    }

    public void setNumberOfFloors(String numberOfFloors) {
        this.numberOfFloors = numberOfFloors;
    }

    public String getFacilityFree() {
        return facilityFree;
    }

    public void setFacilityFree(String facilityFree) {
        this.facilityFree = facilityFree;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public RentType getRentType() {
        return rentType;
    }

    public void setRentType(RentType rentType) {
        this.rentType = rentType;
    }

    public FacilityType getFacilityType() {
        return facilityType;
    }

    public void setFacilityType(FacilityType facilityType) {
        this.facilityType = facilityType;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }
}
