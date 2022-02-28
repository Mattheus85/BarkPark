const cities = document.querySelector("#cities");

window.onload = async function (evt) {
    evt.preventDefault();
    console.log("Getting Location Data...");

    axios
    .get(
      "https://hdvd9zw9q5.execute-api.us-west-1.amazonaws.com/Alpha/locations"
    )
    .then((locationsResult) => {
      console.log(locationsResult);
      populateLocations(locationsResult.data.locationSet);
    });
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