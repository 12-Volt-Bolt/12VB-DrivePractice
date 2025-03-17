/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.tools;

/**
 * Add your docs here.
 */
public class Timer
{
    
    private long startTime;
    private long length;
    
    public Timer() {
        this(0);
    }
    
    public Timer(long milliseconds) {
        reset();
        setLength(milliseconds);
    }
    
    public void reset()
    {
        startTime = System.currentTimeMillis();
    }
    
    public void setLength(long milliseconds)
    {
        if (milliseconds > 0)
        {
            length = milliseconds;
        } else
        {
            length = -1;
        }
    }

    public long getLength()
    {
        return length;
    }
    
    public long getStartTime()
    {
        return startTime;
    }
    
    public boolean isFinished()
    {
        return (System.currentTimeMillis() > startTime + length && length > 0) ? true : false;
    }
}
