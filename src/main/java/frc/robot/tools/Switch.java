package frc.robot.tools;

public class Switch {

    private boolean state;
    private boolean risingEdge;

    /**
     * Creates a new switch with an initial state of true.
     */
    public Switch()
    {
        state = true;
    }

    public Switch(boolean initialState)
    {
        state = initialState;
    }

    public boolean state()
    {
        return state;
    }

    public void flip()
    {
        state = !state;
    }

    /**
     * Flips the switch only when 'input' changes to true, 'input' must change to false before the switch will flip again.
     * 
     * @return Whether or not the value changed.
     */
    public boolean flipOnTrue(boolean input)
    {
        boolean output = false;
        if (input == true && risingEdge == false)
        {
            risingEdge = true;
            flip();
            output = true;
        } else if (input == false)
        {
            risingEdge = false;
        }
        return output;
    }

    public void set(boolean newState)
    {
        state = newState;
    }
}