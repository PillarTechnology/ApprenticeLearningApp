
import io.github.bonigarcia.wdm.WebDriverManager
import org.openqa.selenium.chrome.ChromeDriver
import static io.github.bonigarcia.wdm.DriverManagerType.CHROME

// Location where Geb saves the screenshots and HTML dumps at the end of each test
reportsDir = 'build/test-reports'

atCheckWaiting = true

// Run tests in Chrome by default
driver = {
    // Download and configure Marionette using https://github.com/bonigarcia/webdrivermanager
//    WebDriverManager.chromedriver().setup()
    WebDriverManager.getInstance(CHROME).setup()
    new ChromeDriver()
}
