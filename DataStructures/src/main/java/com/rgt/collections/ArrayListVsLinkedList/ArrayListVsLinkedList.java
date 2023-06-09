package com.rgt.collections.ArrayListVsLinkedList;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

class ArrayListVsLinkedList {

	private static long getMemoryUsage() {
		Runtime runtime = Runtime.getRuntime();
		return runtime.totalMemory() - runtime.freeMemory();

	}

	public static void main(String[] args) {
		/**  * ArrayList Insertion ,Deletion Time and Space Complexity  */
		List<Integer> arrayList = new ArrayList<Integer>();

		long startTime, endTime;
		long memoryBefore, memoryAfter;
		startTime = System.nanoTime();
		memoryBefore = getMemoryUsage();
		for (int i = 0; i < 1000000; i++) {
			arrayList.add(i);
		}
		memoryAfter = getMemoryUsage();
		long spaceComplexityInsertion = memoryAfter - memoryBefore;
		endTime = System.nanoTime();
		long arrayListTimeComplexityInsertion = endTime - startTime;
		arrayList.remove(90000);
		memoryAfter = getMemoryUsage();
		long spaceComplexityDeletion = memoryAfter - memoryBefore;
		endTime = System.nanoTime();
		long arrayListTimeComplexityDeletion = endTime - startTime;
		System.out.println("----------------------------------------------");
		System.out.println("********ARRAY LIST***********");
		System.out.println("ArrayList Time Complexity for Insertion :" + arrayListTimeComplexityInsertion + "ns");
		System.out.println("ArrayList Space Complexity for Insertion :" + spaceComplexityInsertion + "bytes");
		System.out.println("ArrayList Time Complexity for Deletion :" + arrayListTimeComplexityDeletion + "ns");
		System.out.println("ArrayList Space Complexity for Deletion :" + spaceComplexityDeletion + "bytes");
		System.out.println("----------------------------------------------");

		/**  * For Linked List Insertion ,Deletion Time and Space Complexity  */
		List<Integer> linkedList = new LinkedList<>();
		for (int i = 0; i < 100000; i++) {
			linkedList.add(i);
		}
		memoryAfter = getMemoryUsage();
		long spaceComplexityLinkedListInsertion = memoryAfter - memoryBefore;
		endTime = System.nanoTime();
		long linkedListTimeComplexityInsertion = endTime - startTime;
		linkedList.remove(1000);
		memoryAfter = getMemoryUsage();
		long spaceComplexityLinkedListDeletion = memoryAfter - memoryBefore;
		endTime = System.nanoTime();
		long arrayListTimeComplexityLinkedListDeletion = endTime - startTime;
		System.out.println("----------------------------------------------");
		System.out.println("********LINKED LIST***********");
		System.out.println("LinkedList Time Complexity for Insertion :" + linkedListTimeComplexityInsertion + "ns");
		System.out
				.println("LinkedList Space Complexity for Insertion :" + spaceComplexityLinkedListInsertion + "bytes");
		System.out.println(
				"LinkedList Time Complexity for Deletion :" + arrayListTimeComplexityLinkedListDeletion + "ns");
		System.out.println("LinkedList Space Complexity for Deletion :" + spaceComplexityLinkedListDeletion + "bytes");

	}
}