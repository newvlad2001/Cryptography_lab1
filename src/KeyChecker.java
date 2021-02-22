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
            case "num", "rus", "eng" -> this.mode = mode;
            default -> throw new IllegalArgumentException("Only \"num\", \"rus\" and \"eng\" supported.");
        }
    }

    public Errors checkKey(String key) {
        if (key.length() == 0) return Errors.ZERO_LEN;
        if (mode.equals("num")) {
            try {
                if (Integer.parseInt(key) == 0) {
                    return Errors.ZERO;
                }
            } catch (NumberFormatException e) {
                return Errors.NUMBER_OVERFLOW;
            }
        }
        return null;
    }

}

