package com.learning.java;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class JsonEval {
	
	public Set<Character> tokenSet;
	
	public JsonEval() {
		tokenSet = new HashSet<Character>();
		tokenSet.addAll(Arrays.asList('{', '}', '\"', ',', ':'));
	}
	
	public Map<String, Object> jsoneval(String jj) {
		if(jj == null || jj.isEmpty()) {
			throw new IllegalArgumentException("null input");
		}
		Map<String, Object> returnMap = new HashMap<>();
		//parseR(jj, returnMap);
		int v =parseTree(jj, returnMap);
		return returnMap;
	}
	
	private void parseR(String jj, Map<String, Object> jsonMap) {
		//{"k1":"v1","k2":"v2"}
		boolean keyStart = false;
		boolean valStart = false;
		boolean objectStart = false;
		boolean objectEnd = false;

		boolean quotedValue = false;
		char prevToken = ' ';
		StringBuilder jText = new StringBuilder();
		String tagKey = null;
		String tagVal = null;
		for(int i=0; i<jj.length(); i++) {
			char c = jj.charAt(i);
			if(c == ' ') {
				continue;
			}
			if(c == '{') {
				objectStart = true;
				prevToken = c;
				continue;
			}
			if(c != '{' && !objectStart) {
				System.out.println("invalid json start");
			}
			if(c == '\"' && !keyStart  && prevToken != ':' && !valStart) {
				if(jsonMap.size() > 0 && prevToken != ',') {
					System.out.println("missing comma json");
				}
				keyStart = true;
				prevToken = c;
				continue;
			}if(c == '\"' && keyStart) {
				keyStart = false;
				tagKey = jText.toString();
				jText = new StringBuilder();
				prevToken = c;
			}
			if(keyStart) {
				jText.append(c);
			}
			if(!keyStart && prevToken == ':') {
				valStart = true;
				if(c == '\"') {
					quotedValue = true;
					prevToken = c;
					continue;
				}
			}if((c == '\"' && valStart && quotedValue)
					 || (valStart && (c == ',' || c=='}'))) {
				valStart = false;
				quotedValue = false;
				tagVal = jText.toString();
				jText = new StringBuilder();
				jsonMap.put(tagKey, tagVal);
			}
			if(valStart && !tokenSet.contains(c)) {
				jText.append(c);
			}
			if(c=='}') {
				objectEnd = true; 
			}
			if(tokenSet.contains(c) ) {
				prevToken = c;
			}
		}
		if(!objectEnd) {
			System.out.println("malformed json - object end not found");
		}
	}
	
	private int parseTree(String jj, Map<String, Object> jsonMap) {
		//{"k1":"v1","k2":"v2"}
		if(jj == null || jj.isEmpty()) {
			return -1;
		}
		boolean keyStart = false;
		boolean valStart = false;
		boolean objectStart = false;
		boolean objectEnd = false;

		boolean quotedValue = false;
		char prevToken = ' ';
		StringBuilder jText = new StringBuilder();
		String tagKey = null;
		String tagVal = null;
		for(int i=0; i<jj.length(); i++) {
			char c = jj.charAt(i);
			if(c == ' ') {
				continue;
			}
			if(c == '{') {

				if(tagKey != null) {
					Map<String, Object> childMap = new HashMap<>();
					jsonMap.put(tagKey, childMap);
					int ctr = parseTree(jj.substring(i+1), childMap);
					System.out.println("cmap "+childMap);
					System.out.println("jsonm "+jsonMap);
					i += ctr;
					if(i >= jj.length() - 1) {
						return i;
					}
				}

			}

			if(c == '\"' && !keyStart  && prevToken != ':' && !valStart) {
				if(jsonMap.size() > 0 && prevToken != ',') {
					System.out.println("missing comma json");
				}
				keyStart = true;
				prevToken = c;
				continue;
			}if(c == '\"' && keyStart) {
				keyStart = false;
				tagKey = jText.toString();
				System.out.println("tagk "+tagKey);

				jText = new StringBuilder();
				prevToken = c;
			}
			if(keyStart) {
				jText.append(c);
			}
			if(!keyStart && prevToken == ':') {
				valStart = true;
				if(c == '\"') {
					quotedValue = true;
					prevToken = c;
					continue;
				}
			}if((c == '\"' && valStart && quotedValue)
					 || (valStart && (c == ',' || c=='}'))) {
				valStart = false;
				quotedValue = false;
				tagVal = jText.toString();
				System.out.println("tagv "+tagVal);
				jText = new StringBuilder();
				if(tagVal.isEmpty()) {return -1;}
				jsonMap.put(tagKey, tagVal);
			}
			if(valStart && !tokenSet.contains(c)) {
				jText.append(c);
			}

			if(tokenSet.contains(c) ) {
				prevToken = c;
			}
			if(c=='}') {
				objectEnd = true; 
				return i;
			}
		}
		return -1;
	}
	
	public static void main(String[] args) {
		JsonEval jval = new JsonEval();
		String json = "{\"k1\":\"val1\",\"k2\":{\"k3\":\"2\",\"k4\":5}}";
		Map<String, Object> data = jval.jsoneval(json);
		System.out.println(data);
		
	}

}
