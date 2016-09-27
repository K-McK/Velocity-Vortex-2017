package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.HardwareLayout;


@TeleOp(name="tele1", group="test")
//@Disabled
public class tele1 extends LinearOpMode {

    /* Declare OpMode members. */
    HardwareLayout   robot           = new HardwareLayout();
//    @Override
    public void runOpMode() throws InterruptedException {
        double left;
        double right;
//
//        /* Initialize the hardware variables.
//         * The init() method of the hardware class does all the work here
//         */
        robot.init(hardwareMap);

        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            // Run wheels in tank mode (note: The joystick goes negative when pushed forwards, so negate it)
            left = -gamepad1.left_stick_y;
            right = -gamepad1.right_stick_y;

            if (Math.abs(left)>0.1) {
                if (left<0) left = -(Math.pow(gamepad1.left_stick_y, 2)/1);
                else left = (Math.pow(gamepad1.left_stick_y, 2)/1);
            }
            else left = 0;

            if (Math.abs(right)>0.1) {
                if (right<0) right = -(Math.pow(gamepad1.right_stick_y, 2)/1);
                else right = (Math.pow(gamepad1.right_stick_y, 2)/1);
            }
            else right = 0;

            if (gamepad1.left_bumper || gamepad1.right_bumper) {
                //fullspeed
            }
            else if (Math.abs(gamepad1.left_trigger) > 0.2 || Math.abs(gamepad1.right_trigger) > 0.2) {
                right = right * 0.40;
                left = left * 0.40;
            }
            else {
                right = right * 0.75;
                left = left * 0.75;
            }

            robot.leftMotor.setPower(left);
            robot.rightMotor.setPower(right);

            telemetry.addData("left", "%.2f", left);
            telemetry.addData("right", "%.2f", right);
            telemetry.addData("right", "%7d", robot.rightMotor.getCurrentPosition());
            telemetry.addData("left", "%7d", robot.leftMotor.getCurrentPosition());
            telemetry.update();

            if (gamepad1.a) {
                robot.leftMotor.setPower(0);
                robot.rightMotor.setPower(0);
                sleep(10);
                stop();
            }

            idle(); // Always call idle() at the bottom of your while(opModeIsActive()) loop
        }
    }
}
