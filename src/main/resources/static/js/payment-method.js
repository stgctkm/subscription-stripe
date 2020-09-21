const stripeElements = function(publicKey, setupIntent) {
  const stripe = Stripe(publicKey);
  const elements = stripe.elements();

  // Element styles
  const style = {
    base: {
      fontSize: "16px",
      color: "#32325d",
      fontFamily:
        "-apple-system, BlinkMacSystemFont, Segoe UI, Roboto, sans-serif",
      fontSmoothing: "antialiased",
      "::placeholder": {
        color: "rgba(0,0,0,0.4)"
      }
    }
  };

  const card = elements.create("card",
    {
      style: style,
      hidePostalCode: true,
    }
  );

  card.mount("#card-element");

  // Element focus ring
  card.on("focus", function() {
    const el = document.getElementById("card-element");
    el.classList.add("focused");
  });

  card.on("blur", function() {
    const el = document.getElementById("card-element");
    el.classList.remove("focused");
  });

  // Handle payment submission when user clicks the pay button.
  const button = document.getElementById("submit");
  button.addEventListener("click", function(event) {
    event.preventDefault();
    changeLoadingState(true);
    const email = document.getElementById("email").value;

    stripe
      .confirmCardSetup(setupIntent.clientSecret, {
        payment_method: {
          card: card,
          // billing_details: { email: email }
        }
      })
      .then(function(result) {
        if (result.error) {
          changeLoadingState(false);
          const displayError = document.getElementById("card-errors");
          displayError.textContent = result.error.message;
        } else {
          // The PaymentMethod was successfully set up
          orderComplete(stripe, setupIntent.clientSecret);
        }
      });
  });
};

const getSetupIntent = function(publicKey) {
  return fetch("/create-setup-intent", {
    method: "post",
    headers: {
      "Content-Type": "application/json"
    }
  })
    .then(function(response) {
      return response.json();
    })
    .then(function(setupIntent) {
      console.log(setupIntent);
      stripeElements(publicKey, setupIntent);
    });
};

const getPublicKey = function() {
  return fetch("/public-key", {
    method: "get",
    headers: {
      "Content-Type": "application/json"
    }
  })
    .then(function(response) {
      return response.json();
    })
    .then(function(response) {
      getSetupIntent(response.publicKey);
      // getSetupIntent(response);
    });
};

// Show a spinner on payment submission
const changeLoadingState = function(isLoading) {
  if (isLoading) {
    document.querySelector("button").disabled = true;
    document.querySelector("#spinner").classList.remove("hidden");
    document.querySelector("#button-text").classList.add("hidden");
  } else {
    document.querySelector("button").disabled = false;
    document.querySelector("#spinner").classList.add("hidden");
    document.querySelector("#button-text").classList.remove("hidden");
  }
};

/* Shows a success / error message when the payment is complete */
const orderComplete = function(stripe, clientSecret) {
  stripe.retrieveSetupIntent(clientSecret).then(function(result) {
    var setupIntent = result.setupIntent;
    var setupIntentJson = JSON.stringify(setupIntent, null, 2);

    document.querySelector(".sr-payment-form").classList.add("hidden");
    document.querySelector(".sr-result").classList.remove("hidden");
    document.querySelector("pre").textContent = setupIntentJson;
    setTimeout(function() {
      document.querySelector(".sr-result").classList.add("expand");
    }, 200);

    changeLoadingState(false);
  });
};

getPublicKey();
