# Planit Automation Assessment – Selenium + Maven + TestNG

End‑to‑end UI tests for **Jupiter Toys** demo (http://jupiter.cloud.planittesting.com).  
Includes: **Page Object Model**, **ExtentReports HTML** (with inline screenshots on failure), and **GitHub Actions** CI.

## Implemented Scenarios
1) **Contact Form – Errors**  
2) **Contact Form – Success** (runs 5 times)  
3) **Shop/Cart – Prices, Subtotals, and Total**

## Run locally
```bash
mvn clean test -Dheadless=true
```
- HTML report: `target/ExtentReport.html`  
- Screenshots (on failure): `target/screenshots/`

## Repo Structure
```
src/
  main/java/com/planit/pages/        # Page Objects (Home, Contact, Shop, Cart)
  main/java/com/planit/utils/        # DriverFactory, BasePage, ExtentReportListener
  test/java/com/planit/tests/        # TestNG test classes
  test/resources/testng.xml          # Suite (registers Extent listener)
.github/workflows/ci.yml             # GitHub Actions workflow
pom.xml                               # Maven config (no Lombok)
```

## CI (GitHub Actions)
- Auto‑installs Chrome + Java 17
- Purges any cached Lombok jars (safety)
- Runs tests headless
- Uploads `ExtentReport.html` + `screenshots/` as artifact

## Jenkins
`Jenkinsfile` provided for a simple pipeline.

> This project does **not** use Lombok.
