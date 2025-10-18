package steps;

import com.microsoft.playwright.Page;
import hooks.HooksProvider;
import io.cucumber.java.en.*;
import pages.BeautyhaulHomePage;
import static org.junit.Assert.*;

public class BeautyhaulHeaderSteps {
    Page page;
    BeautyhaulHomePage beautyhaulHomePage;

    @Given("I am on Beautyhaul Homepage")
    public void i_am_on_beautyhaul_homepage() {
        page = HooksProvider.getPage();

        beautyhaulHomePage = new BeautyhaulHomePage(page);
        beautyhaulHomePage.navigate();
    }

    @Then("I should see logo")
    public void i_should_see_logo() {
        assertTrue("Expected results not visible", beautyhaulHomePage.isLogoVisible());
    }

    @Then("I should see logo false")
    public void i_should_see_logo_false() {
        assertTrue("Expected results not visible", beautyhaulHomePage.isLogoVisibleFalse());
    }
}
