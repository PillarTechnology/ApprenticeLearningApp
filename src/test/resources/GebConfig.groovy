import static io.github.bonigarcia.wdm.DriverManagerType.CHROME;

import org.openqa.selenium.chrome.ChromeDriver
import io.github.bonigarcia.wdm.WebDriverManager;


// Location where Geb saves the screenshots and HTML dumps at the end of each test
reportsDir = 'build/test-reports'

atCheckWaiting = true

// Run tests in Chrome by default
driver = {
    WebDriverManager.getInstance(CHROME).setup();

    new ChromeDriver()
}
