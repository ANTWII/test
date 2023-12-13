package restAssured.Config;

import org.testng.annotations.DataProvider;

import java.util.Map;

public class CustomDataProvider {

    //Data provider for Loan Services
    @DataProvider(name="TestData",parallel = false)
    public Object[][] TestData() {

        return new Object[][]{
               {Map.of("phoneNumber","+233540108519","generatepincode","7755","PaymentAmount","1","Telco","06")} ,
                {Map.of("phoneNumber","+233236648852","generatepincode","7755","PaymentAmount","1","Telco","06")} ,
                {Map.of("phoneNumber","+233245880771","generatepincode","7755","PaymentAmount","1","Telco","06")} ,
                {Map.of("phoneNumber","+233246331221","generatepincode","7755","PaymentAmount","1","Telco","06")} ,
        };

    }

    //Data provider for Airtime Services
    @DataProvider(name="Airtime",parallel =false)

    public Object[][] TestData2() {

        return new Object[][]{
                {Map.of("phoneNumber","+233540108519","generatepincode","7755","PaymentAmount","1","Telco","06")} ,
                {Map.of("phoneNumber","+233548772115","generatepincode","7755","PaymentAmount","1","Telco","02")} ,

        };

    }

}
