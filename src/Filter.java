public class Filter {

    private String lang;

    Filter(String lang, boolean isSpaceIncluded) {
        setLang(lang);
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        if (lang.equals("rus") || lang.equals("eng")) {
            this.lang = lang;
        }
    }



    public String filterMsg(String msg, boolean isSpaceIncluded) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < msg.length(); i++) {
            if (isCorrectChar(msg.charAt(i), isSpaceIncluded)) {
                result.append(msg.charAt(i));
            }
        }
        return result.toString();
    }

    private boolean isCorrectChar(char c, boolean isSpaceIncluded) {
        boolean result = false;
        if (lang.equals("eng"))
            result = ((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z'));
        else if (lang.equals("rus"))
            result = (((c >= 'А' && c <= 'Я') || c == 'Ё') || ((c >= 'а' && c <= 'я') || c == 'ё'));
        if (isSpaceIncluded && !result)
            result = (c == ' ');
        return result;
    }
}
