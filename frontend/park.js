const createReview = document.querySelector("#create-review-form");
const park = document.querySelector("#parkModel");
const reviews = document.querySelector("#reviewModel");
const urlParams = new URLSearchParams(window.location.search);
const id = urlParams.get("id");

window.onload = async function (evt) {
  evt.preventDefault();
  console.log("Getting Data...");
  axios
    .get(
      "https://hdvd9zw9q5.execute-api.us-west-1.amazonaws.com/Alpha/parks/" + id
    )
    .then((parkResult) => {
      console.log(parkResult);
      if (
        parkResult.data.errorType ===
        "com.barkpark.exceptions.ParkNotFoundException"
      ) {
        throw "No data for park with id: " + id;
      }
      populatePark(parkResult.data.parkModel);
    });
  axios
    .get(
      "https://hdvd9zw9q5.execute-api.us-west-1.amazonaws.com/Alpha/reviews?parkId=" +
        id
    )
    .then((reviewsResult) => {
      console.log(reviewsResult);
      if (
        reviewsResult.data.errorType ===
        "com.barkpark.exceptions.ReviewsNotFoundException"
      ) {
        throw "No Reviews for park with id: " + id;
      }
      populateReview(reviewsResult.data.reviewList);
    });
  createRatings();
};

createReview.onsubmit = async function (evt) {
  evt.preventDefault();
  const title = document.querySelector("#review-title-input").value;
  const body = document.querySelector("#review-body-input").value;
  const userId = document.querySelector("#user-id-input").value;
  const rating = getRating();
  const newReview = {
    reviewTitle: title,
    reviewBody: body,
    rating: rating,
  };
  axios
    .post(
      `https://hdvd9zw9q5.execute-api.us-west-1.amazonaws.com/Alpha/reviews/?parkId=${id}&userId=${userId}`,
      newReview
    )
    .then((reviewRes) => {
      console.log(reviewRes);
      //window.location.reload();
    });
};

function populatePark(parkData) {
  let nameDiv = document.createElement("div");
  nameDiv.className = "name-class";
  let parkName = document.createTextNode("Park name: " + parkData.name);
  nameDiv.appendChild(parkName);
  park.appendChild(nameDiv);

  let locationDiv = document.createElement("div");
  locationDiv.className = "location-class";
  let cityName = document.createTextNode("Location: " + parkData.location);
  locationDiv.appendChild(cityName);
  park.appendChild(locationDiv);
}

function populateReview(reviewData) {
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

function createRatings() {
  const options = ["1", "2", "3", "4", "5"];

  // generate the radio ratings
  const rating = document.querySelector("#rating-radio");
  rating.innerHTML = options
    .map(
      (option) => `<div>
                <input type="radio" name="option" required value="${option}" id="${option}">
                 <label for="${option}">${option}</label>
            </div>`
    )
    .join(" ");

  // add an event listener for the change event
  // const radioButtons = document.querySelectorAll('input[name="option"]');
  // for (const radioButton of radioButtons) {
  //   radioButton.addEventListener("change", showSelected);
  // }
  // function showSelected(e) {
  //   console.log(e);
  //   if (this.checked) {
  //     document.querySelector(
  //       "#rating-radio"
  //     ).append = `You selected ${this.value}`;
  //   }
  // }
}

function getRating() {
  var button = document.getElementsByName("option");

  for (i = 0; i < button.length; i++) {
    if (button[i].checked) return button[i].value;
  }
}
