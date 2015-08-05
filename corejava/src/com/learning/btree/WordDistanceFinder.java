package com.learning.btree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordDistanceFinder {
	
	Map<String, List<Integer>> wordMap = new HashMap<>();
	
/*	public WordDistanceFinder(List<String> words) {
		for(int i=0; i<words.size(); i++) {
			String word = words.get(i);
			List<Integer> wordIndex = wordMap.get(word);
			if(wordIndex == null) {
				wordIndex = new ArrayList<>();
				wordIndex.add(i);
				wordMap.put(word, wordIndex);
			} else {
				wordIndex.add(i);
			}
		}
	}*/
	List<String> words = null;
	public WordDistanceFinder(List<String> words) {
		this.words = words;
	}
	
	public int distance1(String word1, String word2) {
		int idx1 = -1;
		int idx2 = -1;
		int min=Integer.MAX_VALUE;
		for(int i=0; i<words.size(); i++) {
			if(words.get(i).equals(word1)) {
				idx1 = i;
			}
			else if(words.get(i).equals(word2)) {
				idx2 = i;
			}
			if(idx1 != -1 && idx2 != -1 && Math.abs(idx1 - idx2) < min) {
				min = Math.abs(idx1 - idx2);
			}
		}
		return min;
	}
	
	public int distance(String word1, String word2) {
		List<Integer> wordIndex1 = wordMap.get(word1); 
		List<Integer> wordIndex2 = wordMap.get(word2);
		if(wordIndex1 == null 
				|| wordIndex2 == null) {
			return -1;
		}
		int i=0;
		int j=0;
		int min = Integer.MAX_VALUE;


		while(i < wordIndex1.size() && j < wordIndex2.size()) {
			int w1 = wordIndex1.get(i);
			int w2 = wordIndex2.get(j);
			int temp = Math.abs(w2-w1);
			if(temp < min) {
				min = temp;
			}
			if(w1 < w2) {
				i++;
			}else{
				j++;
			}
		}
		return min;
	}
	
    public int distance2 (String wordOne, String wordTwo) {
        List<Integer> listOne = wordMap.get(wordOne); 
        List<Integer> listTwo = wordMap.get(wordTwo); 
        if(listOne == null || listTwo == null) {
            return -1;
        }
    
        int i=0;
        int j =0;
        int min = Integer.MAX_VALUE;
        while(i<listOne.size() && j<listTwo.size() ){ 
	        if(listOne.get(i) > listTwo.get(j)) {
	            int diff = listOne.get(i) - listTwo.get(j);
	            if(diff < min) {
	                min = diff;
	            }
	            j++;
	        }  else {
	             int diff = listTwo.get(j) - listOne.get(i);     
	             if(diff < min) {
	                min = diff;
	            }
	            i++;
	        }
        }      
        
        return min;
       }
	
	public static void main(String[] args) {
		WordDistanceFinder finder = new WordDistanceFinder(Arrays.asList("the", "quick", "brown", "fox", "quick", "jumped", "over", "the", "jumped" ));
		System.out.println("min dist "+finder.distance1("jumped", "over"));

	}

}
