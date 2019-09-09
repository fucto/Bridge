package bridge.web;

import bridge.ejb.CollegeInfoBean;
import bridge.entity.College;

import bridge.entity.CollegeShow;
import bridge.entity.User;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.validation.constraints.NotNull;

@Named
@SessionScoped
@Stateless
public class CollegeSearch implements Serializable {

    @EJB
    private CollegeInfoBean collegeInfoBean;
    protected List<CollegeShow> CInfo = new ArrayList();

    public List<CollegeShow> getCInfo() {
        return CInfo;
    }

    public void setCInfo(List<CollegeShow> CInfo) {
        this.CInfo = CInfo;
    }
    private static final Logger logger = Logger.getLogger("bridge.web.CollegeInfo");

    // required properties
    @NotNull
    protected int id;
    @NotNull
    protected String name_ch;
    @NotNull
    protected String name_en;
    @NotNull
    protected String country;
    @NotNull
    protected String pic;
    @NotNull
    protected String info;
    @NotNull
    protected String site;

    // optional properties
    protected int tofel;
    protected int gre;
    protected int fee;
    protected double gpa;

    protected List<User> stars;

    public String scearch_process(String keyword) {
        this.CInfo = collegeInfoBean.processInfo_search(keyword);
        return "/college/collegeSearchListPage.xhtml";
    }

    public String refresh() {
        this.CInfo = collegeInfoBean.processInfo_default();
        return "/college/collegeSearchListPage.xhtml";
    }

    public String getCollegeDetailsById() {
        this.id = Integer.parseInt(FacesContext.getCurrentInstance()
                .getExternalContext().getRequestParameterMap()
                .get("college_id"));
        College college = collegeInfoBean.getCollegeById(this.id);
        logger.log(Level.INFO, "current college {0}", String.valueOf(college.getCId()));
        getStars(college);

        // required properties
        this.setName_ch(college.getCNameCh());
        this.setName_en(college.getCNameEn());
        this.setCountry(college.getCCountry());
        this.setInfo(college.getCInfo());
        this.setSite(college.getCSite());
        this.setPic(college.getCPic());

        // optional properties
        this.tofel = college.getCTofel() != null ? college.getCTofel().intValue() : 0;
        this.gre = college.getCGre() != null ? college.getCGre().intValue() : 0;
        this.fee = college.getCFee() != null ? college.getCFee().intValue() : 0;
        this.gpa = college.getCGPA() != null ? college.getCGPA().intValue() : 0;

        logger.log(Level.INFO, "TOFEL {0}", String.valueOf(this.tofel));
        logger.log(Level.INFO, "GRE {0}", String.valueOf(this.gre));
        logger.log(Level.INFO, "Fee {0}", String.valueOf(this.fee));
        logger.log(Level.INFO, "GPA {0}", String.valueOf(this.gpa));
        logger.log(Level.INFO, "Stars {0}", stars);

        return "/college/collegeSearchInfoPage.xhtml";
    }

    public void refreshStars() {
        this.id = Integer.parseInt(FacesContext.getCurrentInstance()
                .getExternalContext().getRequestParameterMap()
                .get("cur_college_id"));
        College cur_college = collegeInfoBean.getCollegeById(this.id);
        getStars(cur_college);
        logger.log(Level.INFO, "cur_stars after refresh {0}", this.stars.toString());
    }

    public void getStars(College college) {
        List<User> result = collegeInfoBean.getAllStarers(college);
        this.stars = result;
    }

    public String default_process() {
        this.CInfo = collegeInfoBean.processInfo_default();
        return "/college/collegeListPage.xhtml";
    }



    public String feeAsc_process() {
        this.CInfo = collegeInfoBean.processInfo_feeAsc();
        return "/college/collegeListPage.xhtml";
    }

    public List<User> getStars() {
        return stars;
    }

    public void setStars(List<User> stars) {
        this.stars = stars;
    }

    public CollegeInfoBean getCollegeInfoBean() {
        return collegeInfoBean;
    }

    public void setCollegeInfoBean(CollegeInfoBean collegeInfoBean) {
        this.collegeInfoBean = collegeInfoBean;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName_ch() {
        return name_ch;
    }

    public void setName_ch(String name_ch) {
        this.name_ch = name_ch;
    }

    public String getName_en() {
        return name_en;
    }

    public void setName_en(String name_en) {
        this.name_en = name_en;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public int getTofel() {
        return tofel;
    }

    public void setTofel(int tofel) {
        this.tofel = tofel;
    }

    public int getGre() {
        return gre;
    }

    public void setGre(int gre) {
        this.gre = gre;
    }

    public int getFee() {
        return fee;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

}
