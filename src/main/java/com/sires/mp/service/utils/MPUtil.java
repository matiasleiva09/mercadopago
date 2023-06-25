package com.sires.mp.service.utils;

import com.mercadopago.resources.payment.PaymentFeeDetail;

import java.math.BigDecimal;
import java.util.List;

public class MPUtil {
    public static BigDecimal calculateTotalFee(List<PaymentFeeDetail> feeDetails) {
        return feeDetails.stream().map(paymentFeeDetail -> paymentFeeDetail.getAmount()).reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
