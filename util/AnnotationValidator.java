package util;

import java.lang.reflect.Field;

import model.annotation.MaxLength;
import model.annotation.Positive;

public class AnnotationValidator {

    public static String[] validate(Object obj) {

        java.util.ArrayList<String> errors = new java.util.ArrayList<String>();

        Field[] fields = obj.getClass().getDeclaredFields();

        for (Field field : fields) {

            if (field.isAnnotationPresent(Positive.class)) {

                field.setAccessible(true);

                try {

                    Object value = field.get(obj);

                    if (value instanceof Number) {

                        long num = ((Number) value).longValue();

                        if (num <= 0) {

                            Positive p = field.getAnnotation(Positive.class);
                            errors.add(p.message());
                        }
                    }

                } catch (Exception e) {

                }
            }

            if (field.isAnnotationPresent(MaxLength.class)) {

                field.setAccessible(true);

                try {

                    Object value = field.get(obj);

                    if (value instanceof String) {

                        String s = (String) value;
                        MaxLength m = field.getAnnotation(MaxLength.class);

                        if (s.length() > m.value()) {

                            errors.add("exceeds max length");
                        }
                    }

                } catch (Exception e) {

                }
            }
        }

        return errors.toArray(new String[0]);
    }
}
