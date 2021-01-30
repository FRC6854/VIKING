package viking.led;

public interface LEDMappings {
    // Lower Conveyor Forward
    final char LCF = 'a';

    // Lower Conveyor Backward
    final char LCB = 'b';

    // Upper Conveyor Forward
    final char UCF = 'c';

    // Upper Conveyor Backward
    final char UCB = 'd';

    // Conveyors
    final char BOTH_FORWARD = 'e';
    final char BOTH_BACKWARD = 'f';

    // Vision
    final char NO_VISION = 'g';
    final char VISION = 'h';

    // Climbing
    final char CLIMB_ACTIVE = 'i';
    final char WINCH_ACTIVE = 'j';

    final char LOW_VOLTAGE = 'k';

    final char DEFAULT = '1';
}
