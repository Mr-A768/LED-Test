// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.AddressableLED;




public class ledSubsystem extends SubsystemBase {
  /** Creates a new ledSubsystem. */
  public ledSubsystem() {}

  public static AddressableLED m_led;
  public static AddressableLEDBuffer m_ledBuffer; 

  //Set the data 
  public void setLEDVar(AddressableLED LED, AddressableLEDBuffer LEDBuffer){
    m_led = LED; 
    m_ledBuffer = LEDBuffer; 
  } 

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void colors(){
    int r1 = 255; 
  }

  public void solid(){
    
    for (int i = 0; i < (m_ledBuffer.getLength()); i++) {
      // Sets the specified LED to the RGB values for red 
      m_ledBuffer.setRGB(i, 128, 128, 128); 
    }

    m_led.setData(m_ledBuffer);
    m_led.start();
  }
  
  public void singleOrbit(){
    //code
  }
  
  public void doubleOrbit(){
    //code
  }
  
  public void blink(){
    //code
  }
  
  public void rainbow(){

  } 


}




