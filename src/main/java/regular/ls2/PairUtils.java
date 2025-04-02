package regular.ls2;

public class PairUtils {
    public static <T1, T2> String combine(Pair<T1, T2> pair) {
        String result = "";
        if (pair.getFirst() instanceof Number && pair.getSecond() instanceof Number) {
            // Если два элемента числа, возвращаем их сумму как строку
            double sum = ((Number) pair.getFirst()).doubleValue() + ((Number) pair.getSecond()).doubleValue();
            result = "Сумма: " + sum;
        } else if (pair.getFirst() instanceof String && pair.getSecond() instanceof String) {
            // Если два элемента строки, возвращаем их конкатенацию
            result = "Конкатенация: " + pair.getFirst() + " " + pair.getSecond();
        } else {
            result = "Типы несовместимы для операции.";
        }
        return result;
    }
}