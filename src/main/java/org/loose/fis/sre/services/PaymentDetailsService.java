package org.loose.fis.sre.services;

import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;
import org.loose.fis.sre.exceptions.IncorrectDetailsException;
import org.loose.fis.sre.model.PaymentDetails;

import java.util.ArrayList;

public class PaymentDetailsService {
    private static ObjectRepository<PaymentDetails> paymentDetailsRepository;
    private static Nitrite database;
    public static void initDatabase() {
        database = Nitrite.builder()
                .filePath(FileSystemService.getPathToFile("paymentDetails.db").toFile())
                .openOrCreate("test", "test");

        paymentDetailsRepository = database.getRepository(PaymentDetails.class);
    }

    public static void addPaymentDetails(String studentName, String fee, String month, String status) throws IncorrectDetailsException {
        areDetailsCorrect(studentName,month);
        paymentDetailsRepository.insert(new PaymentDetails(studentName,fee,month,status));

    }

    public static ArrayList<PaymentDetails> getPaymentDetails(){
        ArrayList<PaymentDetails> paymentDetails = new ArrayList<>();
        for (PaymentDetails paymentDetail: paymentDetailsRepository.find()) {
            paymentDetails.add(paymentDetail);
        }
        return paymentDetails;
    }

    public static ObjectRepository<PaymentDetails> getPaymentDetailsRepository() {
        return paymentDetailsRepository;
    }

    public static void areDetailsCorrect (String studentName, String month) throws IncorrectDetailsException
    {
        for(PaymentDetails payment: paymentDetailsRepository.find()){
            if(payment.getStudentName().equals(studentName) &&
                    payment.getMonth().equals(month)){
               throw new IncorrectDetailsException ("These details were already introduced ");
            }
        }
    }

    public static void closeDatabase()
    {
        database.close();
    }
}
