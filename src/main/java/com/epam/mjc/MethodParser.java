package com.epam.mjc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MethodParser {

    /**
     * Parses string that represents a method signature and stores all it's members into a {@link MethodSignature} object.
     * signatureString is a java-like method signature with following parts:
     *      1. access modifier - optional, followed by space: ' '
     *      2. return type - followed by space: ' '
     *      3. method name
     *      4. arguments - surrounded with braces: '()' and separated by commas: ','
     * Each argument consists of argument type and argument name, separated by space: ' '.
     * Examples:
     *      accessModifier returnType methodName(argumentType1 argumentName1, argumentType2 argumentName2)
     *      private void log(String value)
     *      Vector3 distort(int x, int y, int z, float magnitude)
     *      public DateTime getCurrentDateTime()
     *
     * @param signatureString source string to parse
     * @return {@link MethodSignature} object filled with parsed values from source string
     */
    public MethodSignature parseFunction(String signatureString) {

        StringSplitter splitter = new StringSplitter();
        List<String> splitString = splitter.splitByDelimiters(signatureString, new ArrayList<>(Arrays.asList(" ", "(", ")", ",")));

        boolean flag = splitString.get(0).equals("public") || splitString.get(0).equals("private") || splitString.get(0).equals("protected");

        MethodSignature signature;
        if (flag) {
            signature = new MethodSignature(splitString.get(2));
            signature.setAccessModifier(splitString.get(0));
            signature.setReturnType(splitString.get(1));
        } else {
            signature = new MethodSignature(splitString.get(1));
            signature.setReturnType(splitString.get(0));
            signature.setAccessModifier(null);
        }

        return signature;
    }
}
