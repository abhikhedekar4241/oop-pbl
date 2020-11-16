public class Constants extends Credentials {
    // Strings
    final String welcomeMessage = "Hey there! Here's the list of functions that I can perform.";
    final String menuOptions = "\n1. Follow By username\n2. Scrap and follow\n3. Unfollow multiple\n4. Exit\nEnter your choice: ";
    final String invalidChoice = "\nInvalid choice. Please try again.";
    final String enterName = "\nEnter target username: ";
    final String enterMax = "\nEnter maximum count: ";
    final String enterUserName = "\nEnter your username";
    final String enterPassword = "\nEnter your password";

    // Timeout
    final int milliSeconds = 30;

    // URLs
    final String instagramBaseUrl = "https://www.instagram.com/";
    final String instagramLoginUrl = instagramBaseUrl + "accounts/login/?hl=en&source=auth_switcher";

    // sign in step
    final String userNameFieldPath = "/html/body/div[1]/section/main/div/div/div[1]/div/form/div/div[1]/div/label/input";
    final String passwordFieldPath = "/html/body/div[1]/section/main/div/div/div[1]/div/form/div/div[2]/div/label/input";

    // follow by username step
    final String followButtonPath = "/html/body/div[1]/section/main/div/header/section/div[1]/div[1]/div/div/div/span/span[1]/button";

    // followers list step
    final String followersButtonPath = "/html/body/div[1]/section/main/div/header/section/ul/li[2]/a";
    final String followersListSelector = "div[role='dialog'] ul";
    final String focusButton1Path = "/html/body/div[5]/div/div/div[2]/ul/div/li[1]/div/div[2]/div[2]/div";
    final String focusButton2Path = "/html/body/div[5]/div/div/div[2]/ul/div/li[2]/div/div[2]/div[2]/div";
//    final String followButtonInListPath = "/html/body/div[5]/div/div/div[2]/ul/div/li[ID_HERE]/div/div[3]/button";

    // unfollow all step
    final String followingListSelector = "div[role='dialog'] ul";
    final String followingListButtonPath = "/html/body/div[1]/section/main/div/header/section/ul/li[3]/a";
    final String privateUnfollowButtonPath = "/html/body/div[6]/div/div/div/div[3]/button[1]";

    // Path Exception messages
    final String followButtonPathUpdate = "Update path for the required parameter in follow button press step. ";
    final String signInPathUpdate = "Update path for the required parameter in sign in step. ";
    final String delayException = "Exception in delay. ";

    final String followersPathUpdate = "Update path for the required parameter in followers button press step. ";
    final String followersListPathUpdate = "Update path for the required parameter in followers list step. ";

    // Error messages
    final String followButtonPressError = "Error at follow button press step. ";
    final String signInButtonError = "Error at sign in step. ";
    final String followersListError = "Error at followers list step. ";

}

