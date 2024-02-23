import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        try {
            File file = new File("input.txt");
            Scanner scanner = new Scanner(file);
            List<Integer> numbers = new ArrayList<>();
            while (scanner.hasNextInt()) {
                numbers.add(scanner.nextInt());
            }
            scanner.close();

            // decr.seq., incr.seq., aver.
            List<Integer> maxIncrSec = new ArrayList<>();
            List<Integer> currIncrSec = new ArrayList<>();
            List<Integer> maxDecrSec = new ArrayList<>();
            List<Integer> currDecrSec = new ArrayList<>();
            ;
            long sum = numbers.get(0);
            for (int i = 1; i < numbers.size(); i++)  {
                sum += numbers.get(i);
                if (numbers.get(i) > numbers.get(i - 1)) {
                    if(currDecrSec.size() > maxDecrSec.size()) {
                        maxDecrSec = new ArrayList<>(currDecrSec);
                    }
                    currDecrSec.clear();
                    if(currIncrSec.isEmpty()) {
                        currIncrSec.add(numbers.get(i-1));
                        currIncrSec.add(numbers.get(i));
                    } else {
                        currIncrSec.add(numbers.get(i));
                    }
                } else if (numbers.get(i) < numbers.get(i - 1)) {
                    if(currIncrSec.size() > maxIncrSec.size()) {
                        maxIncrSec = new ArrayList<>(currIncrSec);
                    }
                    currIncrSec.clear();
                    if(currDecrSec.isEmpty()) {
                        currDecrSec.add(numbers.get(i-1));
                        currDecrSec.add(numbers.get(i));
                    } else {
                        currIncrSec.add(numbers.get(i));
                    }
                } else {
                    if(currDecrSec.size() > maxDecrSec.size()) {
                        maxDecrSec = new ArrayList<>(currDecrSec);
                    }
                    currDecrSec.clear();
                    if(currIncrSec.size() > maxIncrSec.size()) {
                        maxIncrSec = new ArrayList<>(currIncrSec);
                    }
                    currIncrSec.clear();
                }
            }
            System.out.println("Перша найбільша зростаюча послідовність чисел:" + maxIncrSec.toString());
            System.out.println("Перша найбільша спадаюча послідовність чисел:" + maxDecrSec.toString());
            System.out.println("Середнє арифметичне значення:" + sum / numbers.size());
            // min, max, median
            List<Integer> sortedNumbers = numbers.parallelStream()
                    .sorted()
                    .toList();

            Integer minInt = sortedNumbers.get(0);
            System.out.println("Мінімальне значення: " + minInt);
            Integer maxInt = sortedNumbers.get(sortedNumbers.size() - 1);
            System.out.println("Максимальне значення: " + maxInt);
            double median = (sortedNumbers.size() / 2 != 0 ? sortedNumbers.get(sortedNumbers.size() / 2) :
                                 (double) (sortedNumbers.get(sortedNumbers.size() / 2) + sortedNumbers.get(sortedNumbers.size() / 2 - 1)) / 2);
            System.out.println("Медіана: " + median);

            long endTime = System.currentTimeMillis();
            long executionTime = endTime - startTime;
            System.out.println("Час виконання програми: " + executionTime / 1000 + " секунд "
                    + executionTime % 1000 + " мілісекунд");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}