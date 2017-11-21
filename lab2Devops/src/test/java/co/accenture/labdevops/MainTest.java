package co.accenture.labdevops;

import com.relevantcodes.extentreports.ExtentReports;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author diego.tapia
 */
public class MainTest {

    protected DriverHandler driver;
    protected ExtentReports report;
    protected File folderReport;
    protected static final String FOLDER_REPORT = "C:\\LabDevOps";
    protected static final String BASE_URL = "http://www.bolivariano.com.co/";

    public MainTest() {
    }

    @Test
    public void hello() 
        asasa
    
        
        try {
            // Init TestCase Bolivariano
            TestCaseBolivariano ts = new TestCaseBolivariano(this);

            //LoadURL
            this.driver.get(BASE_URL);

            //1) Cargar MainPage
            ts.cargarMainPage();

            Thread.sleep(3000);

            //2) Llenar Formulario
            ts.llenarFormulario();

            Thread.sleep(5000);

            //3) Seleccion Viaje
            ts.seleccionViaje();

            Thread.sleep(3000);

            //4) Seleccion Silla
            ts.seleccionSilla();
        }  catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }           
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
        // Init Driver
        initDriver();
        // Init Report
        initReport();
    }

    private void initDriver() {
        this.driver = new DriverHandler();
        this.driver.setUrl_Hub("http://52.87.198.111:4444/wd/hub");
        this.driver.setBrowser(Browser.CHROME);
        this.driver.setBrowserType(BrowserType.HUB);
        this.driver.setUPDriverHandler();
    }

    private void initReport() {
        try {
            this.folderReport = new File(FOLDER_REPORT + "\\Images\\");

            if (this.folderReport.exists()) {
                this.folderReport.mkdir();
                this.folderReport.mkdirs();
            }

            FileUtils.deleteDirectory(this.folderReport);

            report = new ExtentReports(FOLDER_REPORT + "\\Report.html", true);
            report.addSystemInfo("Host", System.getProperty("os.name")).addSystemInfo("Environment", "Development");
            report.loadConfig(new File("src/test/resources/config-report.xml"));
        } catch (IOException e) {            
            System.out.println(e.getMessage());
        }
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {

        if (this.driver != null) {
            this.driver.quit();            
        }
        report.flush();
        report.close();
    }

    public DriverHandler getDriver() {
        return driver;
    }

    public void setDriver(DriverHandler driver) {
        this.driver = driver;
    }

    public ExtentReports getReport() {
        return report;
    }

    public void setReport(ExtentReports report) {
        this.report = report;
    }

    public File getFolderReport() {
        return folderReport;
    }

    public void setFolderReport(File folderReport) {
        this.folderReport = folderReport;
    }
}
