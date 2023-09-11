package Body;

import org.json.JSONObject;

public class RequestBodyCreator {
//    public JSONObject submitOrderRequestBody(String customerId, String basketId) throws JSONException {
//
//
//        SubmitOrderRequestBody requestBody = new SubmitOrderRequestBody();
//        requestBody.setCustomerId(customerId);
//        requestBody.setBasketId(basketId);
// //       requestBody.setContractPassword(RandomUtils.getRandomString(10, true));
//
//        JSONObject jsonRequestBody = new JSONObject(requestBody);
//        return jsonRequestBody;
//    }
//
//    public static void main(String[] args) {
//        System.out.println();
//
//    }
public JSONObject createCustomerRequestBody() {
    CustomerRequestBody requestBody = new CustomerRequestBody();
    requestBody.generateRandomData();

    JSONObject jsonRequestBody = new JSONObject(requestBody);
    return jsonRequestBody;
}

    public static void main(String[] args) {
        RequestBodyCreator creator = new RequestBodyCreator();
        JSONObject requestBody = creator.createCustomerRequestBody();

        System.out.println(requestBody.toString());
    }
}


