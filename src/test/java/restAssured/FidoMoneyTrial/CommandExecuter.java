package restAssured.FidoMoneyTrial;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CommandExecuter {
    public static void main(String[] args) {
        try {
            // Command to be executed
            String command = " node main.js --profile stg --token eyJhbGciOiAiUlMyNTYiLCAidHlwIjogIkpXVCIsICJraWQiOiAiNGI4MWFiMWIwNzIzZThjNmE0YWJmYjZlYjc0OTE2MDM0NDczNzViNiJ9.eyJpc3MiOiAiZmlyZWJhc2UtYWRtaW5zZGstcXpmdGlAZmlkb2FwcC1zdGcuaWFtLmdzZXJ2aWNlYWNjb3VudC5jb20iLCAic3ViIjogImZpcmViYXNlLWFkbWluc2RrLXF6ZnRpQGZpZG9hcHAtc3RnLmlhbS5nc2VydmljZWFjY291bnQuY29tIiwgImF1ZCI6ICJodHRwczovL2lkZW50aXR5dG9vbGtpdC5nb29nbGVhcGlzLmNvbS9nb29nbGUuaWRlbnRpdHkuaWRlbnRpdHl0b29sa2l0LnYxLklkZW50aXR5VG9vbGtpdCIsICJ1aWQiOiAidzh1TFFCMGZ3Q1BSYjRPVjFzSmNZOU1SWndBMyIsICJpYXQiOiAxNjk5Mjc2OTY5LCAiZXhwIjogMTY5OTI4MDU2OX0.ucysn2vmQ3DrtZA1BOiLNF-YFTzRBE_M_nE7ZaZZ42b7wDOsMeVENnMEdrGO1U-hZZCPM2Q4_GHFrVXb_7vDPbbX86JZXmFgOG1bjl6owFTxd43ALRSrHHHc8-zreBGIa8ENZDeS37mYEkTnc_qxhm5msmXFbtXK-CoUsb0bYdJXjK5YTfh_ohtH4r56cACa705ov5A34QX9pCyLP9UCY9JhGrLXB9j4moYxn0ynYIhow0FRNNZ8M50Uryvq7L3gQmD7UiPh2ujGrAu24FkOWDYrRVNHfvg6AD3mpxGoyiDHM5ajX1T2IdT9O0ekvqpysXzvf_22xg3KGYvwMXfhXQ";

            // Execute command
            Process process = Runtime.getRuntime().exec(command);

            // Optional: Read output from the command
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuilder output = new StringBuilder(); // Create a StringBuilder to store the output

            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n"); // Append each line to the StringBuilder
            }

            String commandOutput = output.toString(); // Store the output in a variable

            // Display the output if needed
            System.out.println(commandOutput);
            // Wait for the command to complete
            process.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
