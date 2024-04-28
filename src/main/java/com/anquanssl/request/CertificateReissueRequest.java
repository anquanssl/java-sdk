package com.anquanssl.request;

import lombok.Data;

import java.util.Map;

@Data
public class CertificateReissueRequest {
    private String serviceId; // 必传,下单时返回的id
    private Map<String, String> domainDcv; // 必传
    private String csr; // 必传,客户上传的CSR
    private int renew; // OV/EV必传,是否为续费订单
    private String organization; // OV/EV必传,公司名称
    private String organizationUnit; // OV/EV必传,公司部门
    private String registeredAddressLine1; // OV/EV必传,公司注册地址
    private String serialNo; // OV/EV必传,公司注册号，三证合一
    private String country; // OV/EV必传,2位国别码，大写
    private String state; // OV/EV必传,省份
    private String city; // OV/EV必传,城市
    private String postalCode; // OV/EV必传,邮编
    private String organizationPhone; // OV/EV必传,组织注册登记电话
    private String dateOfIncorporation; // OV/EV必传,成立日期
    private String contactName; // OV/EV必传,联系人
    private String contactTitle; // OV/EV必传,联系人职位
    private String contactPhone; // OV/EV必传,联系人电话
    private String contactEmail; // 必传,联系人邮箱
    private String notifyUrl; // 必传,证书颁发后的通知地址
}
