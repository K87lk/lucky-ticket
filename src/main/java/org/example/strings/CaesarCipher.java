package org.example.strings;
/*
* Задача "Шифр Цезаря"
Реализуйте метод для шифрования/дешифрования строки сдвигом букв
* на N позиций (с учетом сохранения регистра и игнорирования не-букв)
* */
public class CaesarCipher {
    public static void main(String[] args) {
        System.out.println(caesarCipher("Hello, World!", 3, true));
        System.out.println(caesarCipher("Khoor, Zruog!", 3, false));
    }
   public static String caesarCipher(String input, int shift, boolean encrypt) {
        StringBuilder result = new StringBuilder();
        for (char c : input.toCharArray()) {
            if (Character.isLetter(c)) {
                char base = Character.isLowerCase(c) ? 'a' : 'A';
                int originalPos = c - base;
                int newPos = encrypt ? (originalPos + shift) % 26 : (originalPos - shift + 26) % 26;
                result.append((char) (base + newPos));
            } else {
                result.append(c);
            }
        }
        return result.toString();
   }
}
