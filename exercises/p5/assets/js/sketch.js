let slider;

function setup() {
  let canvas = createCanvas(windowWidth, windowHeight);
  canvas.position(0, 0);
  canvas.class("container");
  slider = createSlider(.25, 10, .5);
  slider.class("slider");
}


function windowResized() {
  resizeCanvas(windowWidth, windowHeight);
  background("NavajoWhite");
}

function draw() {
  r =  random(0, 255);
  g = random(0, 255);
  b = random(0, 255);
  x = random(0,500);
  y = random(0,500);
  background(r, g, b);
  noStroke();
  ellipse(x, y, 60, 60);
  fill(r,g,b);
  frameRate(slider.value());
}