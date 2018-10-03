package com.falisoft.common.dto;

import javax.validation.constraints.NotNull;

/**
 * {Insert class description here}
 *
 * @version $Revision: 1.0 $
 * @since Build {1.0} (11 2017)
 * @author Raphael KEITA
 *
 */
public class DeliveryboyCreateRequest extends AccountRequest {
    private Integer rating;
    private Integer status;
    @NotNull(message = "driver licenc enumber should not be  blank") 
    private String driverLicenceNumber;
    @NotNull(message = "engine plate number should not be  blank") 
    private String enginePlateNumber;
    @NotNull(message = "engine model should not be  blank") 
    private String enginModel;
    @NotNull(message = "engine type should not be  blank") 
    private String engineType;
    @NotNull (message = "licence issue date should not be blank") 
    private String licenceIssueDate;
    @NotNull(message = "licence expiration date should not be  blank")    
    private String licenceExpDate;

    public DeliveryboyCreateRequest() {
        super();
    }

    public DeliveryboyCreateRequest(Integer rating, Integer status, String driverLicenceNumber, String enginePlateNumber, String enginModel, String engineType, String licenceIssueDate, String licenceExpDate, String username, String firstname, String lastname, String password, String email, String reference, String phone, String type, String addres) {
        super(username, firstname, lastname, password, email, reference, phone, type, addres);
        this.rating = rating;
        this.status = status;
        this.driverLicenceNumber = driverLicenceNumber;
        this.enginePlateNumber = enginePlateNumber;
        this.enginModel = enginModel;
        this.engineType = engineType;
        this.licenceIssueDate = licenceIssueDate;
        this.licenceExpDate = licenceExpDate;
    }
    
    

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDriverLicenceNumber() {
        return driverLicenceNumber;
    }

    public void setDriverLicenceNumber(String driverLicenceNumber) {
        this.driverLicenceNumber = driverLicenceNumber;
    }

    public String getEnginePlateNumber() {
        return enginePlateNumber;
    }

    public void setEnginePlateNumber(String enginePlateNumber) {
        this.enginePlateNumber = enginePlateNumber;
    }

    public String getEnginModel() {
        return enginModel;
    }

    public void setEnginModel(String enginModel) {
        this.enginModel = enginModel;
    }

    public String getEngineType() {
        return engineType;
    }

    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }

    public String getLicenceIssueDate() {
        return licenceIssueDate;
    }

    public void setLicenceIssueDate(String licenceIssueDate) {
        this.licenceIssueDate = licenceIssueDate;
    }

    public String getLicenceExpDate() {
        return licenceExpDate;
    }

    public void setLicenceExpDate(String licenceExpDate) {
        this.licenceExpDate = licenceExpDate;
    }

   
}
