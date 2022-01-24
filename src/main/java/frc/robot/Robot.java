// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
 
package frc.robot;
 
import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
 
import com.ctre.phoenix.CANifier;
 
/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the
 * name of this class or
 * the package after creating this project, you must also update the
 * build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private Command m_autonomousCommand;
 
  private RobotContainer m_robotContainer;
 
  public static XboxController controller;
  public static AddressableLED m_led;
  public static AddressableLEDBuffer m_ledBuffer;
 
  int r;
  int g;
  int b;
 
  int rr;
  int gg;
  int bb;
 
  int holdSpeed = 256;
  int X = 0;
  int n = 1;
 
  /**
   * This function is run when the robot is first started up and should be used
   * for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    // Instantiate our RobotContainer. This will perform all our button bindings,
    // and put our
    // autonomous chooser on the dashboard.
    m_robotContainer = new RobotContainer();
 
    m_led = new AddressableLED(9);
    controller = new XboxController(0);
 
    // Reuse buffer
    // Default to a length of 60, start empty output
    // Length is expensive to set, so only set it once, then just update data
    m_ledBuffer = new AddressableLEDBuffer(169);
    m_led.setLength(m_ledBuffer.getLength());
 
    // Set the data
    m_led.setData(m_ledBuffer);
    m_led.start();
 
  }
 
  /**
   * This function is called every robot packet, no matter the mode. Use this for
   * items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and
   * test.
   *
   * <p>
   * This runs after the mode specific periodic functions, but before LiveWindow
   * and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // Runs the Scheduler. This is responsible for polling buttons, adding
    // newly-scheduled
    // commands, running already-scheduled commands, removing finished or
    // interrupted commands,
    // and running subsystem periodic() methods. This must be called from the
    // robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();
  }
 
  /** This function is called once each time the robot enters Disabled mode. */
  @Override
  public void disabledInit() {
  }
 
  @Override
  public void disabledPeriodic() {
  }
 
  /**
   * This autonomous runs the autonomous command selected by your
   * {@link RobotContainer} class.
   */
  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_robotContainer.getAutonomousCommand();
 
    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }
 
  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
  }
 
  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
 
  }
 
  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
 
    if (controller.getYButton() == true) {
      controller.setRumble(RumbleType.kLeftRumble, 1);
      controller.setRumble(RumbleType.kRightRumble, 1);
    } else if (controller.getYButton() == false) {
      controller.setRumble(RumbleType.kLeftRumble, 0);
      controller.setRumble(RumbleType.kRightRumble, 0);
    }
 
    controller.getRightTriggerAxis();
    controller.getLeftTriggerAxis();
 
    holdSpeed = 256;
    holdSpeed = (int) (holdSpeed * (controller.getRightTriggerAxis() - controller.getLeftTriggerAxis()));
 
    X += holdSpeed;
 
    if (X > 1535) {
      X = 0;
    } else if (X < 0) {
      X = 1535;
    }
 
    if (X >= 0 && X <= 255) {
 
      r = 256;
      g = n * X;
      b = 0;
 
    }
 
    if (X >= 256 && X <= 511) {
 
      r = -(n * (X - 256)) + 256;
      g = 256;
      b = 0;
 
    }
 
    if (X >= 512 && X <= 767) {
 
      r = 0;
      g = 256;
      b = n * (X - 512);
 
    }
 
    if (X >= 768 && X <= 1023) {
 
      r = 0;
      g = -(n * (X - 768)) + 256;
      b = 256;
 
    }
 
    if (X >= 1024 && X <= 1279) {
 
      r = n * (X - 1024);
      g = 0;
      b = 256;
 
    }
 
    if (X >= 1280 && X <= 1535) {
 
      r = 256;
      g = 0;
      b = -(n * (X - 1280)) + 256;
 
    }
 
    if (r == 256) {
      rr = 255;
    } else {
      rr = r;
    }
    if (g == 256) {
      gg = 255;
    } else {
      gg = g;
    }
    if (b == 256) {
      bb = 255;
    } else {
      bb = b;
    }
 
    for (var bg = 0; bg < (m_ledBuffer.getLength()); bg++) {
 
      // Sets the specified LED to the RGB values for red
      m_ledBuffer.setRGB(bg, rr, gg, bb);
 
    }
 
    m_led.setData(m_ledBuffer);
 
  }
 
  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }
 
  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {
  }
}
 

