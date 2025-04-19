package com.example.week8_2;

public class Company {
    int image ;			// Photo of CEO
    String ceo;			// Name of CEO
    String company;		// Name of Company
    String desc;		// Description of CEP
    String time;		// Date and Time



    public Company(int image, String ceo, String company, String desc,
                   String time) {
        super();
        this.image = image;
        this.ceo = ceo;
        this.company = company;
        this.desc = desc;
        this.time = time;
    }

    public String getCeo() {
        return ceo;
    }

    public void setCeo(String ceo) {
        this.ceo = ceo;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

}