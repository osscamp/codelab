package com.learning.java;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class CsvParser {
	
	public static void parse(String file) {
		List<Map<String, String>> dataList = new ArrayList<>();
		BufferedReader br  = null;
		try {
			br = new BufferedReader(new FileReader(file));
			List<String> colHeadings = null;
			String line = null;
			int ctr = 0;
			while((line = br.readLine()) != null) {
				if(line.trim().isEmpty()) {
					continue;
				}
				List<String>  values = extractValues(line);
				if(ctr == 0) {
					colHeadings = values;
				}else {
					if(values.size() != colHeadings.size()) {
						throw new IllegalArgumentException("column mismatch");
					}
					Map<String, String> vmap = new TreeMap<>();
					for(int i=0; i<values.size(); i++) {
						vmap.put(colHeadings.get(i), values.get(i));
					}
					dataList.add(vmap);
				}
				ctr++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println(dataList);
		
	}
	
	public static List<String> extractValues(String line) {
		List<String> vList = new ArrayList<String>();
		//cases
		//value in quotes
		//value without quote
		//value with escaped delimiter"
		//value with escaped quote
		//missing value? - error
		//empty value
		//single char value
		//value with space - should be quoted
		//extra spaces - valid
		//number of columns - any max?
		//multiple delimiter - error
		
		//base case - well formed simple case without qotes
		//"v10","v\,20",v30
		if(line == null || line.trim().isEmpty()) return vList;
		StringBuilder v = new StringBuilder();
		char prev = ' ';
		for(int i = 0; i<line.length(); i++) {
			char c = line.charAt(i);
			if(c == '\"' && prev != '\\') {
				prev = c;
				continue;
			}
			else if(c == ',' && prev != '\\') {
				vList.add(v.toString());
				v.delete(0, v.length());
			}else{
				if(c != '\\') {
					v.append(c);
				}
			}
			prev = c;
		}
		if(v.length() > 0) {
			vList.add(v.toString());
		}
		return vList;
	}
	
	public static void main(String[] args) {
		//CsvParser.extractValues("a\\,\\\"b1,\"c\\\"d1\",d");
		parse("test.csv");
	}
 
}
