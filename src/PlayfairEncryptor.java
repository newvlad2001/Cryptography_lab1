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
            toEncrypt.replace(i, i + 2, String.valueOf(toReplaceF) + toReplaceS);
        }
        return toEncrypt.toString();
    }

    private StringBuilder prepareString(String msg) {
        StringBuilder resString = new StringBuilder(msg.toUpperCase());
        int i = 0;
        while (i < resString.length() - 1) {
            if (resString.charAt(i) == resString.charAt(i + 1)) {
                resString.insert(i + 1, 'X');
                i++;
            }
            i++;
        }
        if (resString.length() % 2 != 0)
            resString.append('X');
        return resString;
    }

    @Override
    public String decrypt(String msg, String key) {
        return encrypt(msg, key);
    }

    private int[] getPosition(char c) {
        if (c == 'J') return getPosition('I');
        for (int i = 0; i < cipherTable.length; i++) {
            for (int j = 0; j < cipherTable[i].length; j++) {
                if (cipherTable[i][j] == c)
                    return new int[]{i, j};
            }
        }
        return new int[]{0, 0};
    }
}
