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
