package utils;

public class TimerUtil
{
    
    private long startTime;
    private long length;
    
    public TimerUtil() {
        this(0);
    }
    
    public TimerUtil(long milliseconds) {
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
