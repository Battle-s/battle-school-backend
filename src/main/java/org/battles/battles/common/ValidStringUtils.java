package org.battles.battles.common;

import java.util.regex.Pattern;
import org.battles.battles.exception.exception.CNotValidEmailException;

public class ValidStringUtils {

    public static final Pattern EMAIL = Pattern.compile(
        "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$");

    public static String getValidEmail(String email) {
        if (!EMAIL.matcher(email).matches()) {
            throw new CNotValidEmailException();
        }
        return email;
    }

}
