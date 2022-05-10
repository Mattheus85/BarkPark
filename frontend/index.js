const parksList = document.querySelector("#parks");
const cities = document.querySelector("#cities");
const avgRating = document.querySelector("#average-rating");
var parksDataToBeFiltered;

window.onload = async function (evt) {
  evt.preventDefault();
  console.log("Getting Parks Data...");
  createRatings();
  axios
    .get("https://hdvd9zw9q5.execute-api.us-west-1.amazonaws.com/Alpha/parks")
    .then((parksResult) => {
      console.log(parksResult);
      populateParks(parksResult.data.parkList);
      parksDataToBeFiltered = parksResult.data.parkList;
    });
  axios
    .get(
      "https://hdvd9zw9q5.execute-api.us-west-1.amazonaws.com/Alpha/locations"
    )
    .then((locationsResult) => {
      console.log(locationsResult);
      populateLocations(locationsResult.data.locationSet);
    });
};

function populateParks(parksData) {
  let div = document.createElement("div");
  div.className = "parks-class";
  for (let park of parksData) {
    let li = document.createElement("li");
    let a = document.createElement("a");
    let text = document.createTextNode(park.name);

    a.setAttribute("href", `./park.html?id=${park.id}`);

    a.appendChild(text);
    li.appendChild(a);
    div.appendChild(li);
  }
  parksList.appendChild(div);
}

function populateLocations(locationsData) {
  let div = document.createElement("div");
  div.className = "locations-class";
  for (let location of locationsData) {
    let li = document.createElement("li");
    let a = document.createElement("a");
    let text = document.createTextNode(location);

    a.setAttribute("href", `./locations.html?location=${location}`);

    a.appendChild(text);
    li.appendChild(a);
    div.appendChild(li);
  }
  cities.appendChild(div);
}

function createRatings() {
  const options = ["5", "4", "3", "2", "1"];

  // generate the radio ratings
  const rating = document.querySelector("#average-rating");
  rating.innerHTML = options
    .map(
      (option) => `<div>
                <input type="radio" name="option" title="See parks with this rating or above" value="${option}" id="${option}">
                 <label for="${option}">${option} Stars</label>
            </div>`
    )
    .join(" ");

  // add an event listener for the change event
  const radioButtons = document.querySelectorAll('input[name="option"]');
  const showParks = document.querySelector('input[name="get-all-parks"]');
  showParks.addEventListener("change", showAllParks);
  for (const radioButton of radioButtons) {
    radioButton.addEventListener("change", showSelected);
  }
  function showSelected(e) {
    console.log(e);
    if (this.checked) {
      filterParksByAverageRating(`${this.value}`);
    }
  }
  function showAllParks() {
    window.location.reload();
  }
}

function filterParksByAverageRating(rating) {
  let div = document.createElement("div");
  div.className = "parks-class";
  for (let park of parksDataToBeFiltered) {
    if (park.avgRating >= rating) {
      let li = document.createElement("li");
      let a = document.createElement("a");
      let text = document.createTextNode(park.name);

      a.setAttribute("href", `./park.html?id=${park.id}`);

      a.appendChild(text);
      li.appendChild(a);
      div.appendChild(li);
    }
  }
  let oldClass = document.getElementById(`parks`);
  parks.replaceChild(div, parks.firstElementChild);
}
