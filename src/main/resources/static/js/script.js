// Create a Checkout Session with the selected plan ID
var createCheckoutSession = function(priceId) {
  return fetch("/create-checkout-session", {
    method: "POST",
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify({
      priceId: priceId
    })
  }).then(function(result) {
    return result.json();
  });
};

// Handle any errors returned from Checkout
const handleResult = function(result) {
  if (result.error) {
    var displayError = document.getElementById("error-message");
    displayError.textContent = result.error.message;
  }
};


fetch("/payment-methods")
  .then((response) => {
    return response.json();
  })
  .then((responese) => {
    document.getElementById("last4").innerText=`**** ${responese.cards[0].last4}`;
    document.getElementById("brand").innerText=responese.cards[0].brand;
    document.getElementById("payment-method-id").value =responese.cards[0].paymentMethodId;
  });


/* Get your Stripe publishable key to initialize Stripe.js */
fetch("/setup")
  .then(function(result) {
    return result.json();
  })
  .then(function(json) {
    var publishableKey = json.publishableKey;
    const trainingServicePriceId = json.trainingServicePriceId;
    var basicPriceId = json.basicPrice;
    var proPriceId = json.proPrice;

    const stripe = Stripe(publishableKey);

    // TODO これはstripeのcheckout ページにredirect
    // document
    //   .getElementById("training-service-btn")
    //   .addEventListener("click", function(evt) {
    //     createCheckoutSession(trainingServicePriceId)
    //       .then(function(data) {
    //         // Call Stripe.js method to redirect to the new Checkout page
    //         stripe.redirectToCheckout({
    //           sessionId: data.sessionId
    //         })
    //       .then(handleResult);
    //     });
    //   });



    document
      .getElementById("training-service-btn")
      .addEventListener("click", function(evt) {
        fetch("/subscribe", {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify({
            paymentMethodId: document.getElementById("payment-method-id").value,
            priceId: trainingServicePriceId,
          }),
        })
          .then(() => {
            console.log("success");
            window.location.href = '/subscribe/result?status=succeeded';
          })
          .catch(() => {
            console.log("error");
            window.location.href = '/subscribe/result?status=failed';
          })
        // createSubscription({
        //   customerId: customerId,
        //   paymentMethodId: result.paymentMethod.id,
        //   priceId: trainingServicePriceId,
        // })
      });
  });


