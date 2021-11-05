Folder "WebDriver" contains 2 chromedriver versions - one for Windows ("chromedriver.exe" - supporting Chrome version 95) and one for Linux ("chromedriver" - supporting Chrome version 91). To execute tests locally, run the following commands in "WebDriver":

Run the selenium hub and node on Windows:
1. Hub: java -jar selenium-server-standalone-3.141.59.jar -role hub
2. Node: java -Dwebdriver.chrome.driver=chromedriverW.exe -jar selenium-server-standalone-3.141.59.jar -role node -hub http://localhost:4444/grid/register

Run the selenium hub and node on Linux:
1. Hub: java -jar selenium-server-standalone-3.141.59.jar -role hub -port 4445
2. Node: java -Dwebdriver.chrome.driver=chromedriver -jar selenium-server-standalone-3.141.59.jar -role node -hub http://localhost:4445/grid/register
3. Or, alternatively, double click HubAndNode.sh

To execute the tests - run the testNG suite in "src/test/TestSuites/testng.xml"
