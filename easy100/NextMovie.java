package easy100;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class NextMovie {

    class Movie {
        String name;
        int year;

        public Movie(String name, int year) {
            this.name = name;
            this.year = year;
        }

        public String getName() {
            return name;
        }

        public int getYear() {
            return year;
        }
    }

    public String getNextMovie(String[] names, int[] years) {

        List<Movie> movies = new ArrayList<>();

        for (int i = 0; i < names.length; i++) {
            movies.add(new Movie(names[i], years[i]));
        }

        movies.sort(Comparator.comparing(Movie::getYear).reversed().thenComparing(Movie::getName));

        return movies.get(0).name;
    }
}
