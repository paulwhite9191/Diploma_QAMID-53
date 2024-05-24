package ru.iteco.fmhandroid.ui.tests;

import static ru.iteco.fmhandroid.ui.activity.DataHelper.Rand.randomCategory;
import static ru.iteco.fmhandroid.ui.activity.DataHelper.getCurrentDate;
import static ru.iteco.fmhandroid.ui.activity.DataHelper.getCurrentTime;

import android.view.View;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.rules.ScreenshotRule;
import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Feature;
import io.qameta.allure.kotlin.Story;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.steps.AuthorizationSteps;
import ru.iteco.fmhandroid.ui.steps.ControlPanelSteps;
import ru.iteco.fmhandroid.ui.steps.CreateNewsSteps;
import ru.iteco.fmhandroid.ui.steps.DownloadSteps;
import ru.iteco.fmhandroid.ui.steps.MainSteps;

@RunWith(AllureAndroidJUnit4.class)
public class CreateNewsTest {
    @Rule
    public ActivityScenarioRule<AppActivity> activityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);
    @Rule
    public ScreenshotRule screenshotRule = new ScreenshotRule(ScreenshotRule.Mode.FAILURE,
            String.valueOf(System.currentTimeMillis()));
    DownloadSteps downloadSteps = new DownloadSteps();
    MainSteps mainSteps = new MainSteps();
    AuthorizationSteps authorizationSteps = new AuthorizationSteps();
    ControlPanelSteps controlPanelSteps = new ControlPanelSteps();
    CreateNewsSteps createNewsSteps = new CreateNewsSteps();
    private View decorView;

    @Before
    public void setUp() {
        downloadSteps.appDownload();
        try {
            mainSteps.mainScreenLoad();
        } catch (Exception e) {
            authorizationSteps.validLogIn();
            mainSteps.mainScreenLoad();
        }
        activityScenarioRule.getScenario().onActivity(activity -> decorView = activity.getWindow().getDecorView());
    }

    @Test
    @Feature(value = "Тесты раздела Новости")
    @Story("Переход к созданию новости и наличие всех элементов")
    public void shouldOpenCreateNews() {
        mainSteps.openNewsPage();
        controlPanelSteps.openControlPanelPage();
        controlPanelSteps.openCreateNewsButton();
        createNewsSteps.checkThatCreateNewsPageContentIsFull();
    }

    @Test
    @Feature(value = "Тесты раздела Новости")
    @Story("Cоздание новости с пустыми данными")
    public void shouldCreateEmptyNews() {

        mainSteps.openNewsPage();
        controlPanelSteps.openControlPanelPage();
        controlPanelSteps.openCreateNewsButton();
        createNewsSteps.clickSaveButton();


        createNewsSteps.checkToastMessageText("Заполните пустые поля", decorView);
    }

    @Test
    @Feature(value = "Тесты раздела Новости")
    @Story("Cоздание новости с валидными данными")
    public void shouldCreateNewsValid() {

        String publicationDate = getCurrentDate();
        String publicationTime = getCurrentTime();
        String title = "Новость тест";
        String description = "Описание новости тест";

        mainSteps.openNewsPage();
        controlPanelSteps.openControlPanelPage();
        controlPanelSteps.openCreateNewsButton();


        createNewsSteps.createNews(randomCategory(), title, publicationDate,
                publicationTime, description);
        createNewsSteps.clickSaveButton();


        controlPanelSteps.checkIfNewsWithTitle(title);
    }

    @Test
    @Feature(value = "Тесты раздела Новости")
    @Story("Отмена создания новости")
    public void shouldCancelCreateNews() {
        mainSteps.openNewsPage();
        controlPanelSteps.openControlPanelPage();
        controlPanelSteps.openCreateNewsButton();


        createNewsSteps.clickCancelButton();
        createNewsSteps.clickOKButton();
        controlPanelSteps.checkThatControlPanelContentIsFull();
    }
}

