package pages;

import com.microsoft.playwright.*;

public record BeautyhaulHomePage(Page page) {
    public void navigate() {
        page.navigate("https://www.beautyhaul.com/");
    }

    public boolean isLogoVisible() {
        Locator btn = page.locator("xpath=/html/body/header/div[2]/div[1]/div[1]/div[2]/a[2]/img");

        return btn.isVisible();
    }

    public boolean isLogoVisibleFalse() {
        Locator btn = page.locator("xpath=/html/body/header/div[2]/div[1]/div[1]/div[2]/a[3]/img");

        return btn.isVisible();
    }
}
