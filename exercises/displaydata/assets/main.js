let day = [
  {
    day: "Monday", 
    socialHours:0, 
    happinessLevel: 2
  },
  {
    day: "Wednesday", 
    socialHours: 3, 
    happinessLevel: 7
  },
  {
    day: "Saturday", 
    socialHours: 10, 
    happinessLevel: 10
  }
];

//Display contents

let container = document.querySelector(".day-container");

function displayDays(day) {
  // creates a new div; appends that div to the container
  let newItem = document.createElement("div");
  let dayDiv = container.appendChild(newItem);
  // adds a class to each new div
  newItem.classList.add("day");
  // places the show name to the div
  dayDiv.innerHTML = day.day;
  dayDiv.style.padding = day.happinessLevel * 10 + "px";
  console.log(day);
}

day.forEach(displayDays);
