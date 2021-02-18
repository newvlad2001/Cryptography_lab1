public class KeyChecker {

    private String mode;

    private final int MAX_BORDER = 13;
    private final int MIN_BORDER = 1;

    private int lowBorder = 1;
    private int topBorder = 13;

    public void setLowBorder(int lowBorder) {
        if (lowBorder < topBorder && lowBorder > MIN_BORDER && lowBorder < MAX_BORDER - 1)
            this.lowBorder = lowBorder;
    }

    public void setTopBorder(int topBorder) {
        if (topBorder > lowBorder && topBorder > MIN_BORDER + 1 && topBorder < MAX_BORDER)
            this.topBorder = topBorder;
    }

    public int getTopBorder() {
        return topBorder;
    }

    public int getLowBorder() {
        return lowBorder;
    }

    public KeyChecker(String mode) {
        setMode(mode);
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        switch (mode) {
            case "num":
            case "rus":
            case "eng":
                this.mode = mode;
                break;
            default:
                throw new IllegalArgumentException("Only \"num\", \"rus\" and \"eng\" supported.");
        }
    }

    public boolean checkKey(String key) {
        switch (mode) {
            case "eng":
            case "rus":
                if (key.length() == 0) return false;
                for (int i = 0; i < key.length(); i++) {
                    if (Alphabet.indexOf(mode, key.charAt(i)) == -1)
                        return false;
                }
                return true;
            case "num":
                try {
                    return (Integer.parseInt(key) > 1);
                } catch (NumberFormatException e) {
                    return false;
                }
        }
        return false;
    }

}

