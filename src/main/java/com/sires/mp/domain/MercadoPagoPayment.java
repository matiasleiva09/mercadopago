package com.sires.mp.domain;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Table(name="MP_PAGO")
@Getter
@Setter
public class MercadoPagoPayment {
    @Id
    private Long id;
    @Column(name="REGISTRADO")
    private OffsetDateTime dateCreated;
    @Column(name="APROVADO")
    private OffsetDateTime dateApproved;
    @Column(name="ACTUALIZADO")
    private OffsetDateTime dateLastUpdated;
    @Column(name="VENCIMIENTO")
    private OffsetDateTime dateOfExpiration;
    @Column(name="ENTREGADO")
    private OffsetDateTime moneyReleaseDate;
    @Column(name="TIPO_OP")
    private String operationType;
    @Column(name="ID_EMISOR")
    private String issuerId;
    @Column(name="METODO_PAGO")
    private String paymentMethodId;
    @Column(name="TIPO_PAGO")
    private String paymentTypeId;
    @Column(name="ESTADO")
    private String status;
    @Column(name="DETALLE_ESTADO")
    private String statusDetail;
    @Column(name="MONEDA")
    private String currencyId;
    @Column(name="DESCRIPCION")
    private String description;
    @Column(name="COD_AUTH")
    private String authorizationCode;
    @Column(name="TIPO_C_HOLDER")
    private String cardHolderType;
    @Column(name="NOMBRE_C_HOLDER")
    private String cardHolderName;
    @Column(name="C_ID")
    private String cardId;
    @Column(name="C_ULTIMOS_DIGITOS")
    private String cardLastFourDigits;
    @Column(name="C_PRIMEROS_DIGITOS")
    private String cardFirstSixDigits;
    @Column(name="C_VTO_ANIO")
    private int cardExpirationYear;

    @Column(name="C_VTO_MES")
    private int cardExpirationMonth;
    @Column(name="C_CREADA")
    private OffsetDateTime cardDateCreated;

    @Column(name="C_ACTUALIZADA")
    private OffsetDateTime cardDateLastUpdated;

    @Column(name="C_HOLDER_NUM")
    private String cardHolderNumber;

    @Column(name="ID_TX_EXTERNA")
    private String externalReference;

    @Column(name="MONTO_TRANSAC")
    private BigDecimal transactionAmount;
    @Column(name="CAPTURADO")
    private boolean captured;

    @Column(name="MODO_BINARIO")
    private boolean binaryMode;
    @Column(name="PROD")
    private boolean liveMode;
    @Column(name="DESCR_EST_CTA")
    private String statementDescriptor;
    @Column(name="IMPUESTOS")
    private BigDecimal taxesAmount;
    @Column(name="ID_DIF_PRECIO")
    private String differentialPricingId;
    @Column(name="TARIFA_MP")
    private BigDecimal totalFeeAmount;
    @Column(name="CUOTAS")
    private int installments;
    @Column(name="ID_INTEGRADOR")
    private String integratorId;
    @Column(name="CTA_COMERCIO")
    private String merchantAccountId;
    @Column(name="NUM_COMERCIO")
    private String merchantNumber;
    @Column(name="NUM_ORDEN")
    private Long orderId;
    @Column(name="TIPO_ORDEN")
    private String orderType;
    @Column(name="TIPO_DOC")
    private String clientDocumentType;

    @Column(name="NUM_DOC")
    private String clientDocumentNumber;
    @Column(name="MONTO_NETO")
    private BigDecimal netReceivedAmount;
}