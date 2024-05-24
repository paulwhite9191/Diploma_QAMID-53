package ru.iteco.fmhandroid.ui.tests;

import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static org.hamcrest.CoreMatchers.not;

import android.view.View;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.rules.ScreenshotRule;
import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Feature;
import io.qameta.allure.kotlin.Story;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.page.AuthorizationPage;
import ru.iteco.fmhandroid.ui.page.MainPage;
import ru.iteco.fmhandroid.ui.steps.AuthorizationSteps;
import ru.iteco.fmhandroid.ui.steps.DownloadSteps;
import ru.iteco.fmhandroid.ui.steps.MainSteps;

@RunWith(AllureAndroidJUnit4.class)
public class AuthorizationTest {
    @Rule
    public ActivityScenarioRule<AppActivity> activityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);
    @Rule
    public ScreenshotRule screenshotRule = new ScreenshotRule(ScreenshotRule.Mode.FAILURE,
            String.valueOf(System.currentTimeMillis()));
    DownloadSteps downloadSteps = new DownloadSteps();
    AuthorizationPage authorizationPage = new AuthorizationPage();
    AuthorizationSteps authorizationSteps = new AuthorizationSteps();
    MainPage mainPage = new MainPage();
    MainSteps mainSteps = new MainSteps();
    private View decorView;

    @Before
    public void setUp() {
        downloadSteps.appDownload();
        try {
            authorizationSteps.loadAuthorizationPage();
        } catch (
                Exception e) {
            mainSteps.logOut();
            authorizationSteps.loadAuthorizationPage();
        }
        activityScenarioRule.getScenario().onActivity(activity -> decorView = activity.getWindow().getDecorView());
    }

    @After
    public void tearDown() {
        try {
            mainSteps.logOut();
        } catch (Exception ignored) {
        }
    }

    @Test
    @Feature(value = "Тесты страницы Авторизации")
    @Story("Наличие всех элементов формы авторизации")
    public void shouldBeFullContentInAboutBlock() {
        authorizationSteps.checkThatAuthorizationBlockContentIsFull();
    }

    @Test
    @Feature(value = "Тесты страницы Авторизации")
    @Story("Авторизация с валидными данными (happy path")
    public void shouldLoginByValidData() {
        authorizationSteps.validLogIn();
        mainSteps.mainScreenLoad();
        mainSteps.checkThatMainBlockContentIsFull();
    }

    @Test
    @Feature(value = "Тесты страницы Авторизации")
    @Story("Авторизация с не валидными данными (sad path)")
    public void shouldLoginByNotValidData() {
        authorizationSteps.notValidLogIn();

        authorizationSteps.checkToastMessageText("Неверный логин или пароль", decorView);

        authorizationPage.title.check(matches(isDisplayed()));
        mainPage.mainLogo.check(matches(not(isDisplayed())));
    }

    @Test
    @Feature(value = "Тесты страницы Авторизации")
    @Story("Авторизация в приложении без ввода данных")
    public void shouldLoginByEmptyData() {
        authorizationSteps.emptyLogIn();

        authorizationSteps.checkToastMessageText("Логин и пароль не могут быть пустыми", decorView);

        authorizationPage.title.check(matches(isDisplayed()));
        mainPage.mainLogo.check(matches(not(isDisplayed())));
    }

    @Test
    @Feature(value = "Тесты страницы Авторизациии")
    @Story("Выход из авторизованной учетной записи")
    public void shouldLogOut() {
        authorizationSteps.validLogIn();
        mainSteps.mainScreenLoad();
        mainSteps.logOut();
        authorizationSteps.checkThatAuthorizationBlockContentIsFull();
    }

}
