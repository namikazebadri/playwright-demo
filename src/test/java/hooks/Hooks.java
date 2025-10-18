package hooks;

import com.microsoft.playwright.*;
import io.cucumber.java.*;
import io.qameta.allure.Allure;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.microsoft.playwright.*;
import io.cucumber.java.*;

import java.io.*;
import java.nio.file.*;

public class Hooks {
    private static Playwright playwright;
    private static Browser browser;
    private static final Path VIDEO_DIR = Paths.get("target/videos");

    // Instance fields per scenario
    public BrowserContext context;
    public Page page;

    public Hooks() {
        HooksProvider.currentHooks = this;
    }

    @Before
    public void before(Scenario scenario) {
        try {
            if (playwright == null) {
                synchronized (Hooks.class) {
                    if (playwright == null) {
                        playwright = Playwright.create();
                        browser = playwright.chromium().launch(
                                new BrowserType.LaunchOptions().setHeadless(true)
                        );
                    }
                }
            }

            Files.createDirectories(VIDEO_DIR);

            // 🎥 start recording right from scenario start
            context = browser.newContext(new Browser.NewContextOptions()
                    .setRecordVideoDir(VIDEO_DIR)
                    .setRecordVideoSize(1920, 1080));

            page = context.newPage();

            System.out.println("[Hooks] Started recording for: " + scenario.getName());
        } catch (IOException e) {
            throw new RuntimeException("Failed to create video dir", e);
        }
    }

    @After
    public void after(Scenario scenario) {
        try {
            Path videoPath = null;

            // Close page first to finalize video
            if (page != null) {
                videoPath = page.video() != null ? page.video().path() : null;
                page.close();
            }

            context.close();

            if (scenario.isFailed() && videoPath != null && Files.exists(videoPath)) {
                try (InputStream is = Files.newInputStream(videoPath)) {
                    Allure.addAttachment("Screen Recording - " + scenario.getName(),
                            "video/webm", is, "webm");
                    System.out.println("[Hooks] Attached video to Allure for: " + scenario.getName());
                }
            }

            if (!scenario.isFailed() && videoPath != null && Files.exists(videoPath)) {
                Files.deleteIfExists(videoPath);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
