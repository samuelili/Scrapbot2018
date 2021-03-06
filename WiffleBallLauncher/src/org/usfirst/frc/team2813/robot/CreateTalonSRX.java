package org.usfirst.frc.team2813.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
public class CreateTalonSRX {	
	private static final int kTimeoutMs = 100;
	public static class Configuration {
		public NeutralMode NEUTRAL_MODE = NeutralMode.Brake;
		public boolean ENABLE_CURRENT_LIMIT = false;
        public boolean ENABLE_SOFT_LIMIT = false;
        public boolean ENABLE_LIMIT_SWITCH = false;
        public int FORWARD_SOFT_LIMIT = 0;
        public int REVERSE_SOFT_LIMIT = 0;
        public double OPEN_LOOP_RAMP_RATE = 0.0;//Time to reach set speed
        public double CLOSED_LOOP_RAMP_RATE = 0.0;
        public boolean INVERTED = false;
        public boolean SENSOR_PHASE = false;
	}
	
	private static final Configuration kDefaultConfiguration = new Configuration();

	/** 
	 * Creates a WPI_TalonSRX and slaves a VictorSPX with default configuration
	 * @param talonID
	 * @param victorID
	 * @return A WPI_TalonSRX
	 */
	public static WPI_TalonSRX createDefaultWPITalonVictorSlave(int talonID, int victorID) {
		final WPI_TalonSRX talon = createWPITalon(talonID, kDefaultConfiguration);
		final VictorSPX victor = createPermanentSlaveVictor(victorID, talon);
		return talon;
	}
	
	/** 
	 * Creates a TalonSRX and slaves a VictorSPX with default configuration
	 * @param talonID
	 * @param victorID
	 * @return A TalonSRX
	 */
	public static TalonSRX createDefaultTalonVictorSlave(int talonID, int victorID) {
		final TalonSRX talon = createTalon(talonID, kDefaultConfiguration);
		final VictorSPX victor = createPermanentSlaveVictor(victorID, talon);
		return talon;
	}
	
	public static VictorSPX createPermanentSlaveVictor(int id, TalonSRX talon) {
		final VictorSPX victor = new VictorSPX(id);
		victor.follow(talon);
		return victor;
	}
	
	public static WPI_TalonSRX createWPITalon(int id, Configuration config) {
		WPI_TalonSRX talon = new WPI_TalonSRX(id);
		talon.set(ControlMode.PercentOutput, 0.0);
		
		talon.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen, kTimeoutMs);
		talon.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen, kTimeoutMs);
		talon.overrideLimitSwitchesEnable(config.ENABLE_SOFT_LIMIT);
		
		talon.configPeakOutputForward(1.0, kTimeoutMs);
        talon.configPeakOutputReverse(-1.0, kTimeoutMs);
        
        talon.setNeutralMode(config.NEUTRAL_MODE);
        
        talon.configForwardSoftLimitThreshold(config.FORWARD_SOFT_LIMIT, kTimeoutMs);
        talon.configForwardSoftLimitEnable(config.ENABLE_SOFT_LIMIT, kTimeoutMs);

        talon.configReverseSoftLimitThreshold(config.REVERSE_SOFT_LIMIT, kTimeoutMs);
        talon.configReverseSoftLimitEnable(config.ENABLE_SOFT_LIMIT, kTimeoutMs);
        talon.overrideSoftLimitsEnable(config.ENABLE_SOFT_LIMIT);
        
        talon.setInverted(config.INVERTED);
        talon.setSensorPhase(config.SENSOR_PHASE);
        
        talon.configOpenloopRamp(config.OPEN_LOOP_RAMP_RATE, kTimeoutMs);
        talon.configClosedloopRamp(config.CLOSED_LOOP_RAMP_RATE, kTimeoutMs);
        
        talon.selectProfileSlot(0, 0);
        
        talon.enableCurrentLimit(config.ENABLE_CURRENT_LIMIT);
        
		return talon;
	}
	
	public static TalonSRX createTalon(int id, Configuration config) {
		TalonSRX talon = new TalonSRX(id);
		talon.set(ControlMode.PercentOutput, 0.0);
		
		talon.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen, kTimeoutMs);
		talon.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen, kTimeoutMs);
		talon.overrideLimitSwitchesEnable(config.ENABLE_LIMIT_SWITCH);
		
		talon.configPeakOutputForward(1.0, kTimeoutMs);
        talon.configPeakOutputReverse(-1.0, kTimeoutMs);
        
        talon.setNeutralMode(config.NEUTRAL_MODE);
        
        talon.configForwardSoftLimitThreshold(config.FORWARD_SOFT_LIMIT, kTimeoutMs);
        talon.configForwardSoftLimitEnable(config.ENABLE_SOFT_LIMIT, kTimeoutMs);

        talon.configReverseSoftLimitThreshold(config.REVERSE_SOFT_LIMIT, kTimeoutMs);
        talon.configReverseSoftLimitEnable(config.ENABLE_SOFT_LIMIT, kTimeoutMs);
        talon.overrideSoftLimitsEnable(config.ENABLE_SOFT_LIMIT);
        
        talon.setInverted(config.INVERTED);
        talon.setSensorPhase(config.SENSOR_PHASE);
        
        talon.configOpenloopRamp(config.OPEN_LOOP_RAMP_RATE, kTimeoutMs);
        talon.configClosedloopRamp(config.CLOSED_LOOP_RAMP_RATE, kTimeoutMs);
        
        talon.selectProfileSlot(0, 0);
        
        talon.enableCurrentLimit(config.ENABLE_CURRENT_LIMIT);
        
		return talon;
	}
}