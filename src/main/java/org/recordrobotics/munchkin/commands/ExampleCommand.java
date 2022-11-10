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

  private final double SPIN_SPEED = 0.3;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public ExampleCommand(ExampleSubsystem subsystem, IControlInput controlInput) {
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

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    System.out.println("It works!");
    switch (m_control.getFlywheel()) {
      case OFF:
        m_subsystem.spin(0);
        return;
      case LOW:
        m_subsystem.spin(SPIN_SPEED/2);
        break;
      case HIGH:
        m_subsystem.spin(SPIN_SPEED);
        break;
    }
  }





  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
