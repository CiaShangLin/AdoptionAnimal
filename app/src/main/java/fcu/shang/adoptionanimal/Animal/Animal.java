package fcu.shang.adoptionanimal.Animal;

import android.graphics.Bitmap;

/**
 * Created by SERS on 2017/7/13.
 */

public class Animal {
    private String animal_id;            //動物的流水編號
    private String animal_subid;          //動物的區域編號
    private String animal_area_pkid;      //動物所屬縣市代碼
    private int animal_shelter_pkid;        //動物所屬收容所代碼
    private String animal_place;         //動物的實際所在地
    private String animal_kind;          //動物的類型
    private String animal_sex;           //動物性別
    private String animal_bodytype;       //動物體型
    private String animal_colour;         //動物毛色
    private String animal_age;           //動物年紀
    private String animal_sterilization;    //是否絕育
    private String animal_bacterin;       //是否施打狂犬病疫苗
    private String animal_foundplace;    //動物尋獲地
    private String animal_title;          //動物網頁標題
    private String animal_status;         //動物狀態
    private String animal_remark;        //資料備註
    private String animal_caption;          //其他說明
    private String animal_opendate;       //開放認養時間(起)
    private  String animal_closeddate;     //開放認養時間(迄)
    private  String animal_update;        //動物資料異動時間
    private String animal_createtime;     //動物資料建立時間
    private String shelter_name;          //動物所屬收容所名稱
    private String album_name;          //圖片名稱(原始名稱)
    private String album_file;            //圖片名稱
    private String album_base64;           //圖片base64編碼
    private String album_update;
    private String cDate;                //異動時間
    private String shelter_address;        //地址
    private String shelter_tel;            //聯絡電話等欄位資訊
    private int tag;

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }

    public String getAnimal_place() {
        return animal_place;
    }

    public void setAnimal_place(String animal_place) {
        this.animal_place = animal_place;
    }

    public String getAnimal_kind() {
        return animal_kind;
    }

    public void setAnimal_kind(String animal_kind) {
        this.animal_kind = animal_kind;
    }

    public String getAnimal_sex() {
        return animal_sex;
    }

    public void setAnimal_sex(String animal_sex) {
        this.animal_sex = animal_sex;
    }

    public String getAnimal_bodytype() {
        return animal_bodytype;
    }

    public void setAnimal_bodytype(String animal_bodytype) {
        this.animal_bodytype = animal_bodytype;
    }

    public String getAnimal_colour() {
        return animal_colour;
    }

    public void setAnimal_colour(String animal_colour) {
        this.animal_colour = animal_colour;
    }

    public String getAnimal_age() {
        return animal_age;
    }

    public void setAnimal_age(String animal_age) {
        this.animal_age = animal_age;
    }

    public String getAnimal_sterilization() {
        return animal_sterilization;
    }

    public void setAnimal_sterilization(String animal_sterilization) {
        this.animal_sterilization = animal_sterilization;
    }

    public String getAnimal_bacterin() {
        return animal_bacterin;
    }

    public void setAnimal_bacterin(String animal_bacterin) {
        this.animal_bacterin = animal_bacterin;
    }

    public String getAnimal_foundplace() {
        return animal_foundplace;
    }

    public void setAnimal_foundplace(String animal_foundplace) {
        this.animal_foundplace = animal_foundplace;
    }

    public String getAnimal_title() {
        return animal_title;
    }

    public void setAnimal_title(String animal_title) {
        this.animal_title = animal_title;
    }

    public String getAnimal_status() {
        return animal_status;
    }

    public void setAnimal_status(String animal_status) {
        this.animal_status = animal_status;
    }

    public String getAnimal_remark() {
        return animal_remark;
    }

    public void setAnimal_remark(String animal_remark) {
        this.animal_remark = animal_remark;
    }

    public String getAnimal_caption() {
        return animal_caption;
    }

    public void setAnimal_caption(String animal_caption) {
        this.animal_caption = animal_caption;
    }

    public String getAnimal_opendate() {
        return animal_opendate;
    }

    public void setAnimal_opendate(String animal_opendate) {
        this.animal_opendate = animal_opendate;
    }

    public String getAnimal_closeddate() {
        return animal_closeddate;
    }

    public void setAnimal_closeddate(String animal_closeddate) {
        this.animal_closeddate = animal_closeddate;
    }

    public String getAnimal_update() {
        return animal_update;
    }

    public void setAnimal_update(String animal_update) {
        this.animal_update = animal_update;
    }

    public String getAnimal_createtime() {
        return animal_createtime;
    }

    public void setAnimal_createtime(String animal_createtime) {
        this.animal_createtime = animal_createtime;
    }

    public String getShelter_name() {
        return shelter_name;
    }

    public void setShelter_name(String shelter_name) {
        this.shelter_name = shelter_name;
    }

    public String getAlbum_name() {
        return album_name;
    }

    public void setAlbum_name(String album_name) {
        this.album_name = album_name;
    }

    public String getAlbum_file() {
        return album_file;
    }

    public void setAlbum_file(String album_file) {
        this.album_file = album_file;
    }

    public String getAlbum_base64() {
        return album_base64;
    }

    public void setAlbum_base64(String album_base64) {
        this.album_base64 = album_base64;
    }

    public String getAlbum_update() {
        return album_update;
    }

    public void setAlbum_update(String album_update) {
        this.album_update = album_update;
    }

    public String getcDate() {
        return cDate;
    }

    public void setcDate(String cDate) {
        this.cDate = cDate;
    }

    public String getShelter_address() {
        return shelter_address;
    }

    public void setShelter_address(String shelter_address) {
        this.shelter_address = shelter_address;
    }

    public String getShelter_tel() {
        return shelter_tel;
    }

    public void setShelter_tel(String shelter_tel) {
        this.shelter_tel = shelter_tel;
    }

    public int getAnimal_shelter_pkid() {

        return animal_shelter_pkid;
    }

    public void setAnimal_shelter_pkid(int animal_shelter_pkid) {
        this.animal_shelter_pkid = animal_shelter_pkid;
    }


    public void setAnimal_id(String animal_id) {
        this.animal_id = animal_id;
    }

    public void setAnimal_subid(String animal_subid) {
        this.animal_subid = animal_subid;
    }

    public void setAnimal_area_pkid(String animal_area_pkid) {
        this.animal_area_pkid = animal_area_pkid;
    }

    public String getAnimal_id() {
        return animal_id;
    }

    public String getAnimal_subid() {
        return animal_subid;
    }

    public String getAnimal_area_pkid() {
        return animal_area_pkid;
    }
}
