class Bar {

  float pos=width;
  int gabPos=int(random(height-300));


  Bar() {}
  void show() {

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
float acc=0.6;

void bird() {

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
      if (posy>height||frameCount%2==0&&pixels[constrain(loc(int(x+posx), int(y+posy)), 0, pixels.length-1)]==color(0))end=true;
    }
  }
}



int loc(int x, int y) {return x+y*width;}
