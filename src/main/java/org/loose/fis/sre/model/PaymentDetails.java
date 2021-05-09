package org.loose.fis.sre.model;


public class PaymentDetails {
    private static int id;
    private String studentName;
    private String fee;
    private String month;
    private String status;

    public PaymentDetails(String studentName, String fee, String month, String status) {
        id++;
        this.studentName = studentName;
        this.fee = fee;
        this.month = month;
        this.status = status;
    }

    public  PaymentDetails(){

    }

    public String getStudentName() {
        return studentName;
    }

    public String getFee() {
        return fee;
    }

    public String getMonth() {
        return month;
    }

    public String getStatus() {
        return status;
    }
}

