public class RailwayEncryptor implements Encryptor {
    // any language
    public String encrypt(String toEncrypt, String keySrc) {
        StringBuilder cipherText = new StringBuilder();
        int key = Integer.parseInt(keySrc);
        if (key == 1) return toEncrypt;
        for (int lvl = 0; lvl < key; lvl++) {
            int j = lvl;
            boolean flag = false;
            while (j < toEncrypt.length()) {
                cipherText.append(toEncrypt.charAt(j));
                j += getOffset(lvl, flag, key);
                flag = !flag;
            }
        }
        return cipherText.toString();
        //return cipherText.toString().toUpperCase();
    }

    public String decrypt(String toDecrypt, String keySrc) {
        int key = Integer.parseInt(keySrc);
        if (key == 1) return toDecrypt;
        StringBuilder plainText = new StringBuilder();
        StringBuilder[] arr = new StringBuilder[key];
        int index = 0;
        for (int lvl = 0; lvl < key; lvl++) {
            int j = lvl;
            boolean flag = false;
            int counter = 0;
            while (j < toDecrypt.length()) {
                counter++;
                j += getOffset(lvl, flag, key);
                flag = !flag;
            }
            arr[lvl] = new StringBuilder(toDecrypt.substring(index, index + counter));
            index += counter;
        }

        boolean isEnd = false;
        while (!isEnd) {
            isEnd = true;
            for (int j = 0; j < key - 1; j++) {
                if (arr[j].length() != 0) {
                    plainText.append(arr[j].charAt(0));
                    arr[j].deleteCharAt(0);
                    isEnd = false;
                }
            }
            for (int j = key - 1; j > 0; j--) {
                if (arr[j].length() != 0) {
                    plainText.append(arr[j].charAt(0));
                    arr[j].deleteCharAt(0);
                    isEnd = false;
                }
            }
        }
        return plainText.toString();
        //return plainText.toString().toUpperCase();
    }

    private int getOffset(int lvl, boolean flag, int key) {
        if (flag && (lvl != 0) && (lvl != key - 1)) {
            return lvl * 2;
        }
        if (lvl == key - 1) {
            lvl = 0;
        }
        return (key - lvl - 1) * 2;
    }
}
