public class VigenerEncryptor implements Encryptor {
    // only eng or rus
    private final char[] alphabet;
    private final int alphabetLen;
    private final String lang;

    VigenerEncryptor(String lang) throws IllegalArgumentException {
        switch (lang) {
            case "rus" -> {
                this.lang = lang;
                alphabet = Alphabet.rusAlphabet;
                alphabetLen = alphabet.length;
            }
            case "eng" -> {
                this.lang = lang;
                alphabet = Alphabet.engAlphabet;
                alphabetLen = alphabet.length;
            }
            default -> throw new IllegalArgumentException("Only \"eng\" and \"rus\" supported.");
        }
    }

    public String encrypt(String msg, String key) {
        String progressiveKey = createProgressiveKey(msg, key);
        StringBuilder cipherText = new StringBuilder();
        for (int i = 0; i < msg.length(); i++) {
            boolean isLowerCase = Character.isLowerCase(msg.charAt(i));
            char toAppend = alphabet[((Alphabet.indexOf(lang, Character.toUpperCase(msg.charAt(i))) +
                    Alphabet.indexOf(lang, Character.toUpperCase(progressiveKey.charAt(i)))) % alphabetLen)];
            if (isLowerCase) toAppend = Character.toLowerCase(toAppend);
            cipherText.append(toAppend);
        }
        return cipherText.toString();
        //return cipherText.toString().toUpperCase();
    }

    public String decrypt(String msg, String key) {
        String progressiveKey = createProgressiveKey(msg, key);
        StringBuilder plainText = new StringBuilder();
        for (int i = 0; i < msg.length(); i++) {
            boolean isLowerCase = Character.isLowerCase(msg.charAt(i));
            char toAppend = alphabet[((alphabetLen + Alphabet.indexOf(lang, Character.toUpperCase(msg.charAt(i))) -
                    Alphabet.indexOf(lang, Character.toUpperCase(progressiveKey.charAt(i)))) % alphabetLen)];
            if (isLowerCase) toAppend = Character.toLowerCase(toAppend);
            plainText.append(toAppend);
        }

        return plainText.toString();
        //return plainText.toString().toUpperCase();
    }

    private String createProgressiveKey(String msg, String key) {
        StringBuilder progressiveKey = new StringBuilder(key);
        for (int i = 0; i < msg.length() - key.length(); i++) {
            boolean isLowerCase = Character.isLowerCase(progressiveKey.charAt(i));
            char toAppend = alphabet[((Alphabet.indexOf(lang, progressiveKey.charAt(i)) + 1) % alphabetLen)];
            if (isLowerCase) toAppend = Character.toLowerCase(toAppend);
            progressiveKey.append(toAppend);
        }
        return progressiveKey.toString();
    }


}
