package com.rgt.collections.ArrayListVsLinkedList;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class HashSetVsTreeSet {

	private static long getMemoryUsage() {
		Runtime runtime = Runtime.getRuntime();
		return runtime.totalMemory() - runtime.freeMemory();
	}

	public static void main(String[] args) {
		Set<Integer> hashSet = new HashSet<>();
		Set<Integer> treeSet = new TreeSet<>();

		long startTime, endTime;
		long memoryBefore, memoryAfter;

		/**
		 * HashSet Vs TreeSet Insertion
		 */

		startTime = System.nanoTime();
		memoryBefore = getMemoryUsage();
		for (int i = 0; i < 100000; i++) {
			hashSet.add(i);
		}
		memoryAfter = getMemoryUsage();
		endTime = System.nanoTime();
		System.out.println("=======================");
		System.out.println("HashSet insertion time  :" + (endTime - startTime) + "ns");
		System.out.println("HashSet memory usage :" + (memoryAfter - memoryBefore) + "bytes");
		startTime = System.nanoTime();
		memoryBefore = getMemoryUsage();
		for (int i = 0; i < 100000; i++) {
			treeSet.add(i);
		}
		memoryAfter = getMemoryUsage();
		endTime = System.nanoTime();
		System.out.println("=======================");
		System.out.println("TreeSet insertion time :" + (endTime - startTime) + "ns");
		System.out.println("TreeSet memory usage :" + (memoryAfter - memoryBefore) + "bytes");

		/**
		 * HashSet Vs TreeSet Remove
		 */
		startTime = System.nanoTime();
		memoryBefore = getMemoryUsage();
		for (int i = 0; i < 100000; i++) {
			hashSet.remove(Integer.valueOf(i));
		}
		memoryAfter = getMemoryUsage();
		endTime = System.nanoTime();
		System.out.println("==========================");
		System.out.println("HashSet remove time :" + (endTime - startTime) + "ns");
		System.out.println("HashSet memory usage :" + (memoryAfter - memoryBefore) + "bytes");

		System.out.println("==============================");
		startTime = System.nanoTime();
		memoryBefore = getMemoryUsage();
		for (int i = 0; i < 100000; i++) {
			treeSet.remove(Integer.valueOf(i));
		}
		memoryAfter = getMemoryUsage();
		endTime = System.nanoTime();
		System.out.println("TreeSet remove time:" + (endTime - startTime) + "ns");
		System.out.println("TreeSet memory usage:" + (memoryAfter - memoryBefore) + "bytes");

	}

}
