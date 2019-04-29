package com.topdesk.cases.toprob.yoursolution;

import com.topdesk.cases.toprob.Coordinate;
import com.topdesk.cases.toprob.Grid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/*The map is a class, that gives me the needed information from the grid
in order to solve the problem. */

public class Map {

  private final int EMPTYFIELD = 0;
  private final int HOLE = 1;
  private final int ROOM = 2;
  private final int KITCHEN = 3;
  
  private final int row;
  private final int col;
  //the matrix, that shows, on witch cell i have already been.
  private boolean [][] visited;
  /*an int Matrix, that shows the full grid.
  0: Empty
  1: Hole
  2: Room
  3: Kitchen*/
  private int [][] map;
  Grid grid;
  
  public Map(Grid grid) {
    this.grid = grid;
    this.row = grid.getWidth();
    this.col = grid.getHeight();
    initMap(grid);
    visited = new boolean[row][col];
    if(grid.getHeight()<1)
      throw new IllegalArgumentException("The grids height must be larger than 1");
    if(grid.getWidth()<1)
      throw new IllegalArgumentException("the grids width must be larger than 1");
  }
  
  private int[][] initMap(Grid grid){
    System.out.println("height: "+grid.getHeight()+" Widht "+grid.getWidth());
    this.map = new int[row][col];
    
    List<Coordinate> holes = getHolesFromSet(grid.getHoles());
    
    for (int i = 0; i < map.length; i++) {
      for (int j = 0; j < map[i].length; j++) {

          map[i][j] = EMPTYFIELD;
      }
    }
    for (Coordinate hole : holes) {
      map[hole.getX()][hole.getY()] = HOLE;
    }
    System.out.println(holes);
    map[grid.getKitchen().getX()][grid.getKitchen().getY()] = KITCHEN;
    map[grid.getRoom().getX()][grid.getRoom().getY()] = ROOM;

    return map;
  }

  public int[][] getMap() {
    return map;
  }

  public int getHeight(){
    return map.length;
  }
  
  public int getWidth(){
    return map[0].length;
  }
  
  public MyCoordinate getRoom(){
    return new MyCoordinate(grid.getRoom().getX(), grid.getRoom().getY());
  }
  
  public MyCoordinate getKitchen(){
    return new MyCoordinate(grid.getKitchen().getX(), grid.getKitchen().getY());
  }
  
  
  public boolean isExplored(int row, int col) {
    return visited[row][col];
  }
  
  public boolean isHole(int row, int col) {
    if(grid.getHoles().contains(new Coordinate(row, col)))
      return true;
    else
      return false;
  }
  
  public void setVisited(int row, int col, boolean value) {
    visited[row][col] = value;
  }
  
   public boolean isValidLocation(int row, int col) {
     if ((row < 0) || (row >= map.length) || (col < 0) || (col >= map[0].length)) {
            return false;
        }
        return true;
}

  public boolean isBugLocation(int row, int col, int time) {
    Coordinate bug = grid.getBug(time);
    if  (bug.getX() == row && bug.getY() == col)
      return true;
    else
      return false;
  }

  boolean isEnd(int x, int y, MyCoordinate end) {
    return (x==end.getX() && y == end.getY());
  }
  
  public void reset() {
    for (int i = 0; i < visited.length; i++)
        Arrays.fill(visited[i], false);
  }
  public List<Coordinate> getHolesFromSet (Set<Coordinate> holes){
    List<Coordinate> hls = new ArrayList<>();
    Iterator itr = holes.iterator();
    while(itr.hasNext())
      hls.add((Coordinate) itr.next());
    return hls;
  }

}
