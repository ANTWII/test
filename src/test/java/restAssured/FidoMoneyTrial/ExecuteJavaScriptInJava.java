package restAssured.FidoMoneyTrial;


import org.graalvm.polyglot.Context;
        import org.graalvm.polyglot.Value;

public class ExecuteJavaScriptInJava {
    public static void main(String[] args) {
        String token = "eyJhbGciOiAiUlMyNTYiLCAidHlwIjogIkpXVCIsICJraWQiOiAiNGI4MWFiMWIwNzIzZThjNmE0YWJmYjZlYjc0OTE2MDM0NDczNzViNiJ9.eyJpc3MiOiAiZmlyZWJhc2UtYWRtaW5zZGstcXpmdGlAZmlkb2FwcC1zdGcuaWFtLmdzZXJ2aWNlYWNjb3VudC5jb20iLCAic3ViIjogImZpcmViYXNlLWFkbWluc2RrLXF6ZnRpQGZpZG9hcHAtc3RnLmlhbS5nc2VydmljZWFjY291bnQuY29tIiwgImF1ZCI6ICJodHRwczovL2lkZW50aXR5dG9vbGtpdC5nb29nbGVhcGlzLmNvbS9nb29nbGUuaWRlbnRpdHkuaWRlbnRpdHl0b29sa2l0LnYxLklkZW50aXR5VG9vbGtpdCIsICJ1aWQiOiAidzh1TFFCMGZ3Q1BSYjRPVjFzSmNZOU1SWndBMyIsICJpYXQiOiAxNjk5MjYxNzc3LCAiZXhwIjogMTY5OTI2NTM3N30.IML88E6br_KrgVd7hVwlIuFYPKV8XDLAubwHskqiVDnq_bufStivLFBMfiaRVeaDAbZgBpN-MNa_NqGGvwrF53pINBW1EaoOzBYRpj0nD4cqcIVw8IKBpWONyFfWwer8cO-dj_BX_aob4aUhEGtiVsG4sBlD1Fz7Et1JpP6UcYGQ6iTuaCOHLGzxxWn-jqFWq2-rnyEAjJVOMZ5D20sno1a_GqlzigbO1_tZcmFbihL3d_E6-uZjh022gODsFx0xMFYM7himgoCtUTLpWjZyRNpQL79V6GXr16Tx5bNvPkyMkbXRw147cxndAz1QINO09z2zjr5XtJwBh0ZF-K48Bg";

        try (Context context = Context.create()) {
            Value jsBindings = context.getBindings("js");
            jsBindings.putMember("token", token);

            String jsCode = "const { getAuth, signInWithCustomToken } = require('firebase/auth'); " +
                    "const { initializeApp } = require('firebase/app'); " +
                    "const firebaseConfig = " +
                    "{ apiKey: 'YOUR_API_KEY', authDomain: 'YOUR_AUTH_DOMAIN', projectId: 'YOUR_PROJECT_ID', " +
                    "  storageBucket: 'YOUR_STORAGE_BUCKET', messagingSenderId: 'YOUR_MESSAGING_SENDER_ID', " +
                    "  appId: 'YOUR_APP_ID' " +
                    "};" +
                    "const app = initializeApp(firebaseConfig); " +
                    "const auth = getAuth(); " +
                    "auth.languageCode = 'en'; " +
                    "const token = js.token;" +
                    "signInWithCustomToken(auth, token).then((userCredential) => { " +
                    "  console.log({ accessToken: userCredential.user.accessToken }); " +
                    "});";

            context.eval("js", jsCode);
        }
    }
}
