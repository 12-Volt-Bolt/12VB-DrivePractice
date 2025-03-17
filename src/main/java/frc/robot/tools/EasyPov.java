package frc.robot.tools;

public class EasyPov {

  private int[] values;
  private int defaultValue;

  public EasyPov(int[] values, int defaultValue) {
    setPovValues(values);
    setDefaultValue(defaultValue);
  }

  public void setPovValues(int[] values) {
    this.values = new int[8];
    int length = 8 > values.length ? values.length : 8;
    for (int i = 0; i < length; i++)
    {
      this.values[i] = values[i];
    }
  }

  public void setDefaultValue(int defaultValue) {
    this.defaultValue = defaultValue;
  }

  public int getPovValue(int basePovValue) {
    switch (basePovValue) {
      case -1:
        return defaultValue;
      case 0:
        return values[0];
      case 45:
        return values[1];
      case 90:
        return values[2];
      case 135:
        return values[3]; 
      case 180:
        return values[4];
      case 225:
        return values[5];
      case 270:
        return values[6];
      case 315:
        return values[7];
    
      default:
        return -1;
    }
  }
}
