const parksList = document.querySelector("#parks");

window.onload = async function (evt) {
  evt.preventDefault();
  console.log("Getting Parks Data...");
  axios
    .get("https://hdvd9zw9q5.execute-api.us-west-1.amazonaws.com/Alpha/parks")
    .then((parksResult) => {
      console.log(parksResult);
      populateParks(parksResult.data.parkList);
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
  parksList.appendChild(div);
}
