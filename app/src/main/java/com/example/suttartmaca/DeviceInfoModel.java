package com.example.suttartmaca;

public class DeviceInfoModel {
    private String deviceHardwareAddress;
    private String deviceName;

    public DeviceInfoModel() {
    }

    public DeviceInfoModel(String deviceName2, String deviceHardwareAddress2) {
        this.deviceName = deviceName2;
        this.deviceHardwareAddress = deviceHardwareAddress2;
    }

    public String getDeviceName() {
        return this.deviceName;
    }

    public String getDeviceHardwareAddress() {
        return this.deviceHardwareAddress;
    }
}
