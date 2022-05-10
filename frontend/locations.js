const parksList = document.querySelector("#parks");
const backToLocations = document.querySelector("#back-to-locations");
const urlParams = new URLSearchParams(window.location.search);
const currentLocation = urlParams.get("location");
const header = document.querySelector("#h2");
const title = document.querySelector("#title");

window.onload = async function (evt) {
  evt.preventDefault();
  createTitleAndHeader();
  // createLinkToUserPage();
  console.log("Getting Parks Data...");
  axios
    .get(
      "https://hdvd9zw9q5.execute-api.us-west-1.amazonaws.com/Alpha/parks?location=" +
        currentLocation
    )
    .then((parksResult) => {
      console.log(parksResult);
      populateParks(parksResult.data.parkList);
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

function createTitleAndHeader() {
  let h2 = document.createTextNode(currentLocation);
  header.appendChild(h2);

  let ti = document.createTextNode(currentLocation);
  title.appendChild(ti);
}

function createLinkToUserPage() {
  let li = document.createElement("li");
    let a = document.createElement("a");
    let text = document.createTextNode(park.name);

    a.setAttribute("href", `./park.html?id=${park.id}`);

    a.appendChild(text);
    li.appendChild(a);
}
