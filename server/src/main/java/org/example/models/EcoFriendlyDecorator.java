package org.example.models;

public class EcoFriendlyDecorator implements  PackageDecorator {
    private Package aPackage;

    public EcoFriendlyDecorator(Package aPackage) {
        this.aPackage = aPackage;
        updateTrackingInfo(aPackage.getInfo());
    }
    @Override
    public String getInfo() {
        return aPackage.getInfo();
    }

    @Override
    public void updateTrackingInfo(String info) {
        aPackage.setInfo(info + " [Paczka ekologiczna]");
    }
}
