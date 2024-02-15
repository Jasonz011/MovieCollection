import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
public class MovieCollection {
    private ArrayList<Movie> movies;
    private Scanner scanner;
    public MovieCollection() {
        start();
    }

    public void start() {
        readData();
        mainMenu();
    }

    public void mainMenu() {
        System.out.println("Welcome to the movie collection!");
        scanner = new Scanner(System.in);
        String menuOption = "";

        while (!menuOption.equals("q")) {
            System.out.println("------------ Main Menu ----------");
            System.out.println("- search (t)itles");
            System.out.println("- search (c)ast");
            System.out.println("- (q)uit");
            System.out.print("Enter choice: ");
            menuOption = scanner.nextLine();

            if (menuOption.equals("t")) {
                searchTitles();
            } else if (menuOption.equals("c")) {
                searchCast();
            } else if (menuOption.equals("q")) {
                System.out.println("Goodbye!");
            } else {
                System.out.println("Invalid choice!");
            }
        }
    }

    public void searchTitles() {
        System.out.println("Enter a title search term: ");
        String term = scanner.nextLine();
        ArrayList<String> titles = new ArrayList<>();
        for (Movie movie : movies) {
            String title = movie.getTitle();
            if (title.contains(term)) ;
            titles.add(title);
        }
        insertionSortWordList(titles);
        for (int i = 0; i<titles.size(); i++) {
            System.out.println(i+1 + ". " + titles.get(i));
        }
        if (titles.size() == 0) {
            System.out.println("No movie titles match that search term!");
        } else {
            System.out.println("Which movie would you like to learn more about?");
            System.out.print("Enter number: ");
            int num = scanner.nextInt()-1;
            scanner.nextLine();
            System.out.println("Title: " + titles.get(num));
            //
            //
            //
            //
            //fasdfasdfasdf
        }
    }

    public void searchCast() {

    }

    public void readData() {
        ArrayList<String> info = new ArrayList<>();
        try {
            File myFile = new File("src\\movies_data.csv");
            Scanner fileScanner = new Scanner(myFile);
            while (fileScanner.hasNext()) {
                String data = fileScanner.nextLine();
                info.add(data);
            }
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
        String[] movieInfo;
        movies = new ArrayList<>();
        for (int i = 1; i<info.size(); i++) {
            movieInfo = info.get(i).split(",");
            String title = movieInfo[0];
            String cast = movieInfo[1];
            String director = movieInfo[2];
            String overview = movieInfo[3];
            int runtime = Integer.parseInt(movieInfo[4]);
            int userRating = Integer.parseInt(movieInfo[5]);
            Movie movie = new Movie(title, cast, director, overview, runtime, userRating);
            movies.add(movie);
        }
    }

    public void insertionSortWordList(ArrayList<String> words) {
        String s = "";
        for (int i = 1; i < words.size(); i++) {
            s = words.get(i);
            int j = i-1;
            while (j >= 0 && words.get(j).compareTo(s) > 0) {
                words.set(j+1, words.get(j));
                j--;
            }
            words.set(j+1, s);
        }
    }
}
