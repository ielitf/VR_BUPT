package bupt.com.vr_bupt.bean;


public class CommonBean {
    private int icon;
    private String title;
    private String url;


    public CommonBean(int icon, String title,String url) {
        this.icon = icon;
        this.title = title;
        this.url = url;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "CommonBean{" +
                "icon=" + icon +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
