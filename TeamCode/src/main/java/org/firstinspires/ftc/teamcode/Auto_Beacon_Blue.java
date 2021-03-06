/*
Copyright (c) 2016 Robert Atkinson

All rights reserved.

Redistribution and use in source and binary forms, with or without modification,
are permitted (subject to the limitations in the disclaimer below) provided that
the following conditions are met:

Redistributions of source code must retain the above copyright notice, this list
of conditions and the following disclaimer.

Redistributions in binary form must reproduce the above copyright notice, this
list of conditions and the following disclaimer in the documentation and/or
other materials provided with the distribution.

Neither the name of Robert Atkinson nor the names of his contributors may be used to
endorse or promote products derived from this software without specific prior
written permission.

NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
"AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESSFOR A PARTICULAR PURPOSE
ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR
TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * This file contains an minimal examp
 * le of a Linear "OpMode". An OpMode is a 'program' that runs in either
 * the autonomous or the teleop period of an FTC match. The names of OpModes appear on the menu
 * of the FTC Driver Station. When an selection is made from the menu, the corresponding OpMode
 * class is instantiated on the Robot Controller and executed.
 *
 * This particular OpMode just executes a basic Tank Drive Teleop for a PushBot
 * It includes all the skeletal structure that all linear OpModes contain.
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@Autonomous(name="Auto_Beacon_Blue", group="PDRobot")  // @Auto(...) is the other common choice

public class Auto_Beacon_Blue extends LinearOpMode {

    /* Declare OpMode members. */
    HardwarePD robot = new HardwarePD();
    private ElapsedTime runtime = new ElapsedTime();
    // DcMotor leftMotor = null;
    // DcMotor rightMotor = null;

    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        //robot.Giro.calibrate();
        boolean stp=true;
        telemetry.addData("Status", "Initialized");
        //telemetry.addData("Gyroscope calibration is ", robot.Giro.status().toString());
        telemetry.update();
        waitForStart();
        runtime.reset();
        robot.leftMotor.setPower(1);
        robot.rightMotor.setPower(0.1);
        sleep(800);
        robot.leftMotor.setPower(0.7);
        robot.rightMotor.setPower(0.7);
        while(opModeIsActive()&&!stp){
            String ds=robot.Distanta.toString();
            int dist=Integer.parseInt(ds);
            if(dist<48) stp=true;
        }
        sleep(200);
        if(robot.Color.red()>robot.Color.blue()) robot.hitBeacon.setPosition(Servo.MIN_POSITION);
        robot.leftMotor.setPower(0.7);
        robot.rightMotor.setPower(0.7);
        sleep(700);
        robot.rightMotor.setPower(-0.8);
        sleep(1000);
        robot.leftMotor.setPower(0.7);
        robot.rightMotor.setPower(0.7);
        sleep(750);
        robot.leftMotor.setPower(0);
        robot.rightMotor.setPower(0);

    }
}



