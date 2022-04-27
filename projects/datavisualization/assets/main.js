var width = 1200
var height = 600

var svg = d3.select("#my_dataviz")
  .append("svg")
    .attr("width", 1200)
    .attr("height", 600)

let data = [
  {
    day: "Wednesday, April 6th", 
    withFriends: 3, 
    outside: 0,
    socialSetting: 3,
    withCloseFriend: 3,
    happinessLevel: .7 
  },
  {
    day: "Thursday, April 7th", 
    withFriends: 0, 
    outside: 0,
    socialSetting: 0,
    withCloseFriend: 0,
    happinessLevel: .1
  },
  {
    day: "Friday, April 8th", 
    withFriends: 8, 
    outside: 5,
    socialSetting: 6,
    withCloseFriend: 4,
    happinessLevel: 1
  },  
  {
    day: "Saturday, April 9th", 
    withFriends: 10, 
    outside: 6,
    socialSetting: 8,
    withCloseFriend: 6,
    happinessLevel: 1
  },
  {
    day: "Sunday, April 10th", 
    withFriends: 2, 
    outside: 0,
    socialSetting: 0,
    withCloseFriend: 0,
    happinessLevel: .4
  },
  {
    day: "Monday, April 11th", 
    withFriends: 0, 
    outside: 0,
    socialSetting: 0,
    withCloseFriend: 0,
    happinessLevel: .2
  },
  {
    day: "Tuesday, April 12th", 
    withFriends: 2, 
    outside: 0,
    socialSetting: 0,
    withCloseFriend: 2,
    happinessLevel: .4
  },
  {
    day: "Wednesday, April 13th", 
    withFriends: 5, 
    outside: 0,
    socialSetting: 3,
    withCloseFriend: 0,
    happinessLevel: 1
  },
  {
    day: "Thursday, April 14th", 
    withFriends: 5, 
    outside: 0,
    socialSetting: 3,
    withCloseFriend: 5,
    happinessLevel: 1
  },
  {
    day: "Friday, April 15th", 
    withFriends: 6, 
    outside: 6,
    socialSetting: 0,
    withCloseFriend: 0,
    happinessLevel: 1
  },  
  {
    day: "Saturday, April 16th", 
    withFriends: 6, 
    outside: 3,
    socialSetting: 6,
    withCloseFriend: 2,
    happinessLevel: .7
  },
  {
    day: "Sunday, April 17th", 
    withFriends: 0, 
    outside: 0,
    socialSetting: 0,
    withCloseFriend: 0,
    happinessLevel: .2
  },
  {
    day: "Monday, April 18th", 
    withFriends: 1, 
    outside: 0,
    socialSetting: 0,
    withCloseFriend: 0,
    happinessLevel: .3
  },
  {
    day: "Tuesday, April 19th", 
    withFriends: 0, 
    outside: 0,
    socialSetting: 0,
    withCloseFriend: 0,
    happinessLevel: .1
  },
  {
    day: "Wednesday, April 20th", 
    withFriends: 6, 
    outside: 2,
    socialSetting: 4,
    withCloseFriend: 2,
    happinessLevel: .7
  }
]

var size = d3.scaleLinear()
    .domain([0, 5])
    .range([5,65]) 

 var Tooltip = d3.select("#my_dataviz")
    .append("div")
    .style("opacity", 0)
    .attr("class", "tooltip")
    .style("background-color", "white")
    .style("border", "solid")
    .style("border-width", "2px")
    .style("border-radius", "5px")
    .style("padding", "15px")
  
  var mouseover = function(d) {
    Tooltip
      .style("opacity", 1)
  }
  var mousemove = function(d) {
    Tooltip
      .html("<b>" + d.day + "</b>" + "<br>" + "Total Hours Spent With Friends: "+ d.withFriends + "<br>"+ "Hours Spent Outside: " + d.outside + "<br>" + "Hours Spent in a Social Setting: " + d.socialSetting + "<br>" + "Hours Spent With Close Friends: " + d.withCloseFriend)
      .style("left", (d3.mouse(this)[0]+2) + "px")
      .style("top", (d3.mouse(this)[1]) + "px")
  }
  var mouseleave = function(d) {
    Tooltip
      .style("opacity", 0)
  }

var node = svg.append("g")
  .selectAll("div")
  .data(data)
  .enter()

var defs = node.append("defs");
defs.append('pattern')
.data(data)
  .attr("id", function(d) { return d.id;}  )
.attr("width", 70 + "px")
  .attr("height", 105 + "px")
  .attr("x", 0)
  .attr("y", 0)
  .attr('patternUnits', 'userSpaceOnUse')
  .attr("preserveAspectRatio", "none")

var circle = node.append("circle")
.data(data)
     .attr("class", "node")
      .attr("r", function(d){return size(d.withFriends)})
      // .attr("fill", function(d){return color(0,0,0,d.happinessLevel)})
      // .attr("opacity", function(d){return opacity(d.happinessLevel)})
      // .style("opacity", d.happinessLevel)
      .style("opacity", .4)
      .attr("cx", width / 2)
      .attr("cy", height / 2)
      .on("mouseover", mouseover) 
      .on("mousemove", mousemove)
      .on("mouseleave", mouseleave)
      .call(d3.drag() 
           .on("start", dragstarted)
           .on("drag", dragged)
           .on("end", dragended));

//button controllers
var click1 = function(d) {
    circle
       .attr("r", function(d){ return size(d.withFriends)})
  }

  var click2 = function(d) {
    circle
       .attr("r", function(d){ return size(d.outside)})
  }

   var click3 = function(d) {
    circle
       .attr("r", function(d){ return size(d.socialSetting)})
  }

   var click4 = function(d) {
    circle
       .attr("r", function(d){ return size(d.withCloseFriend)})
  }

var clicking = d3.select("#one")
     .on("click", click1)

var clicking = d3.select("#two")
     .on("click", click2)

var clicking = d3.select("#three")
     .on("click", click3)

var clicking = d3.select("#four")
     .on("click", click4)


var simulation = d3.forceSimulation()
      .force("center", d3.forceCenter().x(width / 2).y(height / 2)) 
      .force("charge", d3.forceManyBody().strength(.1)) 
      .force("collide", d3.forceCollide().strength(.2).radius(function(d){ return (size(d.withFriends)+3) }).iterations(1)) 


simulation
    .nodes(data)
    .on("tick", function(d){
      circle
          .attr("cx", function(d){ return d.x; })
          .attr("cy", function(d){ return d.y; })
    });
function dragstarted(d) {
  if (!d3.event.active) simulation.alphaTarget(.05).restart();
  d.fx = d.x;
  d.fy = d.y;
}
function dragged(d) {
  d.fx = d3.event.x;
  d.fy = d3.event.y;
}
function dragended(d) {
  if (!d3.event.active) simulation.alphaTarget(.05);
  d.fx = null;
  d.fy = null;
}



     