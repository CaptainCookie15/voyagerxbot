package subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class MotorSlide {
    private final DcMotor _left;
    private final DcMotor _right;
    private final int _max;
    private final int _min;
    private final double _power;

    public MotorSlide(HardwareMap hardwareMap, String left, String right, int max, int min, double power) {
        _left = hardwareMap.get(DcMotor.class, left);
        _right = hardwareMap.get(DcMotor.class, right);

        _max = max;
        _min = min;
        _power = power;

        _left.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        _right.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        _left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        _right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        _left.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        _right.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void extend(int increment) {
        int newLeftPosition = _left.getCurrentPosition() + increment;
        int newRightPosition = _right.getCurrentPosition() + increment;

        if (newLeftPosition <= _max) {
            _left.setPower(_power);
        } else {
            _left.setPower(0);
        }

        if (newRightPosition <= _max) {
            _right.setPower(_power);
        } else {
            _right.setPower(0);
        }
    }

    public void retract(int increment) {
        int newLeftPosition = _left.getCurrentPosition() - increment;
        int newRightPosition = _right.getCurrentPosition() - increment;

        if (newLeftPosition >= _min) {
            _left.setPower(-_power);
        } else {
            _left.setPower(0);
        }

        if (newRightPosition >= _min) {
            _right.setPower(-_power);
        } else {
            _right.setPower(0);
        }
    }

    public void extendFull() {
        if (_left.getCurrentPosition() < _max) {
            _left.setPower(_power);
        } else {
            _left.setPower(0);
        }

        if (_right.getCurrentPosition() < _max) {
            _right.setPower(_power);
        } else {
            _right.setPower(0);
        }
    }

    public void retractFull() {
        if (_left.getCurrentPosition() > _min) {
            _left.setPower(-_power);
        } else {
            _left.setPower(0);
        }

        if (_right.getCurrentPosition() > _min) {
            _right.setPower(-_power);
        } else {
            _right.setPower(0);
        }
    }

    public void stop() {
        _left.setPower(0);
        _right.setPower(0);
    }

    public boolean isFullExtended() {
        return (_left.getCurrentPosition() >= _max &&
                _right.getCurrentPosition() >= _max);
    }

    public boolean isFullRetracted() {
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