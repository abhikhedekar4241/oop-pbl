import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.NoSuchElementException;

abstract class BotFunctions {
    WebDriver driver;
    Constants constants;

    public BotFunctions() {
        constants = new Constants();
    }

    void driverConfig() {
        System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver");
        driver = new ChromeDriver();
    }

    void delay(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException exception) {
            System.out.println(constants.delayException);
        }
    }

    void maximiseWindow() {
        driver.manage().window().maximize();
    }

    void unfollowMultiple(int max) {
        try {
            // Navigate to my profile
            driver.get(constants.instagramBaseUrl + constants.userName);
            delay(2);

            // Get following count
            String followingText = driver.findElement(new By.ByXPath(constants.followingListButtonPath)).getText();
            int followingCount = Integer.parseInt(followingText.split(" ")[0]);
            System.out.println(followingCount);

            // If max is more than following count then set max to be equal to following count.
            if (followingCount < max) {
                max = followingCount;
            }

            // Get following list dialog
            WebElement followingListButton = driver.findElement(new By.ByXPath(constants.followingListButtonPath));
            followingListButton.click();
            delay(2);
            WebElement followingListDialog = new WebDriverWait(driver, constants.milliSeconds).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(constants.followingListSelector)));
            System.out.println(followingListDialog);

            // Get elements from dialog4
            List<WebElement> followingList = followingListDialog.findElements(new By.ByCssSelector("li"));
            followingCount = followingList.size();
            System.out.println(followingCount);


            // Focus on the dialog for scrolling
            WebElement focusButton;
            try {
                focusButton = driver.findElement(new By.ByXPath(constants.focusButton1Path));
            } catch (NoSuchElementException exception) {
                try{
                    focusButton = driver.findElement(new By.ByXPath(constants.focusButton2Path));
                    System.out.println(exception.getMessage());
                }catch (NoSuchElementException excp){
                    focusButton = driver.findElement(new By.ByXPath(constants.focusButton3Path));
                    System.out.println(exception.getMessage());
                }
            }
            focusButton.click();

            // Setting up action chain for scrolling
            Actions actions = new Actions(driver);

            // Scroll down
            while (max < followingCount) {
                actions.sendKeys(Keys.SPACE).perform();
                followingListDialog = driver.findElement(new By.ByCssSelector(constants.followingListSelector));
                followingList = followingListDialog.findElements(new By.ByCssSelector("li"));
                followingCount = followingList.size();
                System.out.println(followingCount);
            }

            // Scroll up back to the top
            actions.keyDown(Keys.SHIFT);
            for (int i = 0; i < max; i += 1) {
                actions.sendKeys(Keys.SPACE).perform();
            }
            actions.keyUp(Keys.SHIFT);
            delay(3);

            // Click on unfollow button.
            for (int i = 1; i < max + 1; i += 1) {

                try {
                    String unfollowButtonPath = "/html/body/div[5]/div/div/div[2]/ul/div/li[" + i + "]/div/div[2]/button";
                    WebElement unFollowButton = new WebDriverWait(driver, constants.milliSeconds).until(ExpectedConditions.presenceOfElementLocated(By.xpath(unfollowButtonPath)));
                    unFollowButton.click();
                } catch (NoSuchElementException exception) {
                    String unfollowButtonPath = "/html/body/div[5]/div/div/div[2]/ul/div/li[" + i + "]/div/div[3]/button";
                    WebElement unFollowButton = new WebDriverWait(driver, constants.milliSeconds).until(ExpectedConditions.presenceOfElementLocated(By.xpath(unfollowButtonPath)));
                    unFollowButton.click();
                }

                delay(1);
                try {
                    WebElement privateUnfollowButton = new WebDriverWait(driver, constants.milliSeconds).until(ExpectedConditions.presenceOfElementLocated(By.xpath(constants.privateUnfollowButtonPath)));
                    privateUnfollowButton.click();
                } catch (NoSuchElementException exception) {
                    System.out.println(exception.getMessage());
                }
//                delay(5);
            }
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    void scrapAndFollow(String targetUserName, int max) {
        try {
            driver.get(constants.instagramBaseUrl + targetUserName);
            // Click on followers button on the page
            WebElement followersButton = new WebDriverWait(driver, constants.milliSeconds).until(ExpectedConditions.presenceOfElementLocated(By.xpath(constants.followersButtonPath)));
            followersButton.click();
            delay(3);

            // Get followers from the the dialog box
            WebElement followersList = new WebDriverWait(driver, constants.milliSeconds).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(constants.followersListSelector)));
            System.out.println(followersList);
            List<WebElement> followers = followersList.findElements(new By.ByCssSelector("li"));
            int numberOfFollowers = followers.size();

            System.out.println(numberOfFollowers);

            // Button to focus on the followers list for scrolling
            WebElement focusButton;
            try {
                focusButton = driver.findElement(new By.ByXPath(constants.focusButton1Path));
            } catch (Exception exception) {
                focusButton = driver.findElement(new By.ByXPath(constants.focusButton2Path));
                System.out.println(exception.getMessage());
            }
            focusButton.click();

            // Setting up action chain for scrolling
            Actions actions = new Actions(driver);

            // Scroll down
            while (numberOfFollowers < max) {
                actions.sendKeys(Keys.SPACE).perform();
                followersList = driver.findElement(new By.ByCssSelector(constants.followersListSelector));
                followers = followersList.findElements(new By.ByCssSelector("li"));
                numberOfFollowers = followers.size();
                System.out.println(numberOfFollowers);
            }

            // Scroll up back to the top
            actions.keyDown(Keys.SHIFT);
            for (int i = 0; i < max * 2; i += 1) {
                actions.sendKeys(Keys.SPACE).perform();
            }
            actions.keyUp(Keys.SHIFT);
            delay(3);

            // Click on follow buttons
            for (int i = 1; i < max + 1; i += 1) {
                String followButtonPath = "/html/body/div[5]/div/div/div[2]/ul/div/li[" + i + "]/div/div[2]/button";
                WebElement followButton = new WebDriverWait(driver, constants.milliSeconds).until(ExpectedConditions.presenceOfElementLocated(By.xpath(followButtonPath)));
                if (followButton.getText().equals("Follow")) {
                    followButton.click();
//                    delay(5);
                }
            }
        } catch (NoSuchElementException exception) {
            System.out.println(constants.followersPathUpdate);
            System.out.println(constants.followersListPathUpdate);
        } catch (Exception exception) {
            System.out.println(constants.followersListError + exception.getMessage());
        }
    }

    void followByUserName(String userName) {
        try {
            // Navigate to instagram login page
            driver.get(constants.instagramBaseUrl + userName);

            delay(3);
            // Find password field and enter password
            WebElement followButton = new WebDriverWait(driver, constants.milliSeconds)
                    .until(ExpectedConditions.presenceOfElementLocated(By.xpath(constants.followButtonPath)));
            followButton.click();
            delay(3);


        } catch (NoSuchElementException exception) {
            System.out.println(constants.followButtonPathUpdate);
        } catch (Exception exception) {
            System.out.println(constants.followButtonPressError + exception.getMessage());
        }
    }

    void signIn() {
        try {
            // Navigate to instagram login page
            driver.get(constants.instagramLoginUrl);

            // Find userName field and enter userName
            WebElement userNameField = new WebDriverWait(driver, constants.milliSeconds)
                    .until(ExpectedConditions.presenceOfElementLocated(By.xpath(constants.userNameFieldPath)));
            userNameField.clear();
            delay(2);
            userNameField.sendKeys(constants.userName);

            // Find password field and enter password
            WebElement passwordField = new WebDriverWait(driver, constants.milliSeconds)
                    .until(ExpectedConditions.presenceOfElementLocated(By.xpath(constants.passwordFieldPath)));
            delay(2);
            passwordField.clear();
            passwordField.sendKeys(constants.password);

            // Instead of clicking on login button, we can just press enter.
            delay(2);
            passwordField.sendKeys(Keys.ENTER);
        } catch (NoSuchElementException exception) {
            System.out.println(constants.signInPathUpdate + exception.getMessage());
        } catch (Exception exception) {
            System.out.println(constants.signInButtonError + exception.getMessage());
        }
    }

    public void launch() {
        driver.get("https://duckduckgo.com");
        maximiseWindow();
    }

    void dispose() {
        driver.close();
        driver.quit();
    }
}
