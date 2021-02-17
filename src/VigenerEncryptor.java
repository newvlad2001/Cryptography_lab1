public class VigenerEncryptor implements Encryptor {
    // only eng or rus
    protected final char[] alphabet;
    protected final int alphabetLen;

    VigenerEncryptor(String lang) throws IllegalArgumentException {
        switch (lang) {
            case "rus" -> {
                alphabet = new char[]{'А', 'Б', 'В', 'Г', 'Д', 'Е', 'Ё', 'Ж', 'З', 'И', 'Й', 'К', 'Л',
                        'М', 'Н', 'О', 'П', 'Р', 'С', 'Т', 'У', 'Ф', 'Х', 'Ц', 'Ч', 'Ш', 'Щ', 'Ъ', 'Ы',
                        'Ь', 'Э', 'Ю', 'Я'};
                alphabetLen = alphabet.length;
            }
            case "eng" -> {
                alphabet = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
                        'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
                alphabetLen = alphabet.length;
            }
            default -> {
                throw new IllegalArgumentException("Only \"eng\" and \"rus\" supported.");
            }
        }
    }

    public String encrypt(String msg, String key) {
        String progressiveKey = createProgressiveKey(msg, key);
        StringBuilder cipherText = new StringBuilder();
        for (int i = 0; i < msg.length(); i++) {
            boolean isLowerCase = Character.isLowerCase(msg.charAt(i));
            char toAppend = alphabet[((indexOf(Character.toUpperCase(msg.charAt(i))) +
                    indexOf(Character.toUpperCase(progressiveKey.charAt(i)))) % alphabetLen)];
            if (isLowerCase) toAppend = Character.toLowerCase(toAppend);
            cipherText.append(toAppend);
        }
        return cipherText.toString();
    }

    public String decrypt(String msg, String key) {
        String progressiveKey = createProgressiveKey(msg, key);
        StringBuilder plainText = new StringBuilder();
        for (int i = 0; i < msg.length(); i++) {
            boolean isLowerCase = Character.isLowerCase(msg.charAt(i));
            char toAppend = alphabet[((alphabetLen + indexOf(Character.toUpperCase(msg.charAt(i))) -
                    indexOf(Character.toUpperCase(progressiveKey.charAt(i)))) % alphabetLen)];
            if (isLowerCase) toAppend = Character.toLowerCase(toAppend);
            plainText.append(toAppend);
        }
        return plainText.toString();
    }

    private String createProgressiveKey(String msg, String key) {
        StringBuilder progressiveKey = new StringBuilder(key);
        for (int i = 0; i < msg.length() - key.length(); i++) {
            boolean isLowerCase = Character.isLowerCase(progressiveKey.charAt(i));
            char toAppend = alphabet[((indexOf(progressiveKey.charAt(i)) + 1) % alphabetLen)];
            if(isLowerCase) toAppend = Character.toLowerCase(toAppend);
            progressiveKey.append(toAppend);
        }
        return progressiveKey.toString();
    }

    private int indexOf(char c){
        for (int i = 0; i < alphabet.length; i++) {
            if (c == alphabet[i]) return i;
        }
        return 0;
    }

}
