package ru.iteco.fmhandroid.ui.tests;

import static ru.iteco.fmhandroid.ui.activity.DataHelper.Rand.randomCategory;
import static ru.iteco.fmhandroid.ui.activity.DataHelper.getCurrentDate;
import static ru.iteco.fmhandroid.ui.activity.DataHelper.getCurrentTime;

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
import ru.iteco.fmhandroid.ui.steps.EditNewsSteps;
import ru.iteco.fmhandroid.ui.steps.MainSteps;

@RunWith(AllureAndroidJUnit4.class)
public class EditNewsTest {
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
    EditNewsSteps editNewsSteps = new EditNewsSteps();

    @Before
    public void setUp() {
        downloadSteps.appDownload();
        try {
            mainSteps.mainScreenLoad();
        } catch (Exception e) {
            authorizationSteps.validLogIn();
            mainSteps.mainScreenLoad();
        }
    }

    @Test
    @Feature(value = "Тесты раздела Новости")
    @Story("Редактирование новости")
    public void shouldEditNews() {

        String publicationDate = getCurrentDate();
        String publicationTime = getCurrentTime();

        String title = "Заголовок";
        String description = "Описание";
        String newTitle = "Заголовок отредактирован";
        String newDescription = "Описание отедактировано";

        mainSteps.openNewsPage();
        controlPanelSteps.openControlPanelPage();
        controlPanelSteps.openCreateNewsButton();
        createNewsSteps.createNews(randomCategory(), title, publicationDate,
                publicationTime, description);
        createNewsSteps.clickSaveButton();


        controlPanelSteps.clickEditNews(title);
        editNewsSteps.checkThatEditNewsPageContentIsFull();

        editNewsSteps.EditNewsFields(randomCategory(), newTitle, publicationDate,
                publicationTime, newDescription);
        editNewsSteps.changeStatus();
        editNewsSteps.clickSaveButton();


        controlPanelSteps.checkIfNewsWithTitle(newTitle);
    }

    @Test
    @Feature(value = "Тесты раздела Новости")
    @Story("Отмена редактирования новости")
    public void shouldCancelEditNews() {

        String publicationDate = getCurrentDate();
        String publicationTime = getCurrentTime();

        String title = "Заголовок Тест";
        String description = "Описание Тест";

        mainSteps.openNewsPage();
        controlPanelSteps.openControlPanelPage();
        controlPanelSteps.openCreateNewsButton();
        createNewsSteps.createNews(randomCategory(), title, publicationDate,
                publicationTime, description);
        createNewsSteps.clickSaveButton();


        controlPanelSteps.clickEditNews(title);
        editNewsSteps.checkThatEditNewsPageContentIsFull();
        editNewsSteps.changeStatus();


        editNewsSteps.clickCancelButton();
        editNewsSteps.clickOKButton();
        controlPanelSteps.checkThatControlPanelContentIsFull();
    }

}