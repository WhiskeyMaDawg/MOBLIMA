import database.Database;

import Helper.Helper;
import MobView.CineplexApplicationView;

public class CineplexApp {
        /**
         * Main function that is the starting point of the application
         * @param args Arguments passed to the app
         * @throws Exception handles exception
         */
        public static void main(String[] args) throws Exception {
                try {
                        new Database();
                        CineplexApplicationView cineplexAppView = new CineplexApplicationView();
                        Helper.clearScreen();
                        printMOBLIMATitle();
                        Helper.pressAnyKeyToContinue();
                        cineplexAppView.applicationView();
                } catch (Exception e) {
                        System.out.println("Error caught in main app: " + e);
                } finally {
                        Database.saveAllFiles();
                        System.out.println("Program closing ... Thank you for using MOBLIMA!");
                }
        }

        /**
         * Prints the MOBLIMA title
         */
        private static void printMOBLIMATitle() {
                System.out.println();
                System.out.println(
                                "╔════════════════════════════════════════════════════════════════════════════════════════════════════╗");
                System.out.println(
                                "║              __       __   ______   _______   __        ______  __       __   ______               ║");
                System.out.println(
                                "║             /  \\     /  | /      \\ /       \\ /  |      /      |/  \\     /  | /      \\              ║");
                System.out.println(
                                "║             ▐▐  \\   /▐▐ |/▐▐▐▐▐▐  |▐▐▐▐▐▐▐  |▐▐ |      ▐▐▐▐▐▐/ ▐▐  \\   /▐▐ |/▐▐▐▐▐▐  |             ║");
                System.out.println(
                                "║             ▐▐▐  \\ /▐▐▐ |▐▐ |  ▐▐ |▐▐ |__▐▐ |▐▐ |        ▐▐ |  ▐▐▐  \\ /▐▐▐ |▐▐ |__▐▐ |             ║");
                System.out.println(
                                "║             ▐▐▐▐  /▐▐▐▐ |▐▐ |  ▐▐ |▐▐    ▐▐< ▐▐ |        ▐▐ |  ▐▐▐▐  /▐▐▐▐ |▐▐    ▐▐ |             ║");
                System.out.println(
                                "║             ▐▐ ▐▐ ▐▐/▐▐ |▐▐ |  ▐▐ |▐▐▐▐▐▐▐  |▐▐ |        ▐▐ |  ▐▐ ▐▐ ▐▐/▐▐ |▐▐▐▐▐▐▐▐ |             ║");
                System.out.println(
                                "║             ▐▐ |▐▐▐/ ▐▐ |▐▐ \\__▐▐ |▐▐ |__▐▐ |▐▐ |_____  _▐▐ |_ ▐▐ |▐▐▐/ ▐▐ |▐▐ |  ▐▐ |             ║");
                System.out.println(
                                "║             ▐▐ | ▐/  ▐▐ |▐▐    ▐▐/ ▐▐    ▐▐/ ▐▐       |/ ▐▐   |▐▐ | ▐/  ▐▐ |▐▐ |  ▐▐ |             ║");
                System.out.println(
                                "║             ▐▐/      ▐▐/  ▐▐▐▐▐▐/  ▐▐▐▐▐▐▐/  ▐▐▐▐▐▐▐▐/ ▐▐▐▐▐▐/ ▐▐/      ▐▐/ ▐▐/   ▐▐/              ║");
                System.out.println(
                                "║                                                                                                    ║");
                System.out.println(
                                "║                   Welcome to Movie Booking and Listing Management Application                      ║");
                System.out.println(
                                "║                                                                                                    ║");
                System.out.println(
                                "╚════════════════════════════════════════════════════════════════════════════════════════════════════╝");
        };
}