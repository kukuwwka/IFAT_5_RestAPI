package api;

import org.testng.annotations.Test;
import steps.RateStep;
import validators.RateValidators;

public class BookerTest {
    private final RateStep steps = new RateStep();
    private final RateValidators validators = new RateValidators();

    @Test
    public void checkResponse() {
        String response = steps.getResponse();

        validators.validateDateRegex(response);

        validators.validateKeys();

        validators.validateHeaders();

        validators.validateSchema();
    }
}