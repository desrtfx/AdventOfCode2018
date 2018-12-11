package day11;

import java.awt.Point;

public class Day11 {
	
	static class Cell {
		int rackID;
		int powerLevel;
		Point loc;
		
		public Cell(Point loc, int gridSerial) {
			this.loc = loc;
			rackID = loc.x + 10;
			powerLevel = rackID * loc.y;
			powerLevel += gridSerial;
			powerLevel *= rackID;
			powerLevel = (powerLevel % 1000) / 100;
			powerLevel -=5;
		}
	}

	private static final int GRID_SERIAL = 9005;
	
	private static Cell[][] grid = new Cell[300][300];
	private static int[][] powers = new int[300][300];
	
	static {
		for(int y = 0; y < grid.length; y++) {
			for(int x = 0; x < grid[y].length; x++) {
				grid[y][x] = new Cell(new Point(x+1,y+1), GRID_SERIAL);
				powers[y][x] = grid[y][x].powerLevel;
			}
		}
	}
	
	public static int calcTP(Point p, int size) {
		int pwr = 0;
		for(int y = 0; y < size; y++) {
			for(int x = 0; x < size; x++) {
				pwr += powers[p.y + y][p.x + x];
			}
		}
		return pwr;
	}
	
	public static void part1() {
		int maxPower = Integer.MIN_VALUE;
		Point maxP = new Point(0,0);
		int size = 3;
		for(int y = 0; y < grid.length - size; y++) {
			for(int x = 0; x < grid[y].length - size; x++) {
				int tp = calcTP(grid[y][x].loc,size);
				if (tp > maxPower) {
					maxPower = tp;
					maxP = grid[y][x].loc;
				}
			}
		}
		System.out.println("Max Power: " + maxPower + " occurs at: " + (maxP.x + 1) + "," + (maxP.y + 1));
		
	}
	
	
	public static void part2() {
		int maxPower = Integer.MIN_VALUE;
		Point maxP = null;
		int maxSize = -1;
		for(int size = 1; size <= 300; size++) {
			for(int y = 0; y < grid.length - size; y++) {
				for(int x = 0; x < grid[y].length - size; x++) {
					int tp = calcTP(grid[y][x].loc,size);
					if (tp > maxPower) {
						maxPower = tp;
						maxP = grid[y][x].loc;
						maxSize = size;
					}
				}
			}
		}
		System.out.println("Max Power: " + maxPower + " occurs at: " + (maxP.x + 1) + "," + (maxP.y + 1) + "," + maxSize);
	}
	
	
	
	public static void main(String[] args) {
		part1();
		part2();
	}
}
