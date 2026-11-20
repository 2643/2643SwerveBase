// Copyright (c) 2021-2026 Littleton Robotics
// http://github.com/Mechanical-Advantage
//
// Use of this source code is governed by a BSD
// license that can be found in the LICENSE file
// at the root directory of this project.

package frc.robot.subsystems.vision;

import edu.wpi.first.apriltag.AprilTagFieldLayout;
import edu.wpi.first.apriltag.AprilTagFields;
import edu.wpi.first.math.geometry.Rotation3d;
import edu.wpi.first.math.geometry.Transform3d;
import edu.wpi.first.math.util.Units;

public class VisionConstants {
  // AprilTag layout
  public static AprilTagFieldLayout aprilTagLayout =
      AprilTagFieldLayout.loadField(AprilTagFields.kDefaultField);

  // Camera names, must match names configured on coprocessor
  public static String camera0Name = "cam1"; // Front left
  public static String camera1Name = "cam2"; // Front right
  public static String camera2Name = "cam3"; // Back right
  public static String camera3Name = "cam4"; // Back left

  // Camera mount geometry (measure on the robot and update)
  // X = forward, Y = left, Z = up, all from robot center at floor level
  private static final double camX = Units.inchesToMeters(10.875); // Module X offset
  private static final double camY = Units.inchesToMeters(10.875); // Module Y offset
  private static final double camZ = Units.inchesToMeters(6.0); // Lens height off floor
  private static final double camPitch = Units.degreesToRadians(-45.0); // Negative = tilted up

  // Robot to camera transforms
  // (Not used by Limelight, configure in web UI instead)
  // Each camera faces outward diagonally at 45 degrees from its corner
  public static Transform3d robotToCamera0 = // cam1, front left
      new Transform3d(
          camX, camY, camZ, new Rotation3d(0.0, camPitch, Units.degreesToRadians(45.0)));
  public static Transform3d robotToCamera1 = // cam2, front right
      new Transform3d(
          camX, -camY, camZ, new Rotation3d(0.0, camPitch, Units.degreesToRadians(-45.0)));
  public static Transform3d robotToCamera2 = // cam3, back right
      new Transform3d(
          -camX, -camY, camZ, new Rotation3d(0.0, camPitch, Units.degreesToRadians(-135.0)));
  public static Transform3d robotToCamera3 = // cam4, back left
      new Transform3d(
          -camX, camY, camZ, new Rotation3d(0.0, camPitch, Units.degreesToRadians(135.0)));

  // Basic filtering thresholds
  public static double maxAmbiguity = 0.3;
  public static double maxZError = 0.75;

  // Standard deviation baselines, for 1 meter distance and 1 tag
  // (Adjusted automatically based on distance and # of tags)
  public static double linearStdDevBaseline = 0.02; // Meters
  public static double angularStdDevBaseline = 0.06; // Radians

  // Standard deviation multipliers for each camera
  // (Adjust to trust some cameras more than others)
  public static double[] cameraStdDevFactors =
      new double[] {
        1.0, // cam1
        1.0, // cam2
        1.0, // cam3
        1.0 // cam4
      };

  // Multipliers to apply for MegaTag 2 observations
  public static double linearStdDevMegatag2Factor = 0.5; // More stable than full 3D solve
  public static double angularStdDevMegatag2Factor =
      Double.POSITIVE_INFINITY; // No rotation data available
}
