public class PlayfairEncryptor implements Encryptor {
    // only eng
    private final char[][] cipherTable = {
            {'C', 'R', 'Y', 'P', 'T'},
            {'O', 'G', 'A', 'H', 'B'},
            {'D', 'E', 'F', 'I', 'K'},
            {'L', 'M', 'N', 'Q', 'S'},
            {'U', 'V', 'W', 'X', 'Z'}
    };

    public String encrypt(String msg, String keySrc) {
        StringBuilder toEncrypt = prepareString(msg);
        for (int i = 0; i < toEncrypt.length() - 1; i += 2) {
            int[] fCharPos = getPosition(toEncrypt.charAt(i));
            int[] sCharPos = getPosition(toEncrypt.charAt(i + 1));
            boolean isLowerCaseF = Character.isLowerCase(toEncrypt.charAt(i));
            boolean isLowerCaseS = Character.isLowerCase(toEncrypt.charAt(i + 1));
            char toReplaceF = 0;
            char toReplaceS = 0;
            if (fCharPos[0] == sCharPos[0]) {
                toReplaceF = cipherTable[fCharPos[0]][(fCharPos[1] + 1) % cipherTable.length];
                toReplaceS = cipherTable[sCharPos[0]][(sCharPos[1] + 1) % cipherTable.length];
            } else if (fCharPos[1] == sCharPos[1]) {
                toReplaceF = cipherTable[(fCharPos[0] + 1) % cipherTable.length][fCharPos[1]];
                toReplaceS = cipherTable[(sCharPos[0] + 1) % cipherTable.length][sCharPos[1]];
            } else {
                toReplaceF = cipherTable[fCharPos[0]][sCharPos[1]];
                toReplaceS = cipherTable[sCharPos[0]][fCharPos[1]];
            }
            if (isLowerCaseF) {
                toReplaceF = Character.toLowerCase(toReplaceF);
            }
            if (isLowerCaseS) {
                toReplaceS = Character.toLowerCase(toReplaceS);
            }
            toEncrypt.replace(i, i + 2, String.valueOf(toReplaceF) + toReplaceS);
        }
        return toEncrypt.toString();
        //return toEncrypt.toString().toUpperCase();
    }

    private StringBuilder prepareString(String msg) {
        StringBuilder resString = new StringBuilder(msg);
        for (int j = 0; j < resString.length(); j++) {
            if (resString.charAt(j) == 'J') {
                resString.setCharAt(j, 'I');
            } else if (resString.charAt(j) == 'j') {
                resString.setCharAt(j, 'i');
            }
        }
        int i = 0;
        while (i < resString.length() - 1) {
            if (Character.toUpperCase(resString.charAt(i)) == Character.toUpperCase(resString.charAt(i + 1))) {
                if (Character.toUpperCase(resString.charAt(i)) == 'X')
                    resString.insert(i + 1, 'Q');
                else
                    resString.insert(i + 1, 'X');
            }
            i += 2;
        }
        if (resString.length() % 2 != 0)
            resString.append('X');
        System.out.println(resString.toString());
        return resString;
    }

    @Override
    public String decrypt(String msg, String key) {
        StringBuilder toDecrypt;

        toDecrypt = new StringBuilder(msg);
        for (int i = 0; i < toDecrypt.length() - 1; i += 2) {
            int[] fCharPos = getPosition(toDecrypt.charAt(i));
            int[] sCharPos = getPosition(toDecrypt.charAt(i + 1));
            boolean isLowerCaseF = Character.isLowerCase(toDecrypt.charAt(i));
            boolean isLowerCaseS = Character.isLowerCase(toDecrypt.charAt(i + 1));
            char toReplaceF = 0;
            char toReplaceS = 0;
            if (fCharPos[0] == sCharPos[0]) {
                toReplaceF = cipherTable[fCharPos[0]][(cipherTable.length + fCharPos[1] - 1) % cipherTable.length];
                toReplaceS = cipherTable[sCharPos[0]][(cipherTable.length + sCharPos[1] - 1) % cipherTable.length];
            } else if (fCharPos[1] == sCharPos[1]) {
                toReplaceF = cipherTable[(cipherTable.length + fCharPos[0] - 1) % cipherTable.length][fCharPos[1]];
                toReplaceS = cipherTable[(cipherTable.length + sCharPos[0] - 1) % cipherTable.length][sCharPos[1]];
            } else {
                toReplaceF = cipherTable[fCharPos[0]][sCharPos[1]];
                toReplaceS = cipherTable[sCharPos[0]][fCharPos[1]];
            }
            if (isLowerCaseF) {
                toReplaceF = Character.toLowerCase(toReplaceF);
            }
            if (isLowerCaseS) {
                toReplaceS = Character.toLowerCase(toReplaceS);
            }
            toDecrypt.replace(i, i + 2, String.valueOf(toReplaceF) + toReplaceS);
        }
        return toDecrypt.toString();
        //return toDecrypt.toString().toUpperCase();
    }

    private int[] getPosition(char c) {
        c = Character.toUpperCase(c);
        for (int i = 0; i < cipherTable.length; i++) {
            for (int j = 0; j < cipherTable[i].length; j++) {
                if (cipherTable[i][j] == c)
                    return new int[]{i, j};
            }
        }
        return new int[]{0, 0};
    }
}
