package org.example.models;

public class ExpressDeliveryDecorator implements PackageDecorator {
    private Package aPackage;

    public ExpressDeliveryDecorator(Package aPackage) {
        this.aPackage = aPackage;
        updateTrackingInfo(aPackage.getInfo());
    }
    @Override
    public String getInfo() {
        return aPackage.getInfo();
    }

    @Override
    public void updateTrackingInfo(String info) {
        aPackage.setInfo(info + " [Dostawa Ekspresowa]");
    }
}
