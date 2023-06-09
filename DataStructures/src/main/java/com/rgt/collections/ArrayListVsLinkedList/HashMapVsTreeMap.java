package com.rgt.collections.ArrayListVsLinkedList;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class HashMapVsTreeMap {
	private static long getMemoryUsage() {
		Runtime runtime = Runtime.getRuntime();
		return runtime.totalMemory() - runtime.freeMemory();
	}

	public static void main(String[] args) {
		Map<Integer, String> hashMap = new HashMap<>();

		long startTime, endTime;
		long memoryBefore, memoryAfter;

		/**
		 * HashMap Insertion ,Deletion Time and Space complexity
		 */

		startTime = System.nanoTime();
		memoryBefore = getMemoryUsage();
		for (int i = 0; i < 100000; i++) {
			hashMap.put(i, "value" + i);
		}
		memoryAfter = getMemoryUsage();
		long spaceComplexityInsertion = memoryAfter - memoryBefore;
		endTime = System.nanoTime();
		long hashMapTimeComplexityInsertion = endTime - startTime;
		hashMap.remove(1000);
		memoryAfter = getMemoryUsage();
		long spaceComplexityForDeletion = memoryAfter - memoryBefore;
		endTime = System.nanoTime();
		long hashMapTimeComplexityDeletion = endTime - startTime;
		System.out.println("********HashMap***********");
		System.out.println("HashMap Time Complexity for Insertion :" + hashMapTimeComplexityInsertion + "ns");
		System.out.println("HashMap Space Complexity for Insertion :" + spaceComplexityInsertion + "bytes");
		System.out.println("HashMap Time Complexity for Deletion :" + hashMapTimeComplexityDeletion + "ns");
		System.out.println("HashMap Space Complexity for Deletion :" + spaceComplexityForDeletion + "bytes");
		System.out.println("----------------------------------------------");

		/**  * For Linked List Insertion ,Deletion Time and Space Complexity  */

		Map<Integer, String> treeMap = new TreeMap<>();
		for (int i = 0; i < 100000; i++) {
			treeMap.put(i, "value" + i);
		}
		memoryAfter = getMemoryUsage();
		long spaceComplexityForTreeMapInsertion = memoryAfter - memoryBefore;
		endTime = System.nanoTime();
		long treeMapTimeComplexityForInsertion = memoryAfter - memoryBefore;
		treeMap.remove(1000);
		memoryAfter = getMemoryUsage();
		long spaceComplexityForTreeMapDeletion = memoryAfter - memoryBefore;
		endTime = System.nanoTime();
		long treeMapTimeComplexityDeletion = endTime - startTime;
		System.out.println("********TreeMap***********");
		System.out.println("TreeMap Time Complexity for Insertion :" + treeMapTimeComplexityForInsertion + "ns");
		System.out.println("TreeMap Space Complexity for Insertion :" + spaceComplexityForTreeMapInsertion + "bytes");
		System.out.println("TreeMap Time Complexity for Deletion :" + treeMapTimeComplexityDeletion + "ns");
		System.out.println("TreeMap Space Complexity for Deletion :" + spaceComplexityForTreeMapDeletion + "bytes");

	}

}
