package org.loose.fis.sre.services;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.*;
import org.loose.fis.sre.exceptions.IncorrectDetailsException;

import org.loose.fis.sre.model.PaymentDetails;
import org.testfx.api.FxToolkit;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PaymentDetailsServiceTest {

    @BeforeAll
    static void beforeAll() {
        System.out.println("Before All");
    }

    @AfterAll
    static void afterAll() throws TimeoutException {
        FxToolkit.cleanupStages();
    }

    @BeforeEach
    void setUP() throws IOException {
        FileSystemService.APPLICATION_FOLDER = ".test-payment";
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomeFolder().toFile());
        PaymentDetailsService.initDatabase();

    }

    @AfterEach
    void tearDown() {
        PaymentDetailsService.closeDatabase();
    }

    @Test
    @DisplayName("Database is initialized, and there are no Laundries")
    void testDataBaseIsInitializedAndNoLaundriesIsPersisted() {
        assertThat(PaymentDetailsService.getPaymentDetails()).isNotNull();
        assertThat(PaymentDetailsService.getPaymentDetails()).isEmpty();
    }

    @Test
    @DisplayName("PaymentDetails is successfully persisted to Database")
    void testPaymentDetailsIsAddedToDatabase() throws IncorrectDetailsException {
        PaymentDetailsService.addPaymentDetails("Sonia", "100","May",  "Payed" );
        assertThat(PaymentDetailsService.getPaymentDetails()).isNotEmpty();
        assertThat(PaymentDetailsService.getPaymentDetails()).size().isEqualTo(1);
        PaymentDetails PaymentDetails = PaymentDetailsService.getPaymentDetails().get(0);
        assertThat(PaymentDetails).isNotNull();
        assertThat(PaymentDetails.getStudentName()).isEqualTo("Sonia");
        assertThat(PaymentDetails.getFee()).isEqualTo("100");
        assertThat(PaymentDetails.getMonth()).isEqualTo("May");
        assertThat(PaymentDetails.getStatus()).isEqualTo("Payed");


    }

    @Test
    @DisplayName("PaymentDetails can not be added twice")
    void testPaymentDetailsCanNotBeAddedTwice() {
        assertThrows(IncorrectDetailsException.class, () -> {
            PaymentDetailsService.addPaymentDetails("Sonia", "100","May","Payed");
            PaymentDetailsService.addPaymentDetails("Sonia", "100","May","Payed");
        });
    }

}