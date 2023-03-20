package test.example.leader_it.util;

import org.springframework.validation.BindingResult;

public class BindingResultFiller {

    public static String fillBindingResult(BindingResult bindingResult) {
        StringBuilder stringBuilder = new StringBuilder();
        bindingResult.getFieldErrors().forEach(e -> stringBuilder.append(e.getDefaultMessage()).append("; "));
        return stringBuilder.toString();
    }
}
