AutoHero Automated Tests
================
Automated tests for https://www.autohero.com/

### Requirements
1. Java 1.8+ install
2. Maven 3.3+ install
3. Serenity Report
4. Webdriver manager

### How to run
1. Download the source code from git: `git@github.com:SkifGTR/auto1-serenity.git`
2. Open a command line in the cloned directory: `cd ./auto1_serenity`
3. Now run tests: `mvn clean verify`

### How to run tests in different browsers
* `mvn clean verify` - this is default configuration Chrome browser will be run
* `mvn clean verify -Dwebdriver.driver=firefox` - running with Firefox browser