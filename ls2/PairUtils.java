package regular.ls2;

public class PairUtils {

    public static <T, U> String processPair(Pair<T, U> pair) {
        T first = pair.getFirst();
        U second = pair.getSecond();

        // Если оба элемента типа Integer, суммируем их
        if (first instanceof Integer && second instanceof Integer) {
            Integer sum = (Integer) first + (Integer) second;
            return "Sum: " + sum;
        }
        // Если оба элемента типа String, конкатенируем их
        else if (first instanceof String && second instanceof String) {
            String concatenation = (String) first + (String) second;
            return "Concatenation: " + concatenation;
        }
        // В противном случае возвращаем сообщение о несоответствии типов
        else {
            return "Incompatible types or unsupported operation.";
        }
    }
}