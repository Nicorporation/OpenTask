#!/bin/bash
gnome-terminal -- java -jar selenium-server-standalone-3.141.59.jar -role hub -port 4445;
gnome-terminal -- java -Dwebdriver.chrome.driver=chromedriver -jar selenium-server-standalone-3.141.59.jar -role node -hub http://localhost:4445/grid/register