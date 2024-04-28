package com.anquanssl.request;

import java.util.Map;
import lombok.Data;

@Data
public class CertificateCreateRequest {
    private String uniqueId;
    private String productId;
    private String period;
    private Map<String, String> domainDcv;
    private String csr;
    private Integer renew;
    private String organization;
    private String organizationUnit;
    private String registeredAddressLine1;
    private String serialNo;
    private String country;
    private String state;
    private String city;
    private String postalCode;
    private String organizationPhone;
    private String dateOfIncorporation;
    private String contactName;
    private String contactTitle;
    private String contactPhone;
    private String contactEmail;
    private String notifyUrl;
}