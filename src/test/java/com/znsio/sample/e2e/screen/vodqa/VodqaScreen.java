package com.znsio.sample.e2e.screen.vodqa;

import com.znsio.sample.e2e.screen.android.vodqa.VodqaScreenAndroid;
import com.znsio.teswiz.entities.Platform;
import com.znsio.teswiz.runner.Driver;
import com.znsio.teswiz.runner.Drivers;
import com.znsio.teswiz.runner.Runner;
import com.znsio.teswiz.runner.Visual;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.log4j.Logger;

public abstract class VodqaScreen {

    private static final String SCREEN_NAME = VodqaScreen.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);

    public static VodqaScreen get() {
        Driver driver = Drivers.getDriverForCurrentUser(Thread.currentThread().getId());
        Platform platform = Runner.fetchPlatform(Thread.currentThread().getId());
        LOGGER.info(SCREEN_NAME + ": Driver type: " + driver.getType() + ": Platform: " + platform);
        Visual visually = Drivers.getVisualDriverForCurrentUser(Thread.currentThread().getId());

        switch (platform) {
            case android:
                return new VodqaScreenAndroid(driver, visually);
        }
        throw new NotImplementedException(
                SCREEN_NAME + " is not implemented in " + Runner.getPlatform());
    }

    public abstract VodqaScreen login();

    public abstract VodqaScreen putAppInTheBackground(int time);

    public abstract boolean isAppWorkingInBackground();

    public abstract WebViewScreen enterIntoNewsWebViewSection();

    public abstract NativeViewScreen enterIntoNativeViewSection();

    public abstract VodqaScreen scrollFromOneElementPointToAnother();

    public abstract boolean isElementWithTextVisible(String elementText);

    public abstract VodqaScreen tapInTheMiddle();

    public abstract boolean isPreviousPageHeadingVisible(String pageHeading);

    public abstract VodqaScreen openVerticalSwipingScreen();

    public abstract VodqaScreen scrollDownByScreenSize();

    public abstract VodqaScreen selectScreen(String screenName);

    public abstract boolean isSwipeSuccessful(String tileNumber);

    public abstract VodqaScreen swipeByPassingPercentageAttributes(int atPercentScreenHeight, int fromPercentScreenWidth, int toPercentScreenWidth);

    public abstract VodqaScreen scrollVerticallyByPercentage(int fromPercentHeight, int toPercentHeight, int percentWidth);

    public abstract VodqaScreen longPressOnElement();

    public abstract boolean isLongPressedPopupVisible();

    public abstract VodqaScreen doubleTapOnElement();

    public abstract boolean isDoubleTapSuccessful();

    public abstract VodqaScreen multiTouchOnElements();

    public abstract float getSliderValue();

    public abstract DragAndDropScreen openDragAndDropScreen();
}
