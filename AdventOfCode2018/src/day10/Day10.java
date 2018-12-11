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

}
