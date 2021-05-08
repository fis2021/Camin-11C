package org.loose.fis.sre.services;

import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;
import org.loose.fis.sre.model.Announcement;
import org.loose.fis.sre.model.PaymentDetails;
import org.loose.fis.sre.model.Room;

import java.util.ArrayList;

public class PaymentDetailsService {
    private static ObjectRepository<PaymentDetails> paymentDetailsRepository;

    public static void initDatabase() {
        Nitrite database = Nitrite.builder()
                .filePath(FileSystemService.getPathToFile("paymentDetails.db").toFile())
                .openOrCreate("test", "test");

        paymentDetailsRepository = database.getRepository(PaymentDetails.class);
    }

    public static void addPaymentDetails(String studentName, String fee, String month, String status){
        paymentDetailsRepository.insert(new PaymentDetails(studentName,fee,month,status));
    }

    public static ArrayList<PaymentDetails> getPaymentDetails(){
        ArrayList<PaymentDetails> paymentDetails = new ArrayList<>();
        for (PaymentDetails paymentDetail: PaymentDetailsService.getPaymentDetailsRepository().find()) {
            paymentDetails.add(paymentDetail);
        }
        return paymentDetails;
    }

    public static ObjectRepository<PaymentDetails> getPaymentDetailsRepository() {
        return paymentDetailsRepository;
    }
}
