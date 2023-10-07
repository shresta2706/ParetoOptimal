import Shresta.Point;

import java.util.*;


public class StaircaseProblem {

    // Recursive Function for Pareto optimal problem using Divide and Conquer
    public static List<Point> funStaircase(List<Point> points) {
        int n = points.size();
        if (n <= 1) {
            return points;
        }

        // Divide the array into halves and solve recursively on each half
        int mid = n / 2;
        List<Point> leftside = new ArrayList<>(points.subList(0, mid));
        List<Point> rightside = new ArrayList<>(points.subList(mid, n));

        // Recursively compute staircases for the left and right halves using Divide and Conquer
        List<Point> leftStair = funStaircase(leftside);
        List<Point> rightStair = funStaircase(rightside);

        // Calling Merge function to return pareto optimal from the both halves
        return mergeStaircases(leftStair, rightStair);
    }


    // Function for Merging two sub halves
    public static List<Point> mergeStaircases(List<Point> leftStair, List<Point> rightStair) {
        List<Point> mergedStaircase = new ArrayList<>();
        int i = 0, j = 0;
        int maxYaxisLeft = Integer.MIN_VALUE;
        int maxYaxisRight = Integer.MIN_VALUE;

        while (i < leftStair.size() && j < rightStair.size()) {
            Point leftPoint = leftStair.get(i);
            Point rightPoint = rightStair.get(j);

            if (leftPoint.getX() <= rightPoint.getX()) {
                if (leftPoint.getY() > maxYaxisRight) {
                    mergedStaircase.add(leftPoint);
                    maxYaxisLeft = Math.max(maxYaxisLeft, leftPoint.getY());
                }
                i++;
            } else {
                if (rightPoint.getY() > maxYaxisLeft) {
                    mergedStaircase.add(rightPoint);
                    maxYaxisRight = Math.max(maxYaxisRight, rightPoint.getY());
                }
                j++;
            }
        }

        while (i < leftStair.size()) {
            Point leftpareto = leftStair.get(i);
            if (leftpareto.getY() > maxYaxisRight) {
                mergedStaircase.add(leftpareto);
                maxYaxisLeft = Math.max(maxYaxisLeft, leftpareto.getY());
            }
            i++;
        }

        while (j < rightStair.size()) {
            Point rightpareto = rightStair.get(j);
            if (rightpareto.getY() > maxYaxisLeft) {
                mergedStaircase.add(rightpareto);
                maxYaxisRight = Math.max(maxYaxisRight, rightpareto.getY());
            }
            j++;
        }

        return mergedStaircase;
    }


    // Main function

    public static void main(String[] args) {
        List<Point> points = new ArrayList<>();
        int num = 0;
        Scanner s = new Scanner(System.in);
        num = s.nextInt();
        Random random = new Random();
        for (int i = 0; i < num; i++) {
            points.add(new Point(random.nextInt(num),random.nextInt(num)));
        }
//        for (int i = 0; i < 26; i++) {
//            points.add(i % 2, i % 4);
//        }
//
//        Collections.shuffle(points);
//
//        points.add(new Point(1, 3));
//        points.add(new Point(2, 2));
//        points.add(new Point(3, 1));
//        points.add(new Point(4, 4));
//        points.add(new Point(5, 2));
//        points.add(new Point(5, 4));
//        points.add(new Point(3, 2));
//        points.add(new Point(6, 8));


        long start = System.nanoTime();

        List<Point> staircase = funStaircase(points);



        for (Point point : staircase) {
            System.out.println("(" + point.getX() + ", " + point.getY() + ")");
        }
        long finish = System.nanoTime();
        long timeElapsed = finish - start;
        System.out.println(timeElapsed);
    }
}