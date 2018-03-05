package org.usfirst.frc.team2974.robot.smartdashboard;

import java.util.function.Supplier;

public class DebugSmartDashboardProperty<T> extends SmartDashboardProperty<T> {

	/**
	 * This creates a SmartDashboard value to show only when SmartDashboardManager.isDebug is true.
	 */
	public DebugSmartDashboardProperty(String key, T defaultValue, Supplier<T> valueSupplier) {
		super(key, defaultValue, valueSupplier);
	}

	@Override
	protected final void updateSmartDashboard() {
//        if (IS_DEBUG) {
		super.updateSmartDashboard();
//        } else if (SmartDashboardManager.containsBind(getKey())) {
//             why dost thou do this to me SmartDashboard
//            SmartDashboardManager.removeBind(getKey()); // TODO: fix so it actually deletes
//        }
	}
}
