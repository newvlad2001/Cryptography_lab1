public class KeyChecker {

    private String mode;

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
                for (int i = 0; i < key.length(); i++) {
                    if (Alphabet.indexOf(mode, key.charAt(i)) == -1)
                        return false;
                }
                return true;
            case "num":
                try {
                    return(Integer.parseInt(key) > 1);
                } catch (NumberFormatException e) {
                    return false;
                }
        }
        return false;
    }

}

