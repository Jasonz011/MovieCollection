public class Movie {
    private String title;
    private String cast;
    private String director;
    private String overview;
    private int runtime;
    private int userRating;

    public Movie(String s1, String s2, String s3, String s4, int r, int u) {
        title = s1;
        cast = s2;
        director = s3;
        overview = s4;
        runtime = r;
        userRating = u;
    }

    public String getTitle() {
        return title;
    }

    public String getCast() {
        return cast;
    }

    public String getDirector() {
        return director;
    }

    public String getOverview() {
        return overview;
    }

    public int getRuntime() {
        return runtime;
    }

    public int getUserRating() {
        return userRating;
    }
}
