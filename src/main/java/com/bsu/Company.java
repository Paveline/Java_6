package main.java.com.bsu;

import java.util.Objects;

class Company {
    private String companyName;
    private String shortCompanyName;
    private int dateOfActualization;
    private String address;
    private int dateOfFoundation;
    private int countOfEmployes;
    private String auditor;
    private String number;
    private String email;
    private String industry;
    private String typeOfActivity;
    private String website;

    public Company(String companyName, String shortCompanyName, int dateOfActualization, String address, int dateOfFoundation, int countOfEmployes,
                   String auditor, String number, String email, String industry, String typeOfActivity, String website) {
        this.companyName = companyName;
        this.shortCompanyName = shortCompanyName;
        this.dateOfActualization = dateOfActualization;
        this.address = address;
        this.dateOfFoundation = dateOfFoundation;
        this.countOfEmployes = countOfEmployes;
        this.auditor = auditor;
        this.number = number;
        this.email = email;
        this.industry = industry;
        this.typeOfActivity = typeOfActivity;
        this.website = website;
    }

    public Company(String[] arr) {
        this.companyName = arr[0];
        this.shortCompanyName = arr[1];
        this.dateOfActualization = Integer.parseInt(arr[2]);
        this.address = arr[3];
        this.dateOfFoundation = Integer.parseInt(arr[4]);
        this.countOfEmployes = Integer.parseInt(arr[5]);
        this.auditor = arr[6];
        this.number = arr[7];
        this.email = arr[8];
        this.industry = arr[9];
        this.typeOfActivity = arr[10];
        this.website = arr[11];
    }

    @Override
    public String toString() {
        return companyName + ';' + shortCompanyName + ';' + dateOfActualization + ';' + address + ';' +
                dateOfFoundation + ';' + countOfEmployes + ';' +auditor + ';' + number + ';' + email + ';' +
                industry + ';' + typeOfActivity + ';' + website;
    }

    public boolean equals(Company comp) {
        if (this == comp) return true;
        else {
            return this.companyName.equals(comp.companyName) &&
                    this.shortCompanyName.equals(comp.shortCompanyName) &&
                    this.dateOfActualization == comp.dateOfActualization &&
                    this.address.equals(comp.address) &&
                    this.dateOfFoundation == comp.dateOfFoundation &&
                    this.countOfEmployes == comp.countOfEmployes &&
                    this.auditor.equals(comp.auditor) &&
                    this.number.equals(comp.number) &&
                    this.email.equals(comp.email) &&
                    this.industry.equals(comp.industry) &&
                    this.typeOfActivity.equals(comp.typeOfActivity) &&
                    this.website.equals(comp.website);
        }
    }

    public int hashCode() {
        return Objects.hash(companyName, shortCompanyName, dateOfActualization, address, dateOfFoundation, countOfEmployes,
                auditor, number, email, industry, typeOfActivity, website);
    }

    //-----get-----
    public String getCompanyName() {
        return companyName;
    }

    public String getShortCompanyName() {
        return shortCompanyName;
    }

    public int getDateOfActualization() {
        return dateOfActualization;
    }

    public String getAddress() {
        return address;
    }

    public int getDateOfFoundation() {
        return dateOfFoundation;
    }

    public int getCountOfEmployes() {
        return countOfEmployes;
    }

    public String getAuditor() {
        return auditor;
    }

    public String getNumber() {
        return number;
    }

    public String getEmail() {
        return email;
    }

    public String getIndustry() {
        return industry;
    }

    public String getTypeOfActivity() {
        return typeOfActivity;
    }

    public String getWebsite() {
        return website;
    }

    //-----set-----

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setShortCompanyName(String shortCompanyName) {
        this.shortCompanyName = shortCompanyName;
    }

    public void setDateOfActualization(int dateOfActualization) {
        this.dateOfActualization = dateOfActualization;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDateOfFoundation(int dateOfFoundation) {
        this.dateOfFoundation = dateOfFoundation;
    }

    public void setCountOfEmployes(int countOfEmployes) {
        this.countOfEmployes = countOfEmployes;
    }

    public void setAuditor(String auditor) {
        this.auditor = auditor;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public void setTypeOfActivity(String typeOfActivity) {
        this.typeOfActivity = typeOfActivity;
    }

    public void setWebsite(String website) {
        this.website = website;
    }
}
