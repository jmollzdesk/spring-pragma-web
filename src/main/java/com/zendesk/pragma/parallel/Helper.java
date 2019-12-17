package com.zendesk.pragma.parallel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Helper {
    private static final char[] arr = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j'};
    private static final char[] targetArr = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j'};
    private static int seedNum = 10;
    private static int random = 50;

    public static char[] getTargetArr() {
        return targetArr;
    }

    public static String getRandomString() {
        Random rand = new Random();
        for (int x = 0; x < random; x++) {
            int randomIndex = rand.nextInt(seedNum);
            swap(0, randomIndex);
        }
        return new String(arr);
    }

    public static void swap(int x, int y) {
        char temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }

    public static List<String> getLargeList(long size) {
        List<String> arrayList = new ArrayList<>();
        for (long x = 0; x < size; x++) {
            arrayList.add(getRandomString());
        }
        return arrayList;
    }
}
