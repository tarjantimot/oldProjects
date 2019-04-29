
package com.topdesk.cases.toprob.yoursolution;

/*This Class was needed, becouse the coordinate class, that you make, did not 
contain enough information, like the passed time, or the children of the 
coordinate... I could not extend from com.topdesk.cases.toprob.Coordinate.java,
becouse it is a final class, so i made my own "MyCoordinate" class*/
public final class MyCoordinate {
  //position x
  private int x;
  //position y
  private int y;
  //the second, that we were here
  private int time;
  //the MyCoordinate, where we came here
  private MyCoordinate parent;

    public MyCoordinate(int x, int y) {
        this.x = x;
        this.y = y;
        this.parent = null;
        this.time = 0;
    }

    public MyCoordinate(int x, int y, MyCoordinate parent) {
        this.x = x;
        this.y = y;
        this.parent = parent;
        if(null == parent)
          this.time=0;
        else
          this.time=parent.getTime()+1;
    }

  public MyCoordinate(int x, int y, int time, MyCoordinate parent) {
    this.x = x;
    this.y = y;
    this.time = time;
    this.parent = parent;
  }

    
    
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public MyCoordinate getParent() {
        return parent;
    }

  public int getTime() {
    return time;
  }

    
  @Override
  public String toString() {
    return "MyCoordinate{" + "x=" + x + ", y=" + y + '}';
  }
  
}
