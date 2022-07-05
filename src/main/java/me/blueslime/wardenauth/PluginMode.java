package me.blueslime.wardenauth;

public enum PluginMode {
    AUTOMATIC,
    MANUAL,
    STRICT;

    public static PluginMode fromString(String text) {
        switch (text.toLowerCase()) {
            default:
            case "automatic":
            case "auto":
            case "1":
                return PluginMode.AUTOMATIC;
            case "manual":
            case "2":
                return PluginMode.MANUAL;
            case "strict":
            case "3":
                return PluginMode.STRICT;
        }
    }
}
