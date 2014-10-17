package aoesoft.sme.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

public class PinYinUtil {
	/**
	 * 数量不限.
	 */
	private static int UNLIMITED = -1;
	/**
	 * 最常见字.
	 */
	private static int LEVEL_FREQUENCY = 2;
	/**
	 * 常见字.
	 */
	private static int LEVEL_NORMAL = 1;
	/**
	 * 非常用字.
	 */
	private static int LEVEL_ALL = 0;
	
	private static Multimap<String, Entry> meta;
	private static class Entry{
		private String name;
		private String code;
		private String firstChar;
		private String ending;
		private int order = -1;
	}
	
	static{
		init();
	}
	
	private static void init(){
		if(meta == null){
			meta = ArrayListMultimap.create();
		}
		
		BufferedReader br = null;
		String line;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream("etc/py.txt"), "UTF-8"));
			for(line = br.readLine(); line != null; line = br.readLine()){
				try {
					String[] columns = line.split(",");
					
					if(columns == null || columns.length != 6)
						System.err.println("error to parse line :" +line);
					else{
						Entry entry = new Entry();
						entry.name = columns[0];
						entry.code = columns[1];
						entry.firstChar = columns[2];
						entry.ending = columns[3];
						entry.order = Integer.parseInt(columns[5]);
						
						meta.put(entry.name, entry);
					}
				} catch (Exception e) {
					System.err.println("error to parse line :" +line);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String getCode(String name){
		if(meta.containsKey(name)){
			Entry entry = new Entry();
			for(Entry e : meta.get(name)){
				if(e.order > entry.order)
					entry = e;
			}
			return entry.code;
		}
		return null;
	}
	
	public static String[] getFrequencySuggest(String firstChar, int counts){
		return getSuggest(firstChar, counts, LEVEL_FREQUENCY);
	}
	
	public static String[] getAllFrequencySuggest(String firstChar){
		return getFrequencySuggest(firstChar, UNLIMITED);
	}
	
	public static String[] getSuggest(String firstChar, int counts){
		return getSuggest(firstChar, counts, LEVEL_ALL);
	}
	
	public static String[] getAllSuggest(String firstChar){
		return getSuggest(firstChar, UNLIMITED, LEVEL_ALL);
	}
	
	private static String[] getSuggest(String begin, int counts, int level){
		int count = 0;
		List<String> suggest = new LinkedList<String>();
		for(Entry entry : meta.values())
			if(entry.firstChar.equalsIgnoreCase(begin)){
				if(counts == UNLIMITED)
					counts = Integer.MAX_VALUE;
				
				if(count < counts && entry.order >= level){
					suggest.add(entry.name);
				count++;
			}
		}
		return suggest.toArray(new String[0]);
	}
	
	public static void main(String[] args) {
		System.out.println(getCode("丂"));
		for(String s : getFrequencySuggest("T", 5))
			System.out.println(s);
		
	}
}
