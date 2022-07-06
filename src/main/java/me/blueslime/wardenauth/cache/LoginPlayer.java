package me.blueslime.wardenauth.cache;

public class LoginPlayer {

    private String name;

    private String captcha;

    private boolean autoLogin;

    public LoginPlayer(boolean autoLogin, String name, String uuid) {
        this.autoLogin = autoLogin;
        this.captcha   = uuid;
        this.name      = name;
    }

    public String getName() {
        return name;
    }

    public String getCaptcha() {
        return captcha;
    }

    public boolean isAutoLogin() {
        return autoLogin;
    }

    public void setAutoLogin(boolean status) {
        this.autoLogin = status;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }


}
