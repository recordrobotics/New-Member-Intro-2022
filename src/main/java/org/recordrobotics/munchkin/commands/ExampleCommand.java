// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package org.recordrobotics.munchkin.commands;

import org.recordrobotics.munchkin.subsystems.ExampleSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;
import org.recordrobotics.munchkin.control.*;

/** An example command that uses an example subsystem. */
public class ExampleCommand extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final ExampleSubsystem m_subsystem;
  private final IControlInput m_control;

  private double _time;
  private double _controlScaleLong;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public ExampleCommand(ExampleSubsystem subsystem, IControlInput controlInput) {
    System.out.println("Command Initialized");
    if (subsystem == null) {
      throw new IllegalArgumentException("Subsystem is null");
    }
    if (controlInput == null) {
      throw new IllegalArgumentException("Controller is null");
    }

    m_subsystem = subsystem;
    m_control = controlInput;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_subsystem);
  }
  
	public double calcNextCtrlScale(double currControlScale, double input, double time) {
		double nextCtrlScale = 0.0;
    int timeScale = 25;
    
    if(input == 0.0) {
      timeScale = 15;
    }
    nextCtrlScale = currControlScale + (input - currControlScale)/timeScale;
    if(Math.abs(input - currControlScale) <= 0.1) {
      nextCtrlScale = input;
    }
		if(nextCtrlScale < -1.0 || nextCtrlScale > 1.0) {
			nextCtrlScale = nextCtrlScale/Math.abs(nextCtrlScale);
		}
		return nextCtrlScale;
	}

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    _controlScaleLong = calcNextCtrlScale(_controlScaleLong, m_control.getDriveLong(), _time);
    m_subsystem.spin(_controlScaleLong * 0.5);
  }





  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
