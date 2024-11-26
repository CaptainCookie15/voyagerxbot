package subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class MotorSlide {
    private final DcMotor _left;
    private final DcMotor _right;
    private final int _min;
    private final int _max;

    public MotorSlide(HardwareMap hardwareMap, String left, String right, int min, int max) {
        _left = hardwareMap.get(DcMotor.class, left);
        _right = hardwareMap.get(DcMotor.class, right);

        _min = min;
        _max = max;

        _left.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        _right.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        _left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        _right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        _left.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        _right.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void extend(double power) {
        if (_left.getCurrentPosition() < _max) {
            _left.setPower(Math.abs(power));
        } else {
            _left.setPower(0);
        }

        if (_right.getCurrentPosition() < _max) {
            _right.setPower(Math.abs(power));
        } else {
            _right.setPower(0);
        }
    }

    // Retract the slides with a given power
    public void retract(double power) {
        if (_left.getCurrentPosition() > _min) {
            _left.setPower(-Math.abs(power));
        } else {
            _left.setPower(0);
        }

        if (_right.getCurrentPosition() > _min) {
            _right.setPower(-Math.abs(power));
        } else {
            _right.setPower(0);
        }
    }


    public void extendTo(int targetPosition, double power) {
        if (targetPosition < _min || targetPosition > _max) {
            return;
        }

        if (_left.getCurrentPosition() < targetPosition) {
            _left.setPower(Math.abs(power));
        } else {
            _left.setPower(0);
        }

        if (_right.getCurrentPosition() < targetPosition) {
            _right.setPower(Math.abs(power));
        } else {
            _right.setPower(0);
        }
    }


    public void extendFull(double power) {
        extendTo(_max, power);
    }

    public void retractFull(double power) {
        extendTo(_min, -power); // Use negative power for retraction
    }

    public void stop() {
        _left.setPower(0);
        _right.setPower(0);
    }

    public boolean isFullyExtended() {
        return (_left.getCurrentPosition() >= _max &&
                _right.getCurrentPosition() >= _max);
    }

    public boolean isFullyRetracted() {
        return (_left.getCurrentPosition() <= _min &&
                _right.getCurrentPosition() <= _min);
    }

    public int getLeftPosition() {
        return _left.getCurrentPosition();
    }

    public int getRightPosition() {
        return _right.getCurrentPosition();
    }
}