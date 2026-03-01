public class ClassroomController {
    private final DeviceRegistry reg;

    public ClassroomController(DeviceRegistry reg) { this.reg = reg; }

    public void startClass() {
        Projector pj = (Projector) reg.getFirstPowerableOfType("Projector");
        pj.powerOn();
        pj.connectInput("HDMI-1");

        BrightnessControl lights = reg.getFirstOfType(BrightnessControl.class);
        lights.setBrightness(60);

        TemperatureControl ac = reg.getFirstOfType(TemperatureControl.class);
        ac.setTemperatureC(24);

        AttendanceCapable scan = reg.getFirstOfType(AttendanceCapable.class);
        System.out.println("Attendance scanned: present=" + scan.scanAttendance());
    }

    public void endClass() {
        System.out.println("Shutdown sequence:");
        reg.getFirstPowerableOfType("Projector").powerOff();
        reg.getFirstPowerableOfType("LightsPanel").powerOff();
        reg.getFirstPowerableOfType("AirConditioner").powerOff();
    }
}
