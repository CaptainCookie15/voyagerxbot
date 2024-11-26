package auto;
import robot.Robot;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous()
public class Specimen extends LinearOpMode {
    private Robot robot;
    enum State {
        idle
    }
    State curState = State.idle;

    @Override
    public void runOpMode() throws InterruptedException {
        robot = new Robot(hardwareMap);

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();
        if (isStopRequested()) return;

        while(opModeIsActive()) {
            stateMachine();
        }
    }

    private void stateMachine() throws InterruptedException {
        switch(curState) {
            case idle:
                break;
        }
    }
}
