package Body;

import java.util.Random;

public class CustomerRequestBody {
    private String basketId;
    private String msisdn;
    private String customerFirstName;
    private String customerLastName;
    private String email;

    private String externalReference;
    private String password;

    public String getBasketId() {
        return basketId;
    }

    public void setBasketId(String basketId) {
        this.basketId = basketId;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getCustomerFirstName() {
        return customerFirstName;
    }

    public void setCustomerFirstName(String customerFirstName) {
        this.customerFirstName = customerFirstName;
    }

    public String getCustomerLastName() {
        return customerLastName;
    }

    public void setCustomerLastName(String customerLastName) {
        this.customerLastName = customerLastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getExternalReference() {
        return externalReference;
    }

    public void setExternalReference(String externalReference) {
        this.externalReference = externalReference;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public CustomerRequestBody() {
        this.basketId = basketId;
        this.msisdn = msisdn;
        this.customerFirstName = customerFirstName;
        this.customerLastName = customerLastName;
        this.email = email;
        this.externalReference = externalReference;
        this.password = password;
    }


    public void generateRandomData() {
        Random random = new Random();
        this.basketId = Integer.toString(random.nextInt(1000));
        this.msisdn = Integer.toString(random.nextInt(1000));
        this.customerFirstName = RandomNameGenerator.generateRandomName();
        this.customerLastName = "Doe";
        this.email = "johndoe@example.com";
        this.externalReference = "ext123";
        this.password = "securePassword";
    }
}
