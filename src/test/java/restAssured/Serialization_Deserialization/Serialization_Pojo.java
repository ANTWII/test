package restAssured.Serialization_Deserialization;

public class Serialization_Pojo {
    private static String authToken = null;
    private static String pincodeToken = null;
    private static String userID = null;

    public static String getAuthToken() {
        return authToken;
    }

    public static void setAuthToken(String authToken) {
        Serialization_Pojo.authToken = authToken;
    }

    public static String getPincodeToken() {
        return pincodeToken;
    }

    public static void setPincodeToken(String pincodeToken) {
        Serialization_Pojo.pincodeToken = pincodeToken;
    }

    public static String getUserID() {
        return userID;
    }

    public static void setUserID(String userID) {
        Serialization_Pojo.userID = userID;
    }


}
