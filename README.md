[<p align="center"><img src="https://github.com/anquanssl/.github/raw/main/profile/logo_dark.png" width="600" height="85"/></p>](https://www.anquanssl.com?__utm_from=github-org-profile#gh-dark-mode-only)
[<p align="center"><img src="https://github.com/anquanssl/.github/raw/main/profile/logo_light.png" width="600" height="85"/></p>](https://www.anquanssl.com?__utm_from=github-org-profile#gh-light-mode-only)

## AnquanSSL

AnquanSSL, aka "Security SSL", also known as "安全 SSL" in Mandarin, founded in 2018, and our mission is providing affordable, secure, and enhanced TLS utilization experiences in the Greater China market.

这是 [安全SSL](https://www.anquanssl.com) 开放API的 Java SDK.

[![Build Status](https://travis-ci.com/anquanssl/java-sdk.svg?branch=master)](https://travis-ci.com/anquanssl/java-sdk)

[获取](https://www.anquanssl.com/dashboard/api-credentials) `AccessKey` 秘钥对.

此SDK包仅面向开发者提供支持，若您是分销商，您可能需要:
- [AnquanSSL Module for WHMCS]()
- [AnquanSSL Module for HostBill]()
- [AnquanSSL Module for 宝塔(bt.cn)]()

如果您要其它编程语言的开发者，您可能需要
- [AnquanSSL PHP SDK](https://github.com/anquanssl/php-sdk)
- [AnquanSSL Python SDK](https://github.com/anquanssl/python-sdk)
- [AnquanSSL NodeJS SDK](https://github.com/anquanssl/nodejs-sdk)
- [AnquanSSL Golang SDK](https://github.com/anquanssl/golang-sdk)
- [AnquanSSL Java SDK](https://github.com/anquanssl/java-sdk)


## 安装

```bash
...
```

## 使用

```java
package com.anquanssl.resource;

import com.anquanssl.Client;
import com.anquanssl.request.CertificateCreateRequest;
import com.anquanssl.request.CertificateDetailRequest;
import com.anquanssl.request.CertificateRefundRequest;
import com.anquanssl.request.CertificateValidateDCVRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.util.HashMap;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    Order order;

    @BeforeEach
    void setUp() {
        String accessKeyID = "修改成您的 accessKeyID";
        String accessKeySecret = "修改成您的 accessKeySecret";

        Client client = new Client(accessKeyID, accessKeySecret, "");
        order = new Order(client);
    }

    @Test
    void test() throws IOException, InvalidKeyException {
        // Place new order
        CertificateCreateRequest certificateCreateRequest = new CertificateCreateRequest();
        certificateCreateRequest.setProductId("sslcom_dv_flex");
        certificateCreateRequest.setPeriod("annually");
        certificateCreateRequest.setCsr("-----BEGIN CERTIFICATE REQUEST-----\n" +
            "MIIE2DCCAsACAQAwTzELMAkGA1UEBhMCQ04xFzAVBgNVBAMMDnd3dy5oZXhkYXRh\n" +
            "LmNuMScwJQYJKoZIhvcNAQkBFhh4aWFvaHVpLmxhbUBlLmhleGRhdGEuY24wggIi\n" +
            "MA0GCSqGSIb3DQEBAQUAA4ICDwAwggIKAoICAQC3Di3xa6XVtojvD66o+OCwldYX\n" +
            "1rIOZol3AZ2QbKjVl9avYbb7avB1Iyvis7LVXuzFMLjzSSGQsZu4YBKUVQZ8zrw9\n" +
            "1ROWsBXQlSUMuZqkNYt1VKPfZUoPgaDkM5RNMBOa7DeCCyxkxdu1cVVirJvZ/fHu\n" +
            "iamU1n47079d70JHTPoWd4Ug3D4pL2W/HRfJn8BRcvBzKUYo7bFZB+SluBjlm5Yh\n" +
            "IFmBWSjvFlM7z+QHCt7fFA/PmatJfPVAucdSQnbN3gji8BfovncL8rtM5hC7qFZ9\n" +
            "+AiTJya5L+spYzxNDIb2bGuNkssKtPYgFGkU7Y/TH+a4PxRUAbsbTRI4E1gOz7AQ\n" +
            "hRY3XedLjZVtDBRJDGIIobhZf8dNjVwAq7OOn/vbR4IijQ4mW4/+jm9TLVIUkpVb\n" +
            "33yO3Ci+wrI34KVkDBnczDub4aEg5yIm6lfedrtSUgzBs2jbV46XUrVjtnVRc2EM\n" +
            "pu7zToQ7ShpykAmJYZmQOzjP7au3OhtyIpFH7iNXe0ME76Hbgxsk6NpfBxKci460\n" +
            "dhRcggzZgt6deENtjv+AuZg2dDIlIbWFF4a3TvKM2f+R1tLaU+3O4DqFuxrU0Tij\n" +
            "4oiLKl6Vljrrs2kON++02uLXQ6hCxjS4cJ04aofirmsCdH7j+vexpqsnPOP5WuXU\n" +
            "Dolt6PgmpGdED2BneQIDAQABoEQwQgYJKoZIhvcNAQkOMTUwMzAJBgNVHRMEAjAA\n" +
            "MAsGA1UdDwQEAwIF4DAZBgNVHREEEjAQgg53d3cuaGV4ZGF0YS5jbjANBgkqhkiG\n" +
            "9w0BAQsFAAOCAgEAAO/2beQZXkCSGnmPEpY30SVaibD/RVGv9nPG00XY1nAg389l\n" +
            "S60ogWnduaFUKlQduc5cv0kYGUcNYEiqDMPO3ShSNSsjSSNAG5zP1jiMyV6s7a6+\n" +
            "na0qNCZyXkuEgeEJVhsc5q7m2nFgdGU6pPJA+5/7tANyR7xkJSQoAHxASTwaZ9FN\n" +
            "hC8qXRuJfklPjxSv/YB0ZhKsxs67cCP0A0mxZMh5yY7PeUCzPnJYZfsmXpVNF3wx\n" +
            "btEyzFHMXGJ7frK0kwkbkhss/89yE7VrM51eOtXSyLCBQyIuSaEqhuvxwuTnG5Oo\n" +
            "vNfVEZUAW1oEN0wM97272nS8G62oYAho1iUmXFCVksNRNH1Yhe3uKvOLKpC7I0Ef\n" +
            "Xp1HqeIxm5CBMfhNcPppwTV5FFomAT1cOowOlQsa2jf7VQbygNy6medj8aARr8F+\n" +
            "IXVMJfIn62tGjuXglAc9x+4UlH1R46fARoCBhvfOJcYJ/8FJwnYddP5kgPzZgcOF\n" +
            "XjfAxlfu67g2ziCic6gtyt+SRYCNLIxBz99lGRj3zXlNLouPxMy99sEoXO3lIgJq\n" +
            "s4cstKzaK0MA4ghOBKJhyCPNOdxJUcsGuVCWFoXMx7QcXcCzrI0A74FGRMKRa6q/\n" +
            "zDUNg1i9krVrWMOYyBUQxNHju0eXRsb0vaE0s3c0ayHBjnu8019Ebt6Q9mI=\n" +
            "-----END CERTIFICATE REQUEST-----");

        certificateCreateRequest.setUniqueId(UUID.randomUUID().toString());
        certificateCreateRequest.setContactEmail("email@example.org");
        certificateCreateRequest.setDomainDcv(new HashMap<>());
        certificateCreateRequest.getDomainDcv().put("*.example.org", "dns");
        certificateCreateRequest.getDomainDcv().put("example.org", "dns");
        certificateCreateRequest.setNotifyUrl("https://app.example.org/notify");
        Map<String, Object> resp = order.certificateCreate(certificateCreateRequest);
        System.out.println("certificateCreate:" + resp);

        // Get order detail
        CertificateDetailRequest certificateDetailRequest = new CertificateDetailRequest();
        certificateDetailRequest.setServiceId(resp.get("service_id").toString());
        System.out.println("certificateDetail:" + order.certificateDetail(certificateDetailRequest));

        // Perform a domain validation control check
        CertificateValidateDCVRequest certificateValidateDCVRequest = new CertificateValidateDCVRequest();
        certificateValidateDCVRequest.setServiceId(resp.get("service_id").toString());
        System.out.println("certificateValidateDCV:" + order.certificateValidateDCV(certificateValidateDCVRequest));

        // Cancel & refund order
        CertificateRefundRequest certificateRefundRequest = new CertificateRefundRequest();
        certificateRefundRequest.setServiceId(resp.get("service_id").toString());
        System.out.println("certificateRefund:" + order.certificateRefund(certificateRefundRequest));
    }
}
```

## License

MIT