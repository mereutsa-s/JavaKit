package regular.ls2;

public class Main {
    public static void main(String[] args) {
        Pair<Integer, Integer> intPair = new Pair<>(5, 10);
        System.out.println(PairUtils.combine(intPair)); // Ожидаем: Сумма: 15.0

        Pair<String, String> stringPair = new Pair<>("Hello", "World");
        System.out.println(PairUtils.combine(stringPair)); // Ожидаем: Конкатенация: Hello World

        Pair<Integer, String> mixedPair = new Pair<>(5, "Test");
        System.out.println(PairUtils.combine(mixedPair)); // Ожидаем: Типы несовместимы для операции.
    }
}