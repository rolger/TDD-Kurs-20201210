package testdoubles;

public class TemperaturControlUnit {

	private final double LowTemperatureTreshold = 18;
	private final double HighTemperatureTreshold = 22;

	private Action action;

	public Action getAction() {
		return action;
	}

	/*
	    A stub is required.
	 */
	public Action calculateAction(Sensor sensor) {
		Action resultAction = Action.KEEP;
		if (sensor.getTemperature() > HighTemperatureTreshold) {
			resultAction =  Action.COOL;
		} else if (sensor.getTemperature() < LowTemperatureTreshold) {
			resultAction =  Action.HEAT;
		}
		return resultAction;
	}

    /*
        A stub is required.
     */
	public void calculateAndSetAction(Sensor sensor) {
		action = Action.KEEP;
		if (sensor.getTemperature() > HighTemperatureTreshold) {
			action= Action.COOL;
		} else if (sensor.getTemperature() < LowTemperatureTreshold) {
			action = Action.HEAT;
		}
	}

	/*
	    A stub and a mock is required.
	 */
    public void calculateAndSetAction(Sensor sensor, AirCondition airCondition) {
		Action tempAction = Action.KEEP;
		if (sensor.getTemperature() > HighTemperatureTreshold) {
			tempAction= Action.COOL;
		} else if (sensor.getTemperature() < LowTemperatureTreshold) {
			tempAction = Action.HEAT;
		}

		airCondition.adjustTemperature(tempAction);
	}

}
