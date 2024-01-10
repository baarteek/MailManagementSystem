package org.example.models;

public class FragilePackageDecorator implements PackageDecorator {
    private Package aPackage;

    public FragilePackageDecorator(Package aPackage) {
        this.aPackage = aPackage;
        updateTrackingInfo(aPackage.getInfo());
    }

    @Override
    public void updateTrackingInfo(String info) {
        aPackage.setInfo(info + " [Uwaga - Kruche]");
    }

    @Override
    public String getInfo() {
        return aPackage.getInfo();
    }
}
