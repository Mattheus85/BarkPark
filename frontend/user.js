const reviews = document.querySelector("#reviewModel");

window.onload = async function (evt) {
  evt.preventDefault();
  console.log("Getting Data...");
  axios
    .get(
      "https://hdvd9zw9q5.execute-api.us-west-1.amazonaws.com/Alpha/reviews?parkId=" +
        userId
    )
    .then((reviewsResult) => {
      console.log(reviewsResult);
      if (!reviewsResult.data.reviewList) {
        throw "No Reviews for user with id: " + userId;
      }
      populateReview(reviewsResult.data.reviewList);
    });
  createRatings();
};

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