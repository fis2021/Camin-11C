package org.loose.fis.sre.model;

import org.dizitart.no2.objects.Id;

public class PaymentDetails {
    @Id
    private String studentName;
    private String fee;
    private String month;
    private String status;

    public PaymentDetails(String studentName, String fee, String month, String status) {

        this.studentName=studentName;
        this.fee=fee;
        this.month=month;
        this.status=status;
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

