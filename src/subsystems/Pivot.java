package subsystems;

public class Pivot {
    private final Servo _left;
    private final Servo _right;

    public Pivot(HardwareMap hardwareMap, String left, String right, double min, double max) {
        _left = hardwareMap.get(Servo.class, left);
        _right = hardwareMap.get(Servo.class, right);
        _left.setDirection(Servo.Direction.FORWARD);
        _right.setDirection(Servo.Direction.REVERSE);
        _left.scaleRange(min, max);
        _right.scaleRange(min, max);
    }

    // Offset added to left servo
    public Pivot(HardwareMap hardwareMap, String left, String right, double min, double max, double offset) {
        _left = hardwareMap.get(Servo.class, left);
        _right = hardwareMap.get(Servo.class, right);
        _left.setDirection(Servo.Direction.FORWARD);
        _right.setDirection(Servo.Direction.REVERSE);
        _left.scaleRange(min, max+offset);
        _right.scaleRange(min, max);
    }

    public void flipOut() {
        _left.setPosition(1);
        _right.setPosition(1);
    }

    public void flipIn() {
        _left.setPosition(0);
        _right.setPosition(0);
    }

    public void flipTo(double position) {
        _left.setPosition(position);
        _right.setPosition(position);
    }

    public boolean isOut() {
        return (_left.getPosition()>=(0.99) &&
                _right.getPosition()>=(0.99));
    }

    public boolean isIn() {
        return (_left.getPosition()<=(0.01) &&
                _right.getPosition()<=(0.01));
    }

    public boolean isAt(double position) {
        return (_left.getPosition()>=(position-0.01) && _left.getPosition()<=(position+0.01) &&
                _right.getPosition()>=(position-0.01) && _right.getPosition()<=(position+0.01));
    }

    public int getLeftPosition() {
        return _left.getPosition();
    }

    public int getRightPosition() {
        return _right.getPosition();
    }
}
