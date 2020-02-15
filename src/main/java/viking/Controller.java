package viking;

import edu.wpi.first.wpilibj.XboxController;

public class Controller {
    
    private XboxController controller;

    private double lTriggerDeadband = 0;
    private double rTriggerDeadband = 0;

    private double lJoystickDeadbandX = 0;
    private double lJoystickDeadbandY = 0;

    private double rJoystickDeadbandX = 0;
    private double rJoystickDeadbandY = 0;

    public Controller(int port) {
        controller = new XboxController(port);
    }

    public void setControllerLeftStickXDeadband(double value) {
      lJoystickDeadbandX = value;
    }

    public void setControllerLeftStickYDeadband(double value) {
      lJoystickDeadbandY = value;
    }

    public void setControllerRightStickXDeadband(double value) {
      rJoystickDeadbandX = value;
    }

    public void setControllerRightStickYDeadband(double value) {
      rJoystickDeadbandY = value;
    }

    public double getControllerLeftStickY() {
        return controller.getRawAxis(1) * -1;
      }
    
      public double getControllerLeftStickX() {
        return controller.getRawAxis(0);
      }
    
      public double getControllerRightStickX() {
        return controller.getRawAxis(4);
      }
    
      public double getControllerRightStickY() {
        return controller.getRawAxis(5);
      }
    
      public double getControllerLTrigger() {
        return controller.getRawAxis(2);
      }
    
      public double getControllerRTrigger() {
        return controller.getRawAxis(3);
      }
    
      public boolean getControllerLBumperPressed(){
        return controller.getRawButtonPressed(5);
      }
    
      public boolean getControllerLBumper(){
        return controller.getRawButton(5);
      }
    
      public boolean getControllerRBumperPressed(){
        return controller.getRawButtonPressed(6);
      }
    
      public boolean getControllerRBumper(){
        return controller.getRawButton(6);
      }
    
      public boolean getControllerAButtonPressed() {
        return controller.getAButtonPressed();
      }
    
      public boolean getControllerAButton() {
        return controller.getAButton();
      }
    
      public boolean getControllerBButtonPressed() {
        return controller.getBButtonPressed();
      }
    
      public boolean getControllerBButton() {
        return controller.getBButton();
      }
    
      public boolean getControllerXButtonPressed() {
        return controller.getXButtonPressed();
      }
    
      public boolean getControllerYButtonPressed() {
        return controller.getYButtonPressed();
      }
     
      public boolean getControllerStartButtonPressed() {
        return controller.getStartButtonPressed();
      }
    
      public boolean getControllerStartButton(){
        return controller.getStartButton();
      }
}
