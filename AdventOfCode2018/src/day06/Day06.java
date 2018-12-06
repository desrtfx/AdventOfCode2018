package day06;

import java.awt.Point;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import util.FileIO;

public class Day06 {
	
	private static final String FILENAME = "./resources/Input_Day06.txt";

	private static Map<Integer,Point> data = new HashMap<>();

	static {
		List<String[]> rawData = FileIO.getFileLinesSplit(FILENAME, ", ");
		int count = 0;
		for (String[] item : rawData) {
			data.put(count, new Point(Integer.parseInt(item[0]),Integer.parseInt(item[1])));
			count++;
		}
		
	}
	
	
	
	public static int part1(Map<Integer,Point> data) {
		int maxX = Integer.MIN_VALUE;
		int maxY = Integer.MIN_VALUE;
		for(int i = 0; i < data.size(); i++) {
			Point p = data.get(i);
			if (p.x > maxX) {
				maxX = p.x;
			}
			if (p.y > maxY) {
				maxY = p.y;
			}
		}
		
		int[][] grid = new int[maxY + 1][maxX + 1];
		Map<Integer, Integer> areas = new HashMap<>();
		
		for(int y = 0; y <= maxY; y++) {
			for(int x = 0; x <= maxX; x++) {
				int minDist = Integer.MAX_VALUE;
				int minIndex = -1;
				
				for(int i = 0; i < data.size(); i++) {
					Point p = data.get(i);
					int dist = Math.abs(x - p.x) + Math.abs(y - p.y);
					if (dist < minDist) {
						minDist = dist;
						minIndex = i;
					} else if (dist == minDist) { // point has equidistance
						minIndex = -1;
					}
				}
				grid[y][x] = minIndex;
				areas.put(minIndex, areas.getOrDefault(minIndex, 0) + 1);
			}
		}
		
		// remove border regions because infinite
		for(int x = 0; x < maxX; x++) {
			areas.remove(grid[0][x]);
			areas.remove(grid[maxY][x]);
		}
		for(int y = 0; y < maxY; y++) {
			areas.remove(grid[y][0]);
			areas.remove(grid[y][maxX]);
		}
		
		Optional<Integer> big = areas.values().stream().reduce(Integer::max);
		
		
		return big.get();
	}
	
	public static int part2(Map<Integer,Point> data) {
		int maxX = Integer.MIN_VALUE;
		int maxY = Integer.MIN_VALUE;
		for(int i = 0; i < data.size(); i++) {
			Point p = data.get(i);
			if (p.x > maxX) {
				maxX = p.x;
			}
			if (p.y > maxY) {
				maxY = p.y;
			}
		}
		int inArea = 0;
		for(int y = 0; y <= maxY; y++) {
			for(int x = 0; x <= maxX; x++) {
				int size = 0;
				for(Point p : data.values()) {
				    int dist = Math.abs(x - p.x) + Math.abs(y - p.y);
				    size += dist;
				}
				if (size < 10000) {
					inArea++;
				}
			}
		}
		return inArea;
	}
	
	public static void main(String[] args) {
		// part 1
		System.out.printf("Result of Part 1 with input data: %d%n", part1(data));
		
		// Test part 2
		System.out.printf("Result of Part 2 with input data: %d%n", part2(data));
		
	}
	
	
	
	

}
