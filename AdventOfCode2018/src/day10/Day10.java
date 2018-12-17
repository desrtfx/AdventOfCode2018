package day10;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import util.FileIO;

public class Day10 {

	static class Star {
		Point place;
		Point speed;
		
		public Star() {
			place = null;
			speed = null;
		}
		
		public Star(Star s) {
			place = new Point(s.place);
			speed = new Point(s.speed);
		}
		
		public void tick() {
			place.translate(speed.x, speed.y);
		}
		
	}
	
	
	public static final String FILENAME = "./resources/Input_Day10.txt";
	static final String regex = ".*<\\s*(-?\\d+),\\s*(-?\\d+).*<\\s*(-?\\d+),\\s*(-?\\d+)";
	static final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
	
	static List<Star> data = new ArrayList<>();
	
	static {
		List<String> rawdata = FileIO.getFileAsList(FILENAME);
		for(String line : rawdata) {
			Matcher m = pattern.matcher(line);
			if (m.find()) {
				Star s = new Star();
				s.place = new Point(Integer.parseInt(m.group(0)),Integer.parseInt(m.group(1)));
				s.speed = new Point(Integer.parseInt(m.group(2)),Integer.parseInt(m.group(3)));
				data.add(s);
			}
		}
	}
	

	
	public static void part1() {
		
		// copy data
		List<Star> stars = new ArrayList<>();
		for(Star item : data) {
			stars.add(new Star(item));
		}
		
		int minDist = Integer.MAX_VALUE;
		int generation = 0;
		int xMin = Integer.MAX_VALUE;
		int xMax = Integer.MIN_VALUE;
		int xOffs;
		
		
		// simulation
		for(int i = 0; i < 1_000_000; i++) {
			xMin = Integer.MAX_VALUE;
			xMax = Integer.MIN_VALUE;
			// One tick
			for(Star s : stars) {
				s.tick();
				if(s.place.x < xMin) {
					xMin = s.place.x;
				}
				if(s.place.x > xMax) {
					xMax = s.place.x;
				}
			}
			// get distance
			int dist = Math.abs(xMax-xMin);
			if(dist < minDist) {
				minDist = dist;
				generation = i;
				xOffs = xMin;
			}
		}
		
		// reset
		stars = new ArrayList<>();
		for(Star item : data) {
			stars.add(new Star(item));
		}
		
		for(int i = 0; i <= generation; i++) {
			for(Star s: stars) {
				s.tick();
			}
		}
		
		
		
		
		
	}

}
