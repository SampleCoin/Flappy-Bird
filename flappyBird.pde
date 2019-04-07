ArrayList<Bar> b=new ArrayList<Bar>();
int speed=6, score;
boolean end=false;


void setup() {
  size(1500, 800);
  noStroke();
  ellipseMode(CORNER);
  textSize(50);
  textAlign(CENTER,CENTER);
  posx=width*0.2;
}
void draw() {
  background(255);
  score=0;


  if (frameCount%(speed*20)==0)b.add(new Bar());
  for (Bar b : b) {
    b.show();
    if (b.pos<posx)score++;
  }

  bird();
}



void keyPressed() {
  if (key=='r'&&end) {

    b=new ArrayList<Bar>();
    posy=height/3;
    end=false;
    score=0;
  }
}
