/*global WildRydes _config AmazonCognitoIdentity AWSCognito*/

var BarkPark = window.BarkPark || {};

(function scopeWrapper($) {
  var signinUrl = "signin.html";

  var poolData = {
    UserPoolId: _config.cognito.userPoolId,
    ClientId: _config.cognito.userPoolClientId,
  };

  var userPool;

  if (
    !(
      _config.cognito.userPoolId &&
      _config.cognito.userPoolClientId &&
      _config.cognito.region
    )
  ) {
    $("#noCognitoMessage").show();
    return;
  }

  userPool = new AmazonCognitoIdentity.CognitoUserPool(poolData);

  if (typeof AWSCognito !== "undefined") {
    AWSCognito.config.region = _config.cognito.region;
  }

  BarkPark.signOut = function signOut() {
    userPool.getCurrentUser().signOut();
  };

  BarkPark.authToken = new Promise(function fetchCurrentAuthToken(
    resolve,
    reject
  ) {
    var cognitoUser = userPool.getCurrentUser();

    if (cognitoUser) {
      cognitoUser.getSession(function sessionCallback(err, session) {
        if (err) {
          reject(err);
        } else if (!session.isValid()) {
          resolve(null);
        } else {
          resolve(session.getIdToken().getJwtToken());
        }
      });
    } else {
      resolve(null);
    }
  });

  /*
   * Cognito User Pool functions
   */

  function register(username, email, password, onSuccess, onFailure) {
    var dataEmail = {
      Name: "email",
      Value: email,
    };

    var attributeEmail = new AmazonCognitoIdentity.CognitoUserAttribute(
      dataEmail
    );
    // username was toUsername(email)
    userPool.signUp(
      username,
      password,
      [attributeEmail],
      null,
      function signUpCallback(err, result) {
        if (!err) {
          console.log(result);
          onSuccess(result);
        } else {
          onFailure(err);
        }
      }
    );
  }

  function signin(username, password, onSuccess, onFailure) {
    var authenticationDetails = new AmazonCognitoIdentity.AuthenticationDetails(
      {
        Username: username,
        Password: password,
      }
    );

    var cognitoUser = createCognitoUser(username);
    cognitoUser.authenticateUser(authenticationDetails, {
      onSuccess: onSuccess,
      onFailure: onFailure,
    });
  }

  function verify(username, code, onSuccess, onFailure) {
    createCognitoUser(username).confirmRegistration(
      code,
      true,
      function confirmCallback(err, result) {
        if (!err) {
          onSuccess(result);
        } else {
          onFailure(err);
        }
      }
    );
  }

  function createCognitoUser(username) {
    return new AmazonCognitoIdentity.CognitoUser({
      Username: username,
      Pool: userPool,
    });
  }

  function toUsername(email) {
    return email.replace("@", "-at-");
  }

  /*
   *  Event Handlers
   */

  $(function onDocReady() {
    $("#signinForm").submit(handleSignin);
    $("#registrationForm").submit(handleRegister);
    $("#verifyForm").submit(handleVerify);
  });

  function handleSignin(event) {
    var username = $("#usernameInputRegister").val();
    var password = $("#passwordInputSignin").val();
    event.preventDefault();
    signin(
      username,
      password,
      function signinSuccess() {
        console.log("Successfully Logged In");
        var cognitoUsernameToSend = userPool.getCurrentUser().getUsername();
        module.exports = { cognitoUsernameToSend };
        window.location.href = "index.html";
      },
      function signinError(err) {
        alert(err);
      }
    );
  }

  function handleRegister(event) {
    var username = $("#usernameInputRegister").val();
    var email = $("#emailInputRegister").val();
    var password = $("#passwordInputRegister").val();
    var password2 = $("#password2InputRegister").val();

    var onSuccess = function registerSuccess(result) {
      var cognitoUser = result.user;
      console.log("user name is " + cognitoUser.getUsername());
      var confirmation =
        "Registration successful. Please check your email inbox or spam folder for your verification code.";
      if (confirmation) {
        window.location.href = "verify.html";
      }
    };
    var onFailure = function registerFailure(err) {
      alert(err);
    };
    event.preventDefault();

    if (password === password2) {
      register(username, email, password, onSuccess, onFailure);
    } else {
      alert("Passwords do not match");
    }
  }

  function handleVerify(event) {
    var username = $("#usernameInputRegister").val();
    var code = $("#codeInputVerify").val();
    event.preventDefault();
    verify(
      username,
      code,
      function verifySuccess(result) {
        console.log("call result: " + result);
        console.log("Successfully verified");
        alert(
          "Verification successful. You will now be redirected to the login page."
        );
        window.location.href = signinUrl;
      },
      function verifyError(err) {
        alert(err);
      }
    );
  }
})(jQuery);
