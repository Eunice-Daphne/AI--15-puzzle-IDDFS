import java.util.*;

import java.io.*;
public class FifteenPuzzleBFS {
	private static HashMap<Frame,Integer> levelOfFrame = new HashMap(); //to store cuurentState and level
	private static HashMap<Frame,Frame> previousFrames  = new HashMap(); //to store currentState and its parent
	private static int framevalue;
	private static int a = 0;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Frame frame = null;
		frame = getInputArgs(args); //get input from argument
		checkpreviousFrames(frame, null); 
		
		try{

			long start = System.currentTimeMillis();
			Solution solution = breadthFirstSearch(frame,start);
			//Solution solution = iterativeDepthFirstSearch(frame,start);
			
			long duration = System.currentTimeMillis() - start;
			if(a==1)
				System.out.println("Solution cannot be found");
			else
				printSolutionAndData(frame, solution, duration);
		}
		catch(OutOfMemoryError e){
			System.out.println("Memory out of Bound");
		}
		
	}
	//calculate the memory
	public static double memoryUsage(){
		Runtime runtime = Runtime.getRuntime();
		return ((runtime.totalMemory() - runtime.freeMemory()) / 1024);
	}
	//parse the input to store in integer format
	public static Frame getInputArgs(String args[]){
		int[][] input = new int[4][4];
		int k = 0;
		for(int i=0; i<4; i++){
			for(int j=0; j<4; j++){
				String s = args[k];
				input[i][j] = Integer.parseInt(s);
				k++;
			}
		}
		return new Frame(input);

	}
	//function to print the ouput
	public static void printSolutionAndData(Frame frame, Solution solution, long time){
		
		String moves = "";
		for(Frame.Direction d: solution.solution.getSolutionPath())
			moves += d.toString().substring(0,1);
		
		System.out.println("Moves: "+moves);
		System.out.println("Number of Nodes expanded: "+solution.expandedNodes);
		System.out.println("Time Taken: "+time+"ms");
		System.out.println("Memory Used: "+solution.memory+"kb");
	}
	
	//Breadth First Search algorithm
	public static Solution breadthFirstSearch(Frame frame, long time){
		LinkedList<Frame> queue = new LinkedList<Frame>();
		queue.add(frame);
		int expandedNodes = 0;
		while(queue.size()>0 && System.currentTimeMillis()-time<=300000000){
			Frame currentFrame = queue.remove();
			if(currentFrame.isSolved()){
				return new Solution(currentFrame, expandedNodes, memoryUsage());
			}
			expandedNodes++;
			Frame.Direction[] moves = currentFrame.moveableDirections();
			for(Frame.Direction d: moves){
				Frame newFrame = currentFrame.move(d);
				if(checkpreviousFrames(newFrame,currentFrame))
				queue.add(newFrame);
			}
			
		}
		if(System.currentTimeMillis()-time>300000000)
		{
			a=1;
		}
		return null;
	}
	 //checking for the repeated states
		private static boolean checkpreviousFrames(Frame newFrame, Frame currentFrame) {
			boolean a = false;
        if(!levelOfFrame.containsKey(newFrame)){  
            framevalue = currentFrame==null ? 0 : levelOfFrame.get(currentFrame)+1;   
            levelOfFrame.put(newFrame, framevalue);         
            previousFrames.put(newFrame, currentFrame);    
            a = true;
          }
      return a;
    }
	//Defining the solution state	
	private static class Solution{
		public int expandedNodes;
		public Frame solution;
		public double memory;
		public Solution(Frame solution, int expandedNodes, double memory){
			this.expandedNodes = expandedNodes;
			this.solution = solution;
			this.memory = memory;
		}
	}
}
