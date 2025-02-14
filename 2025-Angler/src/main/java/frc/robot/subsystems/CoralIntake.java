package frc.robot.subsystems;

public class CoralIntake extends SubsystemBase{

    private TalonFX m_motor = new TalonFX(10);

    private double m_velo = 0.0;
    private boolean m_enabled = true;

    private MotionMagicVelocityVoltage m_request = new MotionMagicVelocityVoltage(m_velo);

    
    public CoralIntake(){

        m_motor.getConfigurator().apply(new TalonFXConfiguration());
        m_motor.getConfigurator().apply(new CoralIntakeConfig(60.0, 55.0, 400, 4000, InvertedValue.Clockwise_Positive, NeutralModeValue.Brake));

    }

    public void setVelo(double velo){
        m_velo = velo;
    }
    
    public Command setVeloCMD(double velo){
        return new InstantCommand(()-> setVelo(velo));
    }

    public void enable(){
        m_enabled = true;
    }

    public Command enableCMD(){
        return new InstantCommand(()-> enable());
    }

    public void enable(double velo){
        setVelo(velo);
        m_enabled = true;
    }

    public Command enableCMD(double velo){
        return new InstantCommand(()-> enable(velo));
    }

    public void disable(){
        m_enabled = false;
    }

    @Override
    public void periodic() {
        if(m_enabled){
            m_motor.setControl(m_request.withVelocity(m_velo));
        } else {
            m_motor.stopMotor();
        }

        StatusSignal<AngularVelocity> velo = m_motor.getVelocity().refresh();
        SmartDashboard.putNumber("Coral Wrist Velo.", velo.getValueAsDouble());

        StatusSignal<AngularAcceleration> acc = m_motor.getAcceleration().refresh();
        SmartDashboard.putNumber("Coral Wrist Acc.", acc.getValueAsDouble());
    }
}

