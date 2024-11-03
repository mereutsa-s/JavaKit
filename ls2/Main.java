package regular.ls2;

public class Main {
    public static void main(String[] args) {
        Pair<Integer, Integer> intPair = new Pair<>(5, 10);
        String intResult = PairUtils.processPair(intPair);
        System.out.println(intResult); // Вывод: Sum: 15

        Pair<String, String> stringPair = new Pair<>("Hello, ", "world!");
        String strResult = PairUtils.processPair(stringPair);
        System.out.println(strResult); // Вывод: Concatenation: Hello, world!

        Pair<Integer, String> mixedPair = new Pair<>(42, "Not Applicable");
        String mixedResult = PairUtils.processPair(mixedPair);
        System.out.println(mixedResult); // Вывод: Incompatible types or unsupported operation.
    }
}