import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;

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
        ArrayList<Movie> theseMovies = new ArrayList<>();
        for (int i = 1; i<movies.size(); i++) {
            String title = movies.get(i).getTitle();
            if (title.contains(term)) {
                theseMovies.add(movies.get(i));
            }
        }
        if (theseMovies.isEmpty()) {
            System.out.println("No movie titles match that search term!");
        } else {
            sortByTitle(theseMovies);
            // similar to other code segment
            for (int i = 0; i < theseMovies.size(); i++) {
                System.out.println(i + 1 + ". " + theseMovies.get(i).getTitle());
                System.out.println("Which movie would you like to learn more about?");
                System.out.print("Enter number: ");
                int num = scanner.nextInt() - 1;
                scanner.nextLine();
                System.out.println("Title: " + theseMovies.get(num).getTitle());
                System.out.println("Runtime: " + theseMovies.get(num).getRuntime());
                System.out.println("Directed by: " + theseMovies.get(num).getDirector());
                System.out.println("Cast: " + theseMovies.get(num).getCast());
                System.out.println("Overview: " + theseMovies.get(num).getOverview());
                System.out.println("User rating: " + theseMovies.get(num).getUserRating());
            }
        }
    }

    public void searchCast() {
        System.out.print("Enter a person to search for (first or last name): ");
        String term = scanner.nextLine().toLowerCase();
//        ArrayList<Movie> theseMovies = new ArrayList<>();
        ArrayList<String> actorNames = new ArrayList<>();
        for (int i = 1; i<movies.size(); i++) {
            String actors = movies.get(i).getCast();
            String[] actorList = actors.split("\\|");
            ArrayList<String> arrayOfActors = new ArrayList<>(Arrays.stream(actorList).toList()); // current movie's actors
//            int length = actorNames.size();
            for (String actor : arrayOfActors) {
                if (actor.toLowerCase().contains(term) && !actorNames.contains(actor)) {
                    actorNames.add(actor);
                }
            }
//            if (!(length == actorNames.size())) { // checks if any actors have been added to the list of actor names
//                theseMovies.add(movies.get(i));
//            }
        }
        if (actorNames.isEmpty()) {
            System.out.println("No results match your search");
        } else {
            sortByName(actorNames);
            for (int i = 0; i<actorNames.size(); i++) {
                System.out.println(i+1 + ". " + actorNames.get(i));
            }
            System.out.println("Which would you liek to see all movies for?");
            System.out.print("Enter number: ");
            String actor = actorNames.get(scanner.nextInt());
            scanner.nextLine();
            ArrayList<Movie> actorsMovies = new ArrayList<>();
            for (int i = 1; i<movies.size(); i++) {
                Movie currentMovie = movies.get(i);
                if (currentMovie.getCast().contains(actor)) {
                    actorsMovies.add(currentMovie);
                }
            }
            sortByTitle(actorsMovies);
            // similar to other code segment
            for (int i = 0; i < actorsMovies.size(); i++) {
                System.out.println(i + 1 + ". " + actorsMovies.get(i).getTitle());
                System.out.println("Which movie would you like to learn more about?");
                System.out.print("Enter number: ");
                int num = scanner.nextInt() - 1;
                scanner.nextLine();
                System.out.println("Title: " + actorsMovies.get(num).getTitle());
                System.out.println("Runtime: " + actorsMovies.get(num).getRuntime());
                System.out.println("Directed by: " + actorsMovies.get(num).getDirector());
                System.out.println("Cast: " + actorsMovies.get(num).getCast());
                System.out.println("Overview: " + actorsMovies.get(num).getOverview());
                System.out.println("User rating: " + actorsMovies.get(num).getUserRating());
            }
        }



//        for (int i = 0; i<theseMovies.size(); i++) {
//            while (theseMovies.get(i).getCast().contains(term)) {
//
//            }
//        }
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

    public void sortByTitle(ArrayList<Movie> movies) {
        Movie movie;
        for (int i = 1; i < movies.size(); i++) {
            movie = movies.get(i);
            int j = i-1;
            while (j >= 0 && movies.get(j).getTitle().compareTo(movie.getTitle()) > 0) {
                movies.set(j+1, movies.get(j));
                j--;
            }
            movies.set(j+1, movie);
        }
    }

    public void sortByName(ArrayList<String> names) {
        String s = "";
        for (int i = 1; i < names.size(); i++) {
            String name  = names.get(i);
            int j = i-1;
            while (j >= 0 && names.get(j).compareTo(name) > 0) {
                names.set(j+1, names.get(j));
                j--;
            }
            names.set(j+1, name);
        }
    }
}
