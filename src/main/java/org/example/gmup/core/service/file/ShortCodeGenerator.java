package org.example.gmup.core.service.file;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.aventrix.jnanoid.jnanoid.NanoIdUtils;

@Getter
@Setter
public class ShortCodeGenerator {
    private static char[] alphabet = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();


    public static String getShortCode() {
        return NanoIdUtils.randomNanoId(NanoIdUtils.DEFAULT_NUMBER_GENERATOR, alphabet, 7);
    }

}
