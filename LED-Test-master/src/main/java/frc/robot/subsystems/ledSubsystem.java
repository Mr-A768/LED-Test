// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.AddressableLED;

public class ledSubsystem extends SubsystemBase {
  /** Creates a new ledSubsystem. */
  public ledSubsystem() {
  }

  public static AddressableLED m_led = new AddressableLED(9);
  public static AddressableLEDBuffer m_ledBuffer = new AddressableLEDBuffer(50);
  public final Timer m_timer = new Timer();

  int count = 0;

  private int r;
  private int g;
  private int b;

  private int rr;
  private int gg;
  private int bb;

  private int X = 0;
  private int n = 1;
  private int counter = 0;

  private int kitt = 0;

  public void setLength() {
    m_timer.reset();
    m_timer.start();
    m_led.setLength(m_ledBuffer.getLength());

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void solid(int red, int green, int blue) {

    for (int i = 0; i < (m_ledBuffer.getLength()); i++) {
      // Sets the specified LED to the RGB values for red
      m_ledBuffer.setRGB(i, red, green, blue);
    }

    m_led.setData(m_ledBuffer);
    m_led.start();
  }

  public void orbit(int red, int green, int blue) {
    if (counter == 120) {
      m_timer.reset();
      m_timer.start();
      counter = 0;
      solid(0, 0, 0);
    }
    int num = (int) (10 * m_timer.get());

    int i = (num % m_ledBuffer.getLength()) + 1;
    // Sets the specified LED to the RGB values for red
    m_ledBuffer.setRGB(i, red, green, blue);

    int j = (num % m_ledBuffer.getLength());
    // Sets the specified LED to the RGB values for red
    m_ledBuffer.setRGB(j, 0, 0, 0);

    m_led.setData(m_ledBuffer);
    m_led.start();
    counter++;
  }

  public void nightRider(int red, int green, int blue) {
    if (counter == 120) {
      m_timer.reset();
      m_timer.start();
      solid(0, 0, 0);
      kitt++;
    }

    int num = (int) (10 * m_timer.get());

    if (kitt % 2 == 0) {

      int i = (num % m_ledBuffer.getLength()) + 1;
      // Sets the specified LED to the RGB values for red
      m_ledBuffer.setRGB(i, red, green, blue);

      int j = (num % m_ledBuffer.getLength());
      // Sets the specified LED to the RGB values for red
      m_ledBuffer.setRGB(j, 0, 0, 0);

    } else {

      int i = (m_ledBuffer.getLength() - (num % m_ledBuffer.getLength()))+1;
      // Sets the specified LED to the RGB values for red
      m_ledBuffer.setRGB(i, red, green, blue);

      int j = (m_ledBuffer.getLength() - (num % m_ledBuffer.getLength()));
      // Sets the specified LED to the RGB values for red
      m_ledBuffer.setRGB(j, 0, 0, 0);

    }

    m_led.setData(m_ledBuffer);
    m_led.start();

    counter++;
  }

  public void blink(int red, int green, int blue) {
    int num = (int) (4 * m_timer.get());

    if (num % 2 == 0) {
      for (int i = 0; i < (m_ledBuffer.getLength()); i++) {
        // Sets the specified LED to the RGB values for red
        // Maybe actually rbg? code is weird
        m_ledBuffer.setRGB(i, red, green, blue);
      }
    } else {
      for (int i = 0; i < (m_ledBuffer.getLength()); i++) {
        // Sets the specified LED to the RGB values for red
        m_ledBuffer.setRGB(i, 0, 0, 0);
      }
    }
    m_led.setData(m_ledBuffer);
    m_led.start();
  }

  public void rainbow() {

    int funTimer = (int) (32 * m_timer.get());

    X = +(funTimer * 8) % 1536;

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
    m_led.start();
  }

  public void alt() {
    int num = (int) (8 * m_timer.get());

    if (num % 3 == 0) {
      for (int e = 0; e < (m_ledBuffer.getLength()); e += 3) {
        m_ledBuffer.setRGB(e, 200, 0, 0);
      }
      for (int o = 1; o < (m_ledBuffer.getLength()); o += 3) {
        m_ledBuffer.setRGB(o, 0, 200, 0);
      }
      for (int e = 2; e < (m_ledBuffer.getLength()); e += 3) {
        m_ledBuffer.setRGB(e, 0, 0, 200);
      }

    } else if (num % 3 == 1) {
      for (int e = 0; e < (m_ledBuffer.getLength()); e += 3) {
        m_ledBuffer.setRGB(e, 0, 200, 0);
      }
      for (int o = 1; o < (m_ledBuffer.getLength()); o += 3) {
        m_ledBuffer.setRGB(o, 0, 0, 200);
      }
      for (int e = 2; e < (m_ledBuffer.getLength()); e += 3) {
        m_ledBuffer.setRGB(e, 200, 0, 0);
      }

    } else if (num % 3 == 2) {
      for (int e = 0; e < (m_ledBuffer.getLength()); e += 3) {
        m_ledBuffer.setRGB(e, 0, 0, 200);
      }
      for (int o = 1; o < (m_ledBuffer.getLength()); o += 3) {
        m_ledBuffer.setRGB(o, 200, 0, 0);
      }
      for (int e = 2; e < (m_ledBuffer.getLength()); e += 3) {
        m_ledBuffer.setRGB(e, 0, 200, 0);
      }

    }

    m_led.setData(m_ledBuffer);
    m_led.start();
  }
}