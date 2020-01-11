/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends TimedRobot {

  //Code that we wrote!!!
  WPI_TalonSRX frontRightTalon;
  WPI_TalonSRX frontLeftTalon;
  WPI_TalonSRX backRightTalon;
  WPI_TalonSRX backLeftTalon;  
  DifferentialDrive exampleRobotDrive;
  Joystick leftStick;
  Joystick rightStick;
  SpeedControllerGroup rightGroup;
  SpeedControllerGroup leftGroup;

  //Example code provided by FIRST
  private final DifferentialDrive robotDrive
      = new DifferentialDrive(new PWMVictorSPX(0), new PWMVictorSPX(1));
  

  //private final Joystick leftStick = new Joystick(0);
  //private final Joystick rightStick = new Joystick(1);

  private final Timer timer = new Timer();

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    frontRightTalon = new WPI_TalonSRX(1);
    frontLeftTalon = new WPI_TalonSRX(2);
    backRightTalon = new WPI_TalonSRX(3);
    backLeftTalon = new WPI_TalonSRX(4);
    leftStick = new Joystick(0);
    rightStick = new Joystick(1);
    
    rightGroup = new SpeedControllerGroup(frontRightTalon, backRightTalon);
    leftGroup = new SpeedControllerGroup(frontLeftTalon, backLeftTalon);
    exampleRobotDrive = new DifferentialDrive(rightGroup, leftGroup);
  }

  /**
   * This function is run once each time the robot enters autonomous mode.
   */
  @Override
  public void autonomousInit() {
    timer.reset();
    timer.start();
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    // Drive for 2 seconds
    if (timer.get() < 2.0) {
      robotDrive.arcadeDrive(0.5, 0.0); // drive forwards half speed
    } else {
      robotDrive.stopMotor(); // stop robot
    }
  }

  /**
   * This function is called once each time the robot enters teleoperated mode.
   */
  @Override
  public void teleopInit() {
  }

  /**
   * This function is called periodically during teleoperated mode.
   */
  @Override
  public void teleopPeriodic() {
//    robotDrive.arcadeDrive(leftStick.getY(), leftStick.getX());
    exampleRobotDrive.tankDrive(leftStick.getY(), rightStick.getY());
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
