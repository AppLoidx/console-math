package util.printer;

/**
 * @author Arthur Kupriyanov on 03.03.2020
 */
public class NumberConverter {
    public <T> Number  convertTo(Class<T> clazz, String value){
        if (clazz.equals(Integer.class)){
            return Integer.valueOf(value);
        } else if (clazz.equals(Float.class)){
            return Float.valueOf(value);
        } else if (clazz.equals(Double.class)) {
            return Double.valueOf(value);
        } else {
            throw new IllegalArgumentException("Can't define class type");
        }
    }
}
