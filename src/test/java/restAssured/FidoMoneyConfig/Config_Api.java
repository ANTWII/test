package restAssured.FidoMoneyConfig;

public class Config_Api {

    private static String authToken = null;
    private static String pincodeToken = null;
    private static String userID = null;

    public static String getPincodeToken() {
        return pincodeToken;
    }

    public static void setPincodeToken(String pincodeToken) {
        Config_Api.pincodeToken = pincodeToken;
    }

    public static String getUserID() {
        return userID;
    }

    public static void setUserID(String userID) {
        Config_Api.userID = userID;
    }

    public static String getAuthToken() {
        return authToken;
    }

    public static void setAuthToken(String token) {
        authToken = token;
    }

}
