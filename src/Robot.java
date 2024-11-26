
import com.qualcomm.robotcore.hardware.HardwareMap;
import subsystems.Claw;
import subsystems.MotorSlide;
import subsystems.Pivot;
import subsystems.ServoSlide;

public class Robot {
    public final Claw intakeClaw;
    public final Claw outtakeClaw;
    public final Pivot intakePivot;
    public final Pivot outtakePivot;

    public final MotorSlide verticalSlide;
    public final ServoSlide horizontalSlide;

    public Robot(HardwareMap hardwareMap) {
        intakeClaw = new Claw(hardwareMap, "intakeClawLeft", "intakeClawRight", 0, 1);
        outtakeClaw = new Claw(hardwareMap, "outtakeClawLeft", "outtakeClawRight", 0, 0.45);
        intakePivot = new Pivot(hardwareMap, "intakePivotLeft", "intakePivotRight", 0, 1);
        outtakePivot = new Pivot(hardwareMap, "outtakePivotLeft", "outtakePivotRight", 0, 1);

        verticalSlide = new MotorSlide(hardwareMap, "leftVerticalSlide", "rightVerticalSlide", 50, 3050);
        horizontalSlide = new ServoSlide(hardwareMap, "leftHorizontalSlide", "rightHorizontalSlide", 0, 0.5);

    }
}