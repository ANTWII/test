# Firebase token generation

use otp endpoints to generate a token and pass it to this script to translate into access token.

default profile is dev, can be used for stg as well.

# Installation
```sh
npm install
```

```sh
node main.js --profile [stg | dev] --token {token}
```