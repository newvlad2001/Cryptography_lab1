public class Filter {

    private String lang;

    Filter(String lang) {
        setLang(lang);
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        if (lang.equals("rus") || lang.equals("eng") || lang.equals("num")) {
            this.lang = lang;
        } else {
            throw new IllegalArgumentException("only \"rus\", \"eng\" and \"num\" supported.");
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
        boolean result = switch (lang) {
            case "eng" -> ((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z'));
            case "rus" -> (((c >= 'А' && c <= 'Я') || c == 'Ё') || ((c >= 'а' && c <= 'я') || c == 'ё'));
            case "num" -> (c >= '0' && c <= '9');
            default -> false;
        };
        if (isSpaceIncluded && !result)
            result = (c == ' ');
        return result;
    }
}
