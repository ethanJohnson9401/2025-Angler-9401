// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix6.configs.CurrentLimitsConfigs;
import com.ctre.phoenix6.configs.MotionMagicConfigs;
import com.ctre.phoenix6.configs.MotorOutputConfigs;
import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static class OperatorConstants {
    public static final int kDriverControllerPort = 0;
  }


  public static class CoralIntakeWristConfig extends TalonFXConfiguration{

    /**
     * 
     * 
     * @param statorLimit Stator current limit of motor
     * @param supplyLimit Supply current limit of motor
     * @param acc Accerlation of MotionMagic in rp/s
     * @param velo Accerlation of MotionMagic in rps
     */
    public CoralIntakeWristConfig(double statorLimit, double supplyLimit, double acc, double velo, InvertedValue inverted, NeutralModeValue stopType){
      this.CurrentLimits = new CoralIntakeWristCurrentConfig(statorLimit,supplyLimit);
      this.MotionMagic = new CoralIntakeWristMotionMagicConfig(acc, velo);
      this.MotorOutput = new CoralIntakeWristMotorOutputConfig(inverted, stopType);
      this.Slot0 = new CoralIntakeWrist_PID_Config();
    }

    private static class CoralIntakeWristCurrentConfig extends CurrentLimitsConfigs{

      private CoralIntakeWristCurrentConfig(double statorLimit,double supplyLimit){
        this.StatorCurrentLimit = statorLimit;
        this.StatorCurrentLimitEnable = true;
        
        this.SupplyCurrentLimit = supplyLimit;
        this.SupplyCurrentLimitEnable = true;
      }

    }

    private static class CoralIntakeWristMotionMagicConfig extends MotionMagicConfigs{

      private CoralIntakeWristMotionMagicConfig(double acc, double velo){
        this.MotionMagicAcceleration = acc;
        this.MotionMagicCruiseVelocity = velo;
      }
    }

    private static class CoralIntakeWristMotorOutputConfig extends MotorOutputConfigs{

      private CoralIntakeWristMotorOutputConfig(InvertedValue inverted, NeutralModeValue stopType){
        this.Inverted = inverted;
        this.NeutralMode = stopType;
      }
    }

    private static class CoralIntakeWrist_PID_Config extends Slot0Configs{
      
      private CoralIntakeWrist_PID_Config(){
        this.kS = 0.25; // Add 0.25 V output to overcome static friction
        this.kV = 0.12; // A velocity target of 1 rps results in 0.12 V output
        this.kA = 0.01; // An acceleration of 1 rps/s requires 0.01 V output
        this.kP = 4.8; // A position error of 2.5 rotations results in 12 V output
        this.kI = 0; // no output for integrated error
        this.kD = 0.1; // A velocity error of 1 rps results in 0.1 V output
      }
    }

  }

  public static class CoralIntakeConfig extends TalonFXConfiguration{

    /**
     * Makes new object of Coral Intake Config
     * 
     * @param statorLimit Stator current limit of motor
     * @param supplyLimit Supply current limit of motor
     * @param acc Target Accerlation of MotionMagic in rps/s
     * @param velo Target jerk of MotionMagic in rps/s/s
     */
    public CoralIntakeConfig(double statorLimit, double supplyLimit, double acc, double jerk, InvertedValue inverted, NeutralModeValue stopType){
      this.CurrentLimits = new CoralIntakeCurrentConfig(statorLimit,supplyLimit);
      this.MotionMagic = new CoralIntakeMotionMagicConfig(acc, jerk);
      this.MotorOutput = new CoralIntakeMotorOutputConfig(inverted, stopType);
      this.Slot0 = new CoralIntake_PID_Config();
    }

    private static class CoralIntakeCurrentConfig extends CurrentLimitsConfigs{

      private CoralIntakeCurrentConfig(double statorLimit,double supplyLimit){
        this.StatorCurrentLimit = statorLimit;
        this.StatorCurrentLimitEnable = true;
        
        this.SupplyCurrentLimit = supplyLimit;
        this.SupplyCurrentLimitEnable = true;
      }

    }

    private static class CoralIntakeMotionMagicConfig extends MotionMagicConfigs{

      private CoralIntakeMotionMagicConfig(double acc, double jerk){
        this.MotionMagicAcceleration = acc;
        this.MotionMagicJerk = jerk;
      }
    }

    private static class CoralIntakeMotorOutputConfig extends MotorOutputConfigs{

      private CoralIntakeMotorOutputConfig(InvertedValue inverted, NeutralModeValue stopType){
        this.Inverted = inverted;
        this.NeutralMode = stopType;
      }
    }

    private static class CoralIntake_PID_Config extends Slot0Configs{
      
      private CoralIntake_PID_Config(){
        this.kS = 0.25; // Add 0.25 V output to overcome static friction
        this.kV = 0.12; // A velocity target of 1 rps results in 0.12 V output
        this.kA = 0.01; // An acceleration of 1 rps/s requires 0.01 V output
        this.kP = 0.11; // An error of 1 rps results in 0.11 V output
        this.kI = 0; // no output for integrated error
        this.kD = 0; // no output for error derivative
      }
    }

  }
}
