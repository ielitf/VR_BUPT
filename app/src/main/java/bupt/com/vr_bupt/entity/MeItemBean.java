package bupt.com.vr_bupt.entity;

public class MeItemBean {
    private int icon;
    private String title;

    public MeItemBean(int icon, String title) {
        this.icon = icon;
        this.title = title;
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

    @Override
    public String toString() {
        return "MeItemBean{" +
                "icon=" + icon +
                ", title='" + title + '\'' +
                '}';
    }
}
