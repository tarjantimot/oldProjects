package com.topdesk.cases.toprob.yoursolution;

import java.util.List;

import com.topdesk.cases.toprob.Grid;
import com.topdesk.cases.toprob.Instruction;
import com.topdesk.cases.toprob.Solution;
import java.util.ArrayList;
import java.util.LinkedList;

public class YourSolution implements Solution {
  
  
  //NORTH, SOUTH, WEST, EAST, on a matrix. 
  private static final int[][] DIRECTIONS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

  @Override
  public List<Instruction> solve(Grid grid, int time) {
    
    if (time < 0) {
      throw new IllegalArgumentException("The time must be larger or equal to than 0");
    }

    Map map = new Map(grid);
    List<Instruction> solution = new ArrayList<>();

    //our way to the Kitchen
    solution.addAll(backTrackPathToIntstructions(
           calculateRoute(map, time, map.getRoom(), map.getKitchen())));
    /*On the previous method, we get to the kitchen, and we need to stay there 
    for 5 seconds*/
    for (int i = 0; i < 5; i++) {
      solution.add(Instruction.PAUSE);
    }
    /*When we go back from the kitchen to the room, we need to know, how much
    time have passed to calculate the location of the bug.*/
    int currentTime = solution.size()+time;
    
    //Our way back from the kitchen to the room
    solution.addAll(backTrackPathToIntstructions(
            calculateRoute(map, currentTime, map.getKitchen(), map.getRoom())));
    System.out.println(solution);
    return solution;
  }

  public List<MyCoordinate> calculateRoute(Map maze, int time, MyCoordinate begin, MyCoordinate end) {
    //setting up our starting location.
    MyCoordinate start = new MyCoordinate(begin.getX(), begin.getY(), time, null);
    LinkedList<MyCoordinate> nextToVisit = new LinkedList<>();
    nextToVisit.add(start);
    maze.reset();
    while (!nextToVisit.isEmpty()) {
      MyCoordinate cur = nextToVisit.remove();

      //We check, are we on the destination yet.
      if ((end.getX() == cur.getX() && end.getY() == cur.getY())) {
        return backTrackPath(cur);
      }

      /*in a fore loop, we check all the four directions that we can make.*/
      

      for (int[] direction : DIRECTIONS) {
        MyCoordinate coordinate
                = new MyCoordinate(cur.getX() + direction[0],
                        cur.getY() + direction[1], cur);
        /*first, on that coordinate there will be the bug, we will PAUSE.*/
        if (maze.isBugLocation(coordinate.getX(), coordinate.getY(),coordinate.getTime())) {
          coordinate  = new MyCoordinate(cur.getX(), cur.getY(), cur);
          nextToVisit.add(coordinate);
        } else {
          /*Then we check is the next step a hole. If yes, than we say, we've 
          been there, and we will never try to step to that coordinate*/
          if (maze.isHole(coordinate.getX(), coordinate.getY())) {
            maze.setVisited(coordinate.getX(), coordinate.getY(), true);
          } else {
            /*Than we check, is the next step a valid step (on the grid/map), 
            and did we visit that coordinate*/
            if (!maze.isValidLocation(coordinate.getX(), coordinate.getY()) || 
                    (maze.isExplored(coordinate.getX(), coordinate.getY()))) {
            } else {
              /*If none of the above, than we should add to nextToVisit, and 
              we can keep going from that coordinate*/
              nextToVisit.add(coordinate);
              maze.setVisited(cur.getX(), cur.getY(), true);
            }
          }
        }
      }
    }
    /*If we cannot get to the end of the route, we throw an 
    IllegalArgumentException*/
    throw new IllegalArgumentException("The grid is unsolvable.");
  }

  private List<MyCoordinate> backTrackPath(MyCoordinate cur) {
    List<MyCoordinate> path = new ArrayList<>();
    MyCoordinate iter = cur;
    /*On this loop, we will backtrack our rute, becouse every MyCoordinate 
    contains its parent MyCoordinate, where we get to that MyCoordinate.
    If it gets to the "null" that means, it was our starting MyCoordinate*/
    while (iter != null) {
      path.add(iter);
      iter = iter.getParent();
    }

    return path;
  }

  
  private List<Instruction> backTrackPathToIntstructions(
          List<MyCoordinate> backTrackPath) {
    
    List<Instruction> instructions = new ArrayList<>();
    
    /*With a "reverse" for loop we will decode the changes of the coordinates
    to make intsructions, that we can return with, and solve the task.*/
    for (int i = backTrackPath.size() -1 ; i >= 1; i--) {
    MyCoordinate coordinate = backTrackPath.get(i);
      if (coordinate.getX() > backTrackPath.get(i - 1).getX()) {
        instructions.add(Instruction.WEST);
      } else if (coordinate.getX() < backTrackPath.get(i - 1).getX()) {
        instructions.add(Instruction.EAST);
      } else if (coordinate.getY() > backTrackPath.get(i - 1).getY()) {
        instructions.add(Instruction.NORTH);
      } else if (coordinate.getY() < backTrackPath.get(i - 1).getY()) {
        instructions.add(Instruction.SOUTH);
      } else {
        instructions.add(Instruction.PAUSE);
      }
    }
    return instructions;
  }
}

/*Final note:
This solution was based on a tutorial, that I found on the internet, and on my
previous project, and experience, becouse I have little experience on AI projects
(only one A* algorithm with a couple of heurestics. You can find the tutorial at 
https://www.baeldung.com/java-solve-maze This was the core of my solution, but 
of course i was needed to customize and improve it to solve this task.

Created by Timót Tarján 2018*/
