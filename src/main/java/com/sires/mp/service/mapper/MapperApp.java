package com.sires.mp.service.mapper;

import com.mercadopago.client.preference.PreferenceItemRequest;
import com.mercadopago.client.preference.PreferencePayerRequest;
import com.mercadopago.resources.payment.Payment;
import com.mercadopago.resources.preference.Preference;
import com.sires.mp.domain.MercadoPagoPayment;
import com.sires.mp.domain.TokenMP;
import com.sires.mp.service.dto.*;
import com.sires.mp.domain.Configuracion;
import com.sires.mp.service.utils.MPUtil;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.util.List;

@Mapper
public interface MapperApp {
    MapperApp INSTANCE = Mappers.getMapper(MapperApp.class);
    @Mapping(source = "clave",target = "key")
    @Mapping(source="valor",target="value")
    ConfigurationDTO fromEntityToDTO(Configuracion conf);
    @Mapping(source = "clave",target = "key")
    @Mapping(source="valor",target="value")
    List<ConfigurationDTO> fromEntityToDTO(Iterable<Configuracion> conf);
    List<PreferenceItemRequest> fromEntityToDTO(List<ItemCompraDTO> lista);
    @Mapping(source="nombre",target="name")
    @Mapping(source="apellido",target="surname")
    @Mapping(source="direccion",target="address.streetName")
    @Mapping(source="cp",target="address.zipCode")
    @Mapping(source="altura",target="address.streetNumber")
    @Mapping(source="telefono",target="phone.number")
    @Mapping(source="areaTelefono",target="phone.areaCode")
    PreferencePayerRequest fromEntityToDTO(UsuarioDTO usuario);
    @Mapping(source="initPoint",target="urlPreferencia")
    ResponsePreferenceDTO fromEntityToDTO(Preference pref);
    TokenMP fromEntityToDTO(LoginResponseDTO dto);
    @Mappings({
            @Mapping(source = "card.cardholder.identification.type",target = "cardHolderType"),
            @Mapping(source = "card.cardholder.name",target = "cardHolderName"),
            @Mapping(source = "card.id",target = "cardId"),
            @Mapping(source = "card.lastFourDigits",target = "cardLastFourDigits"),
            @Mapping(source = "card.firstSixDigits",target = "cardFirstSixDigits"),
            @Mapping(source = "card.expirationYear",target = "cardExpirationYear"),
            @Mapping(source = "card.expirationMonth",target = "cardExpirationMonth"),
            @Mapping(source = "card.dateLastUpdated",target = "cardDateLastUpdated"),
            @Mapping(source = "card.dateCreated",target = "cardDateCreated"),
            @Mapping(source = "card.cardholder.identification.number",target = "cardHolderNumber"),
            @Mapping(target = "totalFeeAmount",ignore = true),
            @Mapping(source = "order.id",target = "orderId"),
            @Mapping(source = "order.type",target = "orderType"),
            @Mapping(source = "payer.identification.type",target = "clientDocumentType"),
            @Mapping(source = "payer.identification.number",target = "clientDocumentNumber"),
            @Mapping(source = "transactionDetails.netReceivedAmount",target = "netReceivedAmount"),
    })
    MercadoPagoPayment fromEntityToDTO(Payment payment);
    @AfterMapping
    default void calcularTotal(Payment payment, @MappingTarget MercadoPagoPayment dto) {
        System.out.println("hola");
        dto.setTotalFeeAmount(MPUtil.calculateTotalFee(payment.getFeeDetails()));
    }



}
