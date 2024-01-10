package com.example.mailmanagementsystem.models;

public class InsuredPackageDecorator implements PackageDecorator {
    private Package aPackage;
    private double insuredValue;

    public InsuredPackageDecorator(Package aPackage, double insuredValue) {
        this.aPackage = aPackage;
        this.insuredValue = insuredValue;
        updateTrackingInfo(aPackage.getInfo());
    }

    @Override
    public void updateTrackingInfo(String info) {
        aPackage.setInfo(info +  "[Ubezpieczone na " + insuredValue + " zl]");
    }

    @Override
    public String getInfo() {
        return aPackage.getInfo();
    }
}
