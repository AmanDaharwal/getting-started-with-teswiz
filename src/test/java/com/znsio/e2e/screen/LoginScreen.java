package com.znsio.e2e.screen;

import com.znsio.e2e.entities.*;
import com.znsio.e2e.runner.*;
import com.znsio.e2e.screen.android.*;
import com.znsio.e2e.screen.web.*;
import com.znsio.e2e.tools.*;
import org.apache.commons.lang3.*;
import org.apache.log4j.*;

import static com.znsio.e2e.runner.Runner.*;

public abstract class LoginScreen {
    private static final String SCREEN_NAME = LoginScreen.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(LoginScreen.class.getName());

    public static LoginScreen get() {
        Driver driver = fetchDriver(Thread.currentThread().getId());
        Platform platform = Runner.fetchPlatform(Thread.currentThread().getId());
        LOGGER.info(SCREEN_NAME + ": Driver type: " + driver.getType() + ": Platform: " + platform);
        Visual visually = fetchEyes(Thread.currentThread().getId());

        switch (platform) {
            case android:
                return new LoginScreenAndroid(driver, visually);
            case web:
                return new LoginScreenWeb(driver, visually);
        }
        throw new NotImplementedException(SCREEN_NAME + " is not implemented in " + Runner.platform);
    }

    public abstract LoginScreen enterLoginDetails(String username, String password);

    public abstract LoginScreen login();

    public abstract String getInvalidLoginError();

    public abstract LoginScreen dismissAlert();
}