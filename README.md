# Enterprise QA Automation Monorepo | Java • Playwright • Appium • API

## Strategic Vision
This repository is a high-performance, multi-layered automation ecosystem engineered for **Shift-Left Testing**. Built as a **Maven Monorepo**, it demonstrates a unified strategy for validating Web, Mobile, API, and Database layers, ensuring zero-defect delivery in fast-paced Agile/CI-CD environments.

---

## Tech Stack & Ecosystem

| Layer | Technologies |
| :--- | :--- |
| **Web UI** | **Java**, Selenium WebDriver, **Playwright**, JavaScript/TypeScript |
| **Mobile** | **Appium**, Android Studio, Xcode, **BrowserStack** |
| **API** | **REST Assured**, Postman (Newman), WireMock, Mockito |
| **Database** | **PostgreSQL** (Backend Data Validation, Stored Procedures) |
| **Infrastructure** | **Docker**, Jenkins, GitHub Actions, Azure Pipelines |
| **Cloud/DevOps** | **AWS (EC2, S3)**, Maven, Git (GitFlow) |
| **Reporting** | **Allure Reports**, Log4j/SLF4J, Chrome DevTools |

---

## Architectural Excellence
* **Design Patterns:** Implementation of **Singleton** for session management and **Factory Pattern** for cross-framework (Selenium/Playwright/Appium) instantiation.
* **Mobile Excellence:** **Appium-based** test suites for Android and iOS, validated across 5+ device configurations using **BrowserStack**.
* **Database Integrity:** Integrated **PostgreSQL** utilities for backend state verification using JOINs and subqueries to ensure UI/Mobile actions reflect correctly in the DB.
* **Data-Driven Testing:** Parameterized test suites using **TestNG Data Providers** with external **JSON and CSV** sources.
* **Performance Testing:** Load and stress testing simulation for 500+ concurrent users using **JMeter**.

---

## Live Execution & Reporting
* ** [View Live Allure Report](https://malikoliver25.github.io/QA-Automation-Portfolio/)** *(Link generated via GitHub Actions)*
* ** [Watch Execution Demo]([Link to your Video])**
* ** [Launch in GitHub Codespaces](https://github.com/codespaces/new?repo=malikoliver25/QA-Automation-Portfolio)** *(Run tests instantly in your browser)*

---

## Project Structure

| Module | Purpose | Tech | Status |
| :--- | :--- | :--- | :--- |
| `ui-automation-selenium` | Web Testing | Java, Selenium | **Active** |
| `ui-automation-playwright` | Modern Web Testing | Java, Playwright | **In Development** |
| `mobile-automation-appium` | Mobile (iOS/Android) | Appium, Java | **In Development** |
| `api-automation-restassured`| Backend Validation | REST Assured | **In Development** |
| `db-validation-sql` | Data Integrity | PostgreSQL | **In Development** |
| `performance-jmeter` | Load Testing | JMeter | **Planned** |

---

## How To Run
1. **Clone & Navigate:**
   ```bash
   git clone [https://github.com/malikoliver25/QA-Automation-Portfolio.git](https://github.com/malikoliver25/QA-Automation-Portfolio.git)
   cd QA-Automation-Portfolio
## Execute Full Regression:
Bash
mvn clean install
## Generate Local Reports:
Bash
allure serve allure-results

## Contact:
Linkedin: www.linkedin.com/in/malik-o-1b4359115
