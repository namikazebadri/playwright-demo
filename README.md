# playwright-allure-demo

Playwright Java + Cucumber + Allure demo project.

Features:
- Page Object Model
- Gherkin (Cucumber)
- Parallel per-feature execution (maven-surefire parallel=classes)
- Screen recording *only on failure* attached to Allure report
- Allure report integration

Run:

```bash
mvn exec:java -e -Dexec.mainClass=com.microsoft.playwright.CLI -Dexec.args="install"

mvn clean test
# then generate and serve Allure report (requires allure commandline installed)
allure serve target/allure-results
allure generate target/allure-results --clean -o target/allure-report
```

Notes:
- This project provides the source files and configuration. Your local environment must have:
  - Java (JDK 21+)
  - Maven
  - Playwright browser binaries (run `mvn -Dplaywrightinstall=true test` or use Playwright CLI)
  - Allure commandline (optional, for `allure serve`)
