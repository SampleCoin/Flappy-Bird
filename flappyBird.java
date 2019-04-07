import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class flappyBird extends PApplet {

ArrayList<Bar> b=new ArrayList<Bar>();
int speed=6, score;
boolean end=false;


public void setup() {
  
  noStroke();
  ellipseMode(CORNER);
  textSize(50);
  textAlign(CENTER,CENTER);
  posx=width*0.2f;
}
public void draw() {
  background(255);
  score=0;


  if (frameCount%(speed*20)==0)b.add(new Bar());
  for (Bar b : b) {
    b.show();
    if (b.pos<posx)score++;
  }

  bird();
}



public void keyPressed() {
  if (key=='r'&&end) {

    b=new ArrayList<Bar>();
    posy=height/3;
    end=false;
    score=0;
  }
}
class Bar {

  float pos=width;
  int gabPos=PApplet.parseInt(random(height-300));


  Bar() {}
  public void show() {

    fill(0);
    rect(pos, 0, 120, height);
    fill(255);
    rect(pos, gabPos, 120, 300);

    if (!end)pos-=speed;
  }
}



float posy=200;
float posx;
int w=100;
float vel=0;
float acc=0.6f;

public void bird() {

  if (!end)posy+=vel;
  if (!end)vel+=acc;
  
  
  fill(1);
  ellipse(posx, posy, w, w);
  fill(170);
  text(score,width/2,50);
  fill(255,0,0);
  if(end)text("Failed! press r to retry!",width/2,height/2);
  
  if ((keyPressed||mousePressed)&&posy>0) {
    vel=-11;
    keyPressed=false;
    mousePressed=false;
  }



  loadPixels();
  for (int x=-5; x<w+10; x++) {
    for (int y=-5; y<w+10; y++) {
      if (posy>height||frameCount%2==0&&pixels[constrain(loc(PApplet.parseInt(x+posx), PApplet.parseInt(y+posy)), 0, pixels.length-1)]==color(0))end=true;
    }
  }
}



public int loc(int x, int y) {return x+y*width;}
  public void settings() {  size(1500, 800); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "flappyBird" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
