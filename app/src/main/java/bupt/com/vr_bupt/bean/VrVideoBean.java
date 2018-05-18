package bupt.com.vr_bupt.bean;

/**
 * Created by litf on 2018/5/15.
 */

public class VrVideoBean {
    private int vrVideoPicture;
    private String vrVideoName;
    private int vrAtenTionHeadIcon;
    private String vrAtenTionName;
    private String vrScanNum;
    private String vrVideoUrl;

    public VrVideoBean(int vrVideoPicture, String vrVideoName) {
        this.vrVideoPicture = vrVideoPicture;
        this.vrVideoName = vrVideoName;
    }
    public VrVideoBean(int vrVideoPicture, String vrVideoName, String vrVideoUrl) {
        this.vrVideoPicture = vrVideoPicture;
        this.vrVideoName = vrVideoName;
        this.vrVideoUrl = vrVideoUrl;
    }
    public VrVideoBean(int vrVideoPicture, String vrVideoName, String vrVideoUrl, int vrAtenTionHeadIcon, String vrAtenTionName) {
        this.vrVideoPicture = vrVideoPicture;
        this.vrVideoName = vrVideoName;
        this.vrVideoUrl = vrVideoUrl;
        this.vrAtenTionHeadIcon = vrAtenTionHeadIcon;
        this.vrAtenTionName = vrAtenTionName;
    }
    public VrVideoBean(int vrVideoPicture, String vrVideoName, String vrVideoUrl, int vrAtenTionHeadIcon, String vrAtenTionName, String vrScanNum) {
        this.vrVideoPicture = vrVideoPicture;
        this.vrVideoName = vrVideoName;
        this.vrVideoUrl = vrVideoUrl;
        this.vrAtenTionHeadIcon = vrAtenTionHeadIcon;
        this.vrAtenTionName = vrAtenTionName;
        this.vrScanNum = vrScanNum;
    }

    public int getVrVideoPicture() {
        return vrVideoPicture;
    }

    public void setVrVideoPicture(int vrVideoPicture) {
        this.vrVideoPicture = vrVideoPicture;
    }

    public String getVrVideoName() {
        return vrVideoName;
    }

    public void setVrVideoName(String vrVideoName) {
        this.vrVideoName = vrVideoName;
    }

    public int getVrAtenTionHeadIcon() {
        return vrAtenTionHeadIcon;
    }

    public void setVrAtenTionHeadIcon(int vrAtenTionHeadIcon) {
        this.vrAtenTionHeadIcon = vrAtenTionHeadIcon;
    }

    public String getVrAtenTionName() {
        return vrAtenTionName;
    }

    public void setVrAtenTionName(String vrAtenTionName) {
        this.vrAtenTionName = vrAtenTionName;
    }

    public String getVrScanNum() {
        return vrScanNum;
    }

    public void setVrScanNum(String vrScanNum) {
        this.vrScanNum = vrScanNum;
    }

    public String getVrVideoUrl() {
        return vrVideoUrl;
    }

    public void setVrVideoUrl(String vrVideoUrl) {
        this.vrVideoUrl = vrVideoUrl;
    }

    @Override
    public String toString() {
        return "VrVideoBean{" +
                "vrVideoPicture=" + vrVideoPicture +
                ", vrVideoName='" + vrVideoName + '\'' +
                ", vrAtenTionHeadIcon=" + vrAtenTionHeadIcon +
                ", vrAtenTionName='" + vrAtenTionName + '\'' +
                ", vrScanNum='" + vrScanNum + '\'' +
                ", vrVideoUrl='" + vrVideoUrl + '\'' +
                '}';
    }
}
