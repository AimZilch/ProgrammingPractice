package com.aimzilch;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;

public class VoteTallyingProgram {
    public static void main(String[] args) {
        File inputFile = new File("src/main/resources/votes.txt");

        // Initialize vote counts for all candidates
        HashMap<String, Integer> voteCounts = new HashMap<String, Integer>();

        // Initialize number of times each voter has voted
        HashMap<String, Integer> voterCounts = new HashMap<String, Integer>();

        // Initialize max heap to store top candidates
        PriorityQueue<Map.Entry<String, Integer>> maxHeap = new PriorityQueue<>(new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> a, Map.Entry<String, Integer> b) {
                return b.getValue() - a.getValue();
            }
        });

        // Read input file line by line
        try {
            Scanner inputScanner = new Scanner(inputFile);
            while (inputScanner.hasNextLine()) {
                String line = inputScanner.nextLine();
                String[] parts = line.split(",");

                // Check for voter fraud
                if (voterCounts.containsKey(parts[0])) {
                    System.out.println("Voter fraud detected: " + parts[0]);
                } else {
                    voterCounts.put(parts[0], 1);
                }

                // Update vote counts for candidate
                String candidate = parts[1];
                voteCounts.put(candidate, voteCounts.getOrDefault(candidate, 0) + 1);

                // Update max heap
                maxHeap.clear();
                maxHeap.addAll(voteCounts.entrySet());

                // Print top 3 candidates
                System.out.println("Top 3 candidates:");
                int count = 1;
                while (!maxHeap.isEmpty() && count <= 3) {
                    Map.Entry<String, Integer> entry = maxHeap.poll();
                    System.out.println(count + ". " + entry.getKey() + " - " + entry.getValue() + " votes");
                    count++;
                }
                System.out.println();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
