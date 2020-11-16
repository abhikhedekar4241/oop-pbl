import java.util.Scanner;

public class InstagramBot extends BotFunctions {
    Constants constants = new Constants();

    public InstagramBot() {
        handler();
    }

    private void showMenu() {
        System.out.println(constants.welcomeMessage);
        System.out.println(constants.menuOptions);
    }

    private void handler() {
        Scanner scanner = new Scanner(System.in);
        boolean isLoggedIn = false, itr = true;
        int choice, max;
        String userNameToFollow, userNameToScrap;

        do {
            if (!isLoggedIn) {
                driverConfig();
                launch();
                signIn();
                isLoggedIn = true;
            }

            showMenu();
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println(constants.enterName);
                    userNameToFollow = scanner.next();
                    followByUserName(userNameToFollow);
                    break;
                case 2:
                    System.out.println(constants.enterName);
                    userNameToScrap = scanner.next();
                    System.out.println(constants.enterMax);
                    max = scanner.nextInt();
                    scrapAndFollow(userNameToScrap, max);
                    break;
                case 3:
                    System.out.println(constants.enterMax);
                    max = scanner.nextInt();
                    unfollowMultiple(max);
                    break;
                case 4:
                    dispose();
                    itr = false;
                    break;
                default:
                    System.out.println(constants.invalidChoice);
                    break;
            }
        } while (itr);

        scanner.close();
    }
}
