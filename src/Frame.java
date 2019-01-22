import java.awt.Point;
import java.util.*;



public class Frame {

	public enum Direction {UP, DOWN, LEFT, RIGHT} //possible movement of a blank
	private int[][] frame;
	private ArrayList<Direction> solutionPath;
	private Point posOfBlank;
	

	private static final int[][] goal = {
			{1, 2, 3, 4},
			{5, 6, 7, 8},
			{9, 10, 11, 12},
			{13, 14, 15, 0}
		};
	public Frame(int[][] frame){
		this.frame = frame;
		this.posOfBlank = findZeroInFrame(0);
		this.solutionPath = new ArrayList<Direction>();
	
	}
	// constructor used to keep track of solution path
	private Frame(int[][] frame, ArrayList<Direction> solutionPath){
		this.frame = frame;
		this.posOfBlank = findZeroInFrame(0);
		this.solutionPath = solutionPath;
	}
	// finds the location of the blank in the board
	private Point findZeroInFrame(int blank) throws IllegalStateException{
		for(int i=0; i<4; i++){
			for(int j=0; j<4; j++){
				if(frame[i][j] == blank){
					return new Point(j, i);
				}
			}
		}
		throw new IllegalStateException("Zero not found.");
	}
	// check to see if the board is solved
		public boolean isSolved(){
			for(int i=0; i<4; i++){
				for(int j=0; j<4; j++){
					if(frame[i][j] != goal[i][j]){
						return false;
					}
				}
			}
			return true;
		}
		
		// the valid directions in which you can move the blank 
		public Direction[] moveableDirections(){
			ArrayList<Direction> goodDirs = new ArrayList<Direction>();
			if(posOfBlank.x != 3)
				goodDirs.add(Direction.RIGHT);
			if(posOfBlank.x != 0)
				goodDirs.add(Direction.LEFT);
			if(posOfBlank.y != 3)
				goodDirs.add(Direction.DOWN);
			if(posOfBlank.y != 0)
				goodDirs.add(Direction.UP);

			return goodDirs.toArray(new Direction[0]);
		}
		// return a new board as a result of moving the blank 
		public Frame move(Direction d){
			ArrayList<Direction> thisSolution = new ArrayList<Direction>(this.solutionPath);
			switch(d){
				case UP:
					thisSolution.add(Direction.UP);
					return new Frame(swapBlank(posOfBlank, new Point(posOfBlank.x, posOfBlank.y-1)), thisSolution);
				case DOWN:
					thisSolution.add(Direction.DOWN);
					return new Frame(swapBlank(posOfBlank, new Point(posOfBlank.x, posOfBlank.y+1)), thisSolution);
				case LEFT:
					thisSolution.add(Direction.LEFT);
					return new Frame(swapBlank(posOfBlank, new Point(posOfBlank.x-1, posOfBlank.y)), thisSolution);
				case RIGHT:
					thisSolution.add(Direction.RIGHT);
					return new Frame(swapBlank(posOfBlank, new Point(posOfBlank.x+1, posOfBlank.y)), thisSolution);
			}
			throw new UnsupportedOperationException("Cannot move in that direction");
		}
		// construct the new board for .move()
		private int[][] swapBlank(Point a, Point b){
			int[][] newFrame = new int[4][4];
			for(int i=0; i<4; i++){
				for(int j=0; j<4; j++){
					if(i == a.y && j == a.x)
						newFrame[i][j] = frame[b.y][b.x];
					else if(i == b.y && j == b.x)
						newFrame[i][j] = frame[a.y][a.x];
					else
						newFrame[i][j] = frame[i][j];
				}
			}
			return newFrame;
		}
		// get the moves taken to get this board from the original
		public ArrayList<Direction> getSolutionPath(){
			return solutionPath;
		}

		public int[][] getFrame(){
			return this.frame;
		}
}

