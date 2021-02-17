public abstract class Alphabet {

    public static final char[] rusAlphabet = {'А', 'Б', 'В', 'Г', 'Д', 'Е', 'Ё', 'Ж', 'З',
            'И', 'Й', 'К', 'Л', 'М', 'Н', 'О', 'П', 'Р', 'С', 'Т', 'У', 'Ф', 'Х', 'Ц', 'Ч',
            'Ш', 'Щ', 'Ъ', 'Ы', 'Ь', 'Э', 'Ю', 'Я'};

    public static final char[] engAlphabet = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I',
            'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    public static int indexOf(String lang, char c){
        c = Character.toUpperCase(c);
        if (lang.equals("rus")) {
            for (int i = 0; i < rusAlphabet.length; i++) {
                if (c == rusAlphabet[i]) return i;
            }
        } else if (lang.equals("eng")) {
            for (int i = 0; i < engAlphabet.length; i++) {
                if (c == engAlphabet[i]) return i;
            }
        } else {
            throw new IllegalArgumentException("Only \"eng\" and \"rus\" supported.");
        }
        return -1;
    }
}
