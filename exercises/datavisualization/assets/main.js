let planes = [
  {name: "12:00", hour: 8},
  {name: "12:30", hour: 21},
  {name: "1:00", hour: 5},
  {name: "2:00", hour: 14}
  ];

  // One: Use the amount to indicate how big each circle is

// First show all the planes and create divs for each of them
let container = document.querySelector(".garden");
// create a counter variable that will give us each planes number in the array
let i = 0;

function displayPlanes(plane) {
  // creates a new div; appends that div to the container
  let newItem = document.createElement("div");
  let planeDiv = container.appendChild(newItem);
  // increase the counter variable by one
  i++;
  // adds a shared class to each new div
  newItem.classList.add("plane");
  // adds a specific class to each new div
  newItem.classList.add("plane" + [i]);
  // places the plane name and hour to the div
  planeDiv.innerHTML = plane.name;
  // sets height of plane to the amount. try changing height to width, or padding
  planeDiv.style.width = plane.hour * 50 + "px";
  planeDiv.style.animationDuration = plane.hour + "s";
  console.log(plane);
}
// loops through the planes and runs the displayplanes function for each one
planes.forEach(displayPlanes);