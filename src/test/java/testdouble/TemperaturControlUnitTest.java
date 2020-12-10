package testdouble;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import testdoubles.Action;
import testdoubles.AirCondition;
import testdoubles.Sensor;
import testdoubles.TemperaturControlUnit;

@ExtendWith(MockitoExtension.class)
public class TemperaturControlUnitTest {
	@Mock
	private Sensor sensor;
	@Mock
	private AirCondition airCondition;

	@Test
	public void assert_retrun_value_when_temperature_is_in_the_middle_no_heating_or_cooling_required() throws Exception {
		TemperaturControlUnit controlUnit = new TemperaturControlUnit();

		Mockito.when(sensor.getTemperature()).thenReturn(19.0);
		Action result = controlUnit.calculateAction(sensor);

		Assertions.assertThat(result).isEqualTo(Action.KEEP);
	}

	@Test
	public void assert_state_when_temperature_is_in_the_middle_no_heating_or_cooling_required() throws Exception {
		TemperaturControlUnit controlUnit = new TemperaturControlUnit();

		Mockito.when(sensor.getTemperature()).thenReturn(19.0);
		controlUnit.calculateAndSetAction(sensor);

		Assertions.assertThat(controlUnit.getAction()).isEqualTo(Action.KEEP);
	}

	@Test
	public void verify_call_when_temperature_is_in_the_middle_no_heating_or_cooling_required()
			throws Exception {
		TemperaturControlUnit controlUnit = new TemperaturControlUnit();

		Mockito.when(sensor.getTemperature()).thenReturn(19.0);
		controlUnit.calculateAndSetAction(sensor, airCondition);

		Mockito.verify(airCondition).adjustTemperature(Action.KEEP);
	}

}
