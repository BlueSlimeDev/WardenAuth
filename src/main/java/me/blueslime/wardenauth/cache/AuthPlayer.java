package me.blueslime.wardenauth.cache;

public class AuthPlayer {

    private String name;

    private String uuid;

    public AuthPlayer(String name, String uuid) {
        this.name = name;
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public String getUniqueId() {
        return uuid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUniqueId(String uuid) {
        this.uuid = uuid;
    }

}
