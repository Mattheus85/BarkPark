const header = document.querySelector("#h2");
const title = document.querySelector("#title");
const reviews = document.querySelector("#reviewModel");
const urlParams = new URLSearchParams(window.location.search);
const user = urlParams.get("user-id");
// const user = "User";

window.onload = async function (evt) {
  evt.preventDefault();
  createTitleAndHeader();
  axios
    .get(
      "https://hdvd9zw9q5.execute-api.us-west-1.amazonaws.com/Alpha/reviews?userId=" +
        user
    )
    .then((reviewsResult) => {
      console.log(reviewsResult);
      if (
        reviewsResult.data.errorType ===
        "com.barkpark.exceptions.ReviewsNotFoundException"
      ) {
        throw "No Reviews for user with id: " + user;
      }
      populateReviews(reviewsResult.data.reviewList);
    });
};

function populateReviews(reviewData) {
  for (let review of reviewData) {
    let titleDiv = document.createElement("div");
    titleDiv.className = "title-class";
    let title = document.createTextNode(review.reviewTitle);
    titleDiv.appendChild(title);
    reviews.appendChild(titleDiv);

    let bodyDiv = document.createElement("div");
    bodyDiv.className = "body-class";
    let body = document.createTextNode(review.reviewBody);
    bodyDiv.appendChild(body);
    reviews.appendChild(bodyDiv);

    let ratingDiv = document.createElement("div");
    ratingDiv.className = "rating-class";
    let rating = document.createTextNode(review.rating);
    ratingDiv.appendChild(rating);
    reviews.appendChild(ratingDiv);

    let userDiv = document.createElement("div");
    userDiv.className = "user-class";
    let user = document.createTextNode(review.userId);
    userDiv.appendChild(user);
    reviews.appendChild(userDiv);

    let dateDiv = document.createElement("div");
    dateDiv.className = "date-class";
    let date = document.createTextNode(review.date);
    dateDiv.appendChild(date);
    reviews.appendChild(dateDiv);
  }
}

function createTitleAndHeader() {
  let h2 = document.createTextNode(user);
  header.appendChild(h2);

  let ti = document.createTextNode(user);
  title.appendChild(ti);
}

var BarkPark = window.BarkPark || {};
BarkPark.map = BarkPark.map || {};

(function rideScopeWrapper($) {
  var authToken;
    BarkPark.authToken.then(function setAuthToken(token) {
        if (token) {
            authToken = token;
        } else {
            window.location.href = '/signin.html';
        }
    }).catch(function handleTokenError(error) {
        alert(error);
        window.location.href = '/signin.html';
    });

    // Register click handler for #request button
    $(function onDocReady() {
      $('#request').click(handleRequestClick);
      $(BarkPark.map).on('pickupChange', handlePickupChanged);

      BarkPark.authToken.then(function updateAuthMessage(token) {
          if (token) {
              displayUpdate('You are authenticated. Click to see your <a href="#authTokenModal" data-toggle="modal">auth token</a>.');
              $('.authToken').text(token);
          }
      });

      if (!_config.api.invokeUrl) {
          $('#noApiMessage').show();
      }
  });

function handlePickupChanged() {
  var requestButton = $('#request');
  requestButton.text('Request Unicorn');
  requestButton.prop('disabled', false);
}
function handleRequestClick(event) {
  var pickupLocation = WildRydes.map.selectedPoint;
  event.preventDefault();
  requestUnicorn(pickupLocation);
}

function displayUpdate(text) {
  $('#updates').append($('<li>' + text + '</li>'));
}
}(jQuery));