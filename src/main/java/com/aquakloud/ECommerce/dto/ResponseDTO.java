package com.aquakloud.ECommerce.dto;

public class ResponseDTO {

    private String status;
    private String messsage;

    public ResponseDTO(String status, String messsage) {
        this.status = status;
        this.messsage = messsage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMesssage() {
        return messsage;
    }

    public void setMesssage(String messsage) {
        this.messsage = messsage;
    }
}
