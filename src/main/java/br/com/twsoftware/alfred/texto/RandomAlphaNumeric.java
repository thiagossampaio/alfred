package br.com.twsoftware.alfred.texto;

import java.util.Random;

public class RandomAlphaNumeric {

    private static final Random random = new Random();

    //n�o faz sentido instanciar essa classe
    private RandomAlphaNumeric() {}

    //[0 - 9] =  0-9
    //[A - Z] = 10-35
    //[a - z] = 36-61
    private static char toChar(int index) {
        if (index < 10)
            return (char)(index + 48); //os chars 48 a 57 representam [0 - 9]
        else if (index < 36)
            return (char)(index + 55); //os chars 65 a 90 representam [A - Z]
        else
            return (char)(index + 61); //os chars 97 a 122 representam [a - z]
    }

    //char randomico entre [0 - 9], [A - Z] e [a - z]
    public static char randomChar() {
        return toChar(random.nextInt(62));
    }

    //String randomica de tamanho length, contendo apenas caracters alfanumericos
    public static String randomString(int length) {
        return new String(randomCharSequence(length));
    }

    //sequencia de caracters randomicos de tamanho length, contendo apenas alfanumericos
    public static char[] randomCharSequence(int length) {
        if (length < 0)
            throw new IllegalArgumentException("O tamanho da string n�o pode ser negativo");
        if (length == 0)
            return new char[0];
        
        char[] charSequence = new char[length];
        for (int i = 0; i<length; i++)
            charSequence[i] = randomChar();
        return charSequence;
    }

    //sequencia de caracters randomicos de tamanho entre minLength e maxLength, contendo apenas carcters alfanumericos
    public static String randomString(int minLength, int maxLength) {
        if (minLength < 0 || maxLength < 0)
            throw new IllegalArgumentException("O tamanho da string n�o pode ser negativo");
        if (minLength > maxLength)
            throw new IllegalArgumentException("O tamanho maximo da String n�o pode ser menor que o minimo");

        if (minLength == maxLength)
            return randomString(maxLength);
        int size = minLength + random.nextInt(maxLength-minLength+1);
        return randomString(size);
    }
}