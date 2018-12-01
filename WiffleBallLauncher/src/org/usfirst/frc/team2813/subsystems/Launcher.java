package org.usfirst.frc.team2813.subsystems;

import org.usfirst.frc.team2813.commands.Shoot;
import org.usfirst.frc.team2813.robot.CreateTalonSRX;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Launcher extends Subsystem {
	
	private final static int LAUNCHER_MOTOR_1_ID = 3;
	private final static int LAUNCHER_MOTOR_2_ID = 4;
	private final static int ANGLE_MOTOR_ID = 5;
	
	private final static VictorSPX launcherMotor1 = new VictorSPX(LAUNCHER_MOTOR_1_ID);
    private final static VictorSPX launcherMotor2 = new VictorSPX(LAUNCHER_MOTOR_2_ID);

	public final static WPI_TalonSRX angleMotor = CreateTalonSRX.createDefaultWPITalon(ANGLE_MOTOR_ID);
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new Shoot());
    }
    
    public static void shoot() {
    	launcherMotor1.set(ControlMode.PercentOutput, 1.0);
    	launcherMotor2.set(ControlMode.PercentOutput, 1.0);
    }
}

