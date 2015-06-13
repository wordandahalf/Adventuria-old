package net.adventuria.level;

import java.awt.Color;
import java.awt.Graphics;
import net.adventuria.Component;

public class Sky
{
  public int day = 0;
  public int night = 1;
  public int time = this.day;
  public static int dayFrame = 0;
  public static int dayTime = 18000;
  public int changeFrame = 0;
  public int changeTime = 4;
  public int r1 = 70;
  public int g1 = 120;
  public int b1 = 230;
  public int r2 = 15;
  public int g2 = 15;
  public int b2 = 80;
  public int r = this.r1;
  public int b = this.b1;
  public int g = this.g1;
  
  public Sky()
  {
    if (this.time == this.day)
    {
      this.r = this.r1;
      this.g = this.g1;
      this.b = this.b1;
    }
    else if (this.time == this.night)
    {
      this.r = this.r2;
      this.g = this.g2;
      this.b = this.b2;
    }
  }
  
  public void Tick()
  {
    if (dayFrame >= dayTime)
    {
      if (this.time == this.day) {
        this.time = this.night;
      } else if (this.time == this.night) {
        this.time = this.day;
      }
      dayFrame = 0;
    }
    else
    {
      dayFrame += 1;
    }
    if (this.changeFrame >= this.changeTime)
    {
      if (this.time == this.day)
      {
        if (this.r < this.r1) {
          this.r += 1;
        }
        if (this.g < this.g1) {
          this.g += 1;
        }
        if (this.b < this.b1) {
          this.b += 1;
        }
      }
      else if (this.time == this.night)
      {
        if (this.r > this.r2) {
          this.r -= 1;
        }
        if (this.g > this.g2) {
          this.g -= 1;
        }
        if (this.b > this.b2) {
          this.b -= 1;
        }
      }
      this.changeFrame = 0;
    }
    else
    {
      this.changeFrame += 1;
    }
  }
  
  public void Render(Graphics gr)
  {
    gr.setColor(new Color(this.r, this.g, this.b));
    gr.fillRect(0, 0, Component.pixel.width, Component.pixel.height);
  }
}
