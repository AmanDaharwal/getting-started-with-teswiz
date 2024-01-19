package com.znsio.sample.e2e.screen.indigo;

import com.znsio.teswiz.entities.Platform;
import com.znsio.teswiz.runner.Drivers;
import com.znsio.teswiz.runner.Runner;
import com.znsio.teswiz.runner.Driver;
import com.znsio.teswiz.runner.Visual;
import com.znsio.sample.e2e.screen.android.indigo.IndigoHomeScreenAndroid;
import com.znsio.sample.e2e.screen.web.indigo.IndigoHomeScreenWeb;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public abstract class IndigoHomeScreen {
    private static final String SCREEN_NAME = IndigoHomeScreen.class.getSimpleName();
    private static final Logger LOGGER = LogManager.getLogger(SCREEN_NAME);

    public static IndigoHomeScreen get() {
        Driver driver = Drivers.getDriverForCurrentUser(Thread.currentThread().getId());
        Platform platform = Runner.fetchPlatform(Thread.currentThread().getId());
        LOGGER.info(SCREEN_NAME + ": Driver type: " + driver.getType() + ": Platform: " + platform);
        Visual visually = Drivers.getVisualDriverForCurrentUser(Thread.currentThread().getId());

        switch(platform) {
            case android:
                return new IndigoHomeScreenAndroid(driver, visually);
            case web:
                return new IndigoHomeScreenWeb(driver, visually);
        }
        throw new NotImplementedException(
                SCREEN_NAME + " is not implemented in " + Runner.getPlatform());
    }

    public abstract IndigoHomeScreen selectFrom(String from);

    public abstract IndigoHomeScreen selectTo(String destination);

    public abstract IndigoHomeScreen selectNumberOfAdultPassengers(int numberOfAdults);

    public abstract IndigoHomeScreen selectJourneyType(String journeyType);

    public abstract IndigoFlightSearchResultsScreen searchFlightOptions();

    public abstract IndigoGiftVouchersScreen selectGiftVouchers();
}
